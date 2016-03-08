package org.fool.spring.cxf.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.fool.spring.cxf.rest.domain.Product;
import org.fool.spring.cxf.rest.service.ProductService;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/*
 * ������ʹ�� CXF �ṩ��org.apache.cxf.jaxrs.client.JAXRSClientFactory������������ProductService�������
 * ͨ������������Ŀ������ϵķ������ͻ���ͬ��Ҳ��Ҫʹ�� Provider��
 * ��ʱ��Ȼʹ���� Jackson �ṩ��org.codehaus.jackson.jaxrs.JacksonJsonProvider
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
