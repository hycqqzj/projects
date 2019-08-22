package com.hyc.servlet;

import com.hyc.service.HelloService;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步servlet,使用自定义线程池
 * http://localhost:8080/servlet-async/poolAsyncHello
 */
@WebServlet(urlPatterns = "/poolAsyncHello", asyncSupported = true)
public class ThreadPoolAsyncHelloServlet extends HttpServlet {
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
        threadPool.execute(() -> {
            try {
                // 耗时处理
                int time = HelloService.process();
                resp.getWriter().write(time + "-Hello World!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        });
    }
}
