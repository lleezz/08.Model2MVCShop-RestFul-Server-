package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		
		product.setProdName("testService목캔디");
		product.setPrice(1000);
		product.setProdDetail("Product Service 테스트 상품");
		
		productService.addProduct(product);
		
		
	}
	
//	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
//		int prodNo = 10000;
//		product.setProdNo(prodNo);
		
		product = productService.getProduct(10000);
		
		Assert.assertEquals("vaio vgn FS70B", product.getProdName());
	}
	
	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = productService.getProduct(10100);
		
		product.setProdName("test아이폰수정수정");
		
		productService.updateProduct(product);
		
	}
	
	@Test
	public void testGetProductList() throws Exception {
		Search search = new Search();
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("test");
//		search.setSearchCondition("2");
//		search.setSearchKeyword("1000");
		Map<String, Object> map = productService.getProductList(search);
		
		List<Product> list = (List<Product>)map.get("list");
		
		System.out.println(list.size());
		
		int totalCount = (int)map.get("totalCount");
		System.out.println(totalCount);
		
	}
	
	
	
	
	
	



}
