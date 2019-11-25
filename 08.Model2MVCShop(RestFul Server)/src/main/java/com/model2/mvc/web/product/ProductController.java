package com.model2.mvc.web.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.service.product.ProductService;


// ==> ��ǰ���� Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	// �������� ����ϴ� ������ ���� ������ �� �� �ֵ��� �Ѵ�.
	// �������� ���� ������ ���ǵ� ���Ͽ� ����, ���ϴ� ������ �о�� ����ϱ�
	// config > common.properties ���Ͽ��� 
	
	// pageUnit�� ���� �о�� �ش� Ŭ���� ������ ���� pageUnit�� ���
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	// pageSize�� ���� �о�� �ش� Ŭ���� ������ ���� pageSize�� ���
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
//	@RequestMapping(value="addProduct", method=RequestMethod.GET)
//	public String addProduct() throws Exception {
//		
//		System.out.println("��ǰ �߰� ������");
//		
//		return "redirect:/product/addProductView.jsp";
//		
//	}
	
	
	@RequestMapping( value="addProduct", method=RequestMethod.GET)
	public ModelAndView addProduct() throws Exception {
		
		System.out.println("��ǰ �߰� ������");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/product/addProductView.jsp");
		
		return mv;
		
	}
	

}
