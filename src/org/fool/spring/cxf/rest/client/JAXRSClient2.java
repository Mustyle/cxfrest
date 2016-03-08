package org.fool.spring.cxf.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.fool.spring.cxf.rest.domain.Product;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/*
 * 在 JAX-RS 2.0 中提供了一个名为javax.ws.rs.client.ClientBuilder的工具类，
 * 可用于创建客户端并调用 REST 服务，显然这种方式比前一种要先进，因为在代码中不再依赖 CXF API 了
 */
public class JAXRSClient2 {
	public static void main(String[] args) {
		String baseAddress = "http://localhost:8080/ws/rest";

		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();

		List<Product> productList = ClientBuilder.newClient().register(jsonProvider).target(baseAddress)
				.path("/products").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Product>>() {
				});

		productList.forEach(
				product -> System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice()));

		Product product2 = ClientBuilder.newClient().register(jsonProvider).target(baseAddress).path("/product/2")
				.request(MediaType.APPLICATION_JSON).get(Product.class);

		System.out.println(product2.getId() + " " + product2.getName() + " " + product2.getPrice());

	}
}
