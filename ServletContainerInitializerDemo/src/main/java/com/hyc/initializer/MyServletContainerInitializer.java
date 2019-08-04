package com.hyc.initializer;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import com.hyc.utils.ReflectionUtils;

@HandlesTypes(value = IMyApplicationInitializer.class)//此处配置接口类型，容器会将此接口类型的实现类和子类传递过来
public class MyServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext ctx) throws ServletException {
		System.out.println("初始化开始");

		List<MyApplicationInitializer> initializers = new LinkedList<>();

		if (classes != null && !classes.isEmpty()) {
			for (Class<?> initializerClass : classes) {
				System.out.println(initializerClass);
				if (!initializerClass.isInterface() && !Modifier.isAbstract(initializerClass.getModifiers())
						&& MyApplicationInitializer.class.isAssignableFrom(initializerClass)) {
					try {
						initializers.add((MyApplicationInitializer) ReflectionUtils.accessibleConstructor(initializerClass).newInstance());
					} catch (Throwable ex) {
						throw new ServletException("Failed to instantiate MyApplicationInitializer class", ex);
					}
				}
			}
		}

		for (MyApplicationInitializer initializer : initializers) {
			initializer.onStartup(ctx);
		}

		System.out.println("初始化结束");
	}

}
