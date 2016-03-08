package org.fool.spring.cxf.rest.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.fool.spring.cxf.rest.service.impl.ProductServiceImpl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class Server {
	public static void main(String[] args) {
		// ���ResourceClass
		List<Class<?>> resourceClassList = new ArrayList<>();
		resourceClassList.add(ProductServiceImpl.class);

		// ���ResourceProvider
		List<ResourceProvider> resourceProviderList = new ArrayList<>();
		resourceProviderList.add(new SingletonResourceProvider(new ProductServiceImpl()));

		// ���Provider
		List<Object> providerList = new ArrayList<>();
		providerList.add(new JacksonJsonProvider());

		// ���� REST����
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		factoryBean.setAddress("http://localhost:8080/cxfrest/ws/rest");
		factoryBean.setResourceClasses(resourceClassList);
		factoryBean.setResourceProviders(resourceProviderList);
		factoryBean.setProviders(providerList);
		
		factoryBean.create();
		
		System.out.println("REST WS is published...");
	}
}
