package com.model2.mvc.service.product.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class ProductDaoTest {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		
		Product product = new Product();
		
		product.setProdName("test아이폰");
		product.setPrice(1000);
		product.setManuDate("2019-10-01");
		
		productDao.addProduct(product);
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		product.setProdNo(10000);
		int prodNo = product.getProdNo();
		
		productDao.getProduct(prodNo);
	}
	
	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = productDao.getProduct(10100);
		
		product.setProdName("test아이폰수정");
		
		productDao.updateProduct(product);
		
	}
	
	@Test
	public void testGetProductList() throws Exception {
		Search search = new Search();
		
		search.setCurrentPage(1);
		search.setPageSize(3);
//		search.setSearchCondition("1");
//		search.setSearchKeyword("아이폰");
		search.setSearchCondition("2");
		search.setSearchKeyword("1000");
		List<Product> list = productDao.getProductList(search);
		System.out.println(list);
		
		
		
	}
	
	
	
	
	
	
	//@Test
	public void testGetTotalCount() throws Exception {
		
		Search search = new Search();
		search.setSearchCondition("1");
		search.setSearchKeyword("아이폰");
		
		productDao.getTotalCount(search);
		
	}
	
	

}
