package com.hyc.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class DyncMethodInterceptor implements MethodInterceptor {

	/**
	 * 执行代理逻辑
	 * proxyObj动态生成的代理对象
	 */
	@Override
	public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("代理执行" + method.getName() + "方法之前");
		Object result = methodProxy.invokeSuper(proxyObj, args);
		System.out.println("代理执行" + method.getName() + "方法之后");
		
		return result;
	}

}
