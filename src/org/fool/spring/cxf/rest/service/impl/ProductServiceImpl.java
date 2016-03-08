package org.fool.spring.cxf.rest.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fool.spring.cxf.rest.domain.Product;
import org.fool.spring.cxf.rest.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

	private static List<Product> productList = new ArrayList<>();

	static {
		productList.add(new Product(1, "iPhone 6s", 6000));
		productList.add(new Product(3, "iWatch", 2000));
		productList.add(new Product(2, "iPad Air", 4000));
	}

	@Override
	public List<Product> retrieveAllProducts() {
		productList.sort(Comparator.comparing(Product::getId));

		return productList;
	}

	@Override
	public Product retrieveProductById(long id) {
		Product targetProduct = null;

		for (Product product : productList) {
			if (product.getId() == id) {
				targetProduct = product;
				break;
			}
		}

		return targetProduct;
	}

	@Override
	public List<Product> retrieveProductsByName(String name) {
		List<Product> targetProductList = new ArrayList<Product>();

		for (Product product : productList) {
			if (product.getName().contains(name)) {
				targetProductList.add(product);
			}
		}

		return targetProductList;
	}

	@Override
	public Product saveProduct(Product product) {
		product.setId(new Date().getTime());
		productList.add(product);

		return product;
	}

	@Override
	public Product updateProductById(long id, Map<String, Object> fieldMap) {
		Product product = retrieveProductById(id);

		if (product != null) {
			try {
				for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
					Field field = Product.class.getDeclaredField(fieldEntry.getKey());
					field.setAccessible(true);
					field.set(product, fieldEntry.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return product;
	}

	@Override
	public Product deleteProductById(long id) {
		Product targetProduct = null;

		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = iterator.next();

			if (product.getId() == id) {
				targetProduct = product;
				iterator.remove();

				break;
			}
		}

		return targetProduct;
	}

}
