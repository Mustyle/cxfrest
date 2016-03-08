package org.fool.spring.cxf.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.fool.spring.cxf.rest.domain.Product;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/*
 * CXF ���ṩ��һ�ָ�Ϊ���ķ�ʽ��ʹ��org.apache.cxf.jaxrs.client.WebClient������ REST ����
 * ���ַ�ʽ�ڴ�������ϻ����൱���ġ�
 */
public class CXFWebClient {
	public static void main(String[] args) {
		String baseAddress = "http://localhost:8080/cxfrest/ws/rest";

		List<Object> providerList = new ArrayList<>();
		providerList.add(new JacksonJsonProvider());

		List<Product> productList = WebClient.create(baseAddress, providerList).path("/products")
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Product>>() {
				});

		productList.forEach(
				product -> System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice()));
		
		Product product2 = WebClient.create(baseAddress, providerList).path("/product/2")
				.accept(MediaType.APPLICATION_JSON).get(Product.class);
		
		System.out.println(product2.getId() + " " + product2.getName() + " " + product2.getPrice());
	}
}
