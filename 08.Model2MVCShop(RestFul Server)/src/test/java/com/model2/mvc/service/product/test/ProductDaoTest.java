package com.model2.mvc.service.product.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	@Test
	public void testAddProduct() throws Exception{
		
		
		Product product = new Product();
		
		product.setProdName("test¾ÆÀÌÆù");
		product.setPrice(1000);
		product.setManuDate("2019-10-01");
		
		productDao.addProduct(product);
		
	}
	
	
	

}
