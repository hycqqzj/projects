package com.hyc.servlet;

import com.hyc.service.HelloService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 同步servlet
 * http://localhost:8080/servlet-async/syncHello
 */
@WebServlet("/syncHello")
public class SyncHelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 耗时处理
        int time = HelloService.process();
        resp.getWriter().write(time + "-Hello World!");
    }
}
