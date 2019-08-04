package com.hyc.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理逻辑
 */
public class DynaInvocationHandler implements InvocationHandler {
	private Object target;
	
	public DynaInvocationHandler(Object target) {
		this.target = target;
	}

	/**
	 * 动态代理的执行
	 * proxy动态生成的代理类对象
	 * method要执行的方法
	 * args方法参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理执行" + method.getName() + "方法之前");
		
		Object result = method.invoke(target, args);
		
		System.out.println("代理执行" + method.getName() + "方法之后");

		return result;
	}
}
