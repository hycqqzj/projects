package com.hyc.initializer;

import javax.servlet.ServletContext;

public interface IMyApplicationInitializer {
	void onStartup(ServletContext ctx);

}
