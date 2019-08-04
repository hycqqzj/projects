package com.hyc;

import java.lang.reflect.Proxy;
import sun.misc.ProxyGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hyc.proxy.cglib.DyncMethodInterceptor;
import com.hyc.proxy.jdk.DynaInvocationHandler;
import com.hyc.service.ISystemService;
import com.hyc.service.SystemServiceImpl;

@SuppressWarnings("restriction")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicProxyApplicationTests {

	@Test
	public void testJDKDyn() {
		ISystemService sysService = new SystemServiceImpl();

		DynaInvocationHandler handler = new DynaInvocationHandler(sysService);

		ISystemService proxyService = (ISystemService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class<?>[] { ISystemService.class }, handler);
		
		Object res = proxyService.saveSystemInfo("haha");
		System.out.println("方法返回值：" + res);
		
		byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", ISystemService.class.getInterfaces());
		System.out.println(new String(classFile));
	}
	
	@Test
	public void testCGLibDyn() {
		DyncMethodInterceptor interceptor = new DyncMethodInterceptor();
		SystemServiceImpl proxyService = (SystemServiceImpl) interceptor.getProxy(SystemServiceImpl.class);
		Object res = proxyService.saveSystemInfo("haha");
		System.out.println("方法返回值：" + res);
	}

}
