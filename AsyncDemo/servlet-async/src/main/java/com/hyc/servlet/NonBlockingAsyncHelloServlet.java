package com.hyc.servlet;

import com.hyc.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步servlet,使用自定义线程池和非阻塞IO
 * http://localhost:8080/servlet-async/nonBlockPoolAsyncHello
 */
@WebServlet(urlPatterns = "/nonBlockPoolAsyncHello", asyncSupported = true)
public class NonBlockingAsyncHelloServlet extends HttpServlet {
    // 异步任务线程池
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(16, 32,
            60000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(200));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        ServletInputStream inputStream = req.getInputStream();
        ServletOutputStream outputStream = resp.getOutputStream();

        inputStream.setReadListener(new ReadListener() {
            @Override
            public void onDataAvailable() throws IOException {
                System.out.println("Data Available");
            }

            @Override
            public void onAllDataRead() throws IOException {
                outputStream.setWriteListener(new WriteListener() {
                    @Override
                    public void onWritePossible() throws IOException {
                        threadPool.execute(() -> {
                            try {
                                // 耗时处理
                                int time = HelloService.process();
                                byte[] content = (time + "-Hello World!").getBytes();
                                outputStream.write(content, 0, content.length);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                asyncContext.complete();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable t) {
                        asyncContext.complete();
                        t.printStackTrace();
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable t) {
                asyncContext.complete();
                t.printStackTrace();
            }
        });
    }
}
