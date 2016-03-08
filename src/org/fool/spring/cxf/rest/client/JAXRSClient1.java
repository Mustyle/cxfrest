package org.fool.spring.cxf.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.fool.spring.cxf.rest.domain.Product;
import org.fool.spring.cxf.rest.service.ProductService;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/*
 * 本质是使用 CXF 提供的org.apache.cxf.jaxrs.client.JAXRSClientFactory工厂类来创建ProductService代理对象，
 * 通过代理对象调用目标对象上的方法。客户端同样也需要使用 Provider，
 * 此时仍然使用了 Jackson 提供的org.codehaus.jackson.jaxrs.JacksonJsonProvider
 */
public class JAXRSClient1 {
	public static void main(String[] args) {
		String baseAddress = "http://localhost:8080/ws/rest";

		List<Object> providerList = new ArrayList<>();
		providerList.add(new JacksonJsonProvider());

		ProductService productService = JAXRSClientFactory.create(baseAddress, ProductService.class, providerList);

		List<Product> productList = productService.retrieveAllProducts();

		productList.forEach(product -> System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice()));

	}
}
