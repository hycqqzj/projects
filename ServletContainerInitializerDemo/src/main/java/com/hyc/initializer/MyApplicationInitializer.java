package com.hyc.initializer;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import com.hyc.comps.UserFilter;
import com.hyc.comps.UserListener;
import com.hyc.comps.UserServlet;

public class MyApplicationInitializer implements IMyApplicationInitializer {

	@Override
	public void onStartup(ServletContext ctx) {

		// 注册组件 ServletRegistration
		ServletRegistration.Dynamic servlet = ctx.addServlet("userServlet", new UserServlet());
		// 配置servlet的映射信息
		servlet.addMapping("/user");

		// 注册Listener
		ctx.addListener(UserListener.class);

		// 注册Filter FilterRegistration
		FilterRegistration.Dynamic filter = ctx.addFilter("userFilter", UserFilter.class);
		// 配置Filter的映射信息
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		
		String info = ctx.getServerInfo();
		System.out.println("Web容器启动，信息：" + info);
	}

}
