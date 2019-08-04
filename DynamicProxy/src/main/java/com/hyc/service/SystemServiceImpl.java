package com.hyc.service;

public class SystemServiceImpl implements ISystemService {

	@Override
	public Integer saveSystemInfo(String info) {
		System.out.println("执行SystemServiceImpl.saveSystemInfo()开始");
		try {
			// 假设花了一秒时间
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("执行SystemServiceImpl.saveSystemInfo()结束");
		
		return 1;
	}

}
