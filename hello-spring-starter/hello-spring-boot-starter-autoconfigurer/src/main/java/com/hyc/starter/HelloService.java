package com.hyc.starter;

public class HelloService {
	HelloProperties properties;

	public String sayHello(String name) {
		return properties.getPrefix() + "-" + name + "-" + properties.getSuffix();
	}

	public HelloProperties getProperties() {
		return properties;
	}

	public void setProperties(HelloProperties properties) {
		this.properties = properties;
	}

}
