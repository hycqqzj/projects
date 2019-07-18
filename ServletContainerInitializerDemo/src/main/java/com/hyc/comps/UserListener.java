package com.hyc.comps;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//ServletContext servletContext = sce.getServletContext();
		System.out.println("UserListener...contextInitialized...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("UserListener...contextDestroyed...");
	}

}
