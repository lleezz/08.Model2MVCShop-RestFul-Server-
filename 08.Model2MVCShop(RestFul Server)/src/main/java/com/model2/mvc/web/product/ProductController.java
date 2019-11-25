package com.model2.mvc.web.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.service.product.ProductService;


// ==> 상품관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	// 공통으로 사용하는 값들을 쉽게 가져다 쓸 수 있도록 한다.
	// 공통으로 쓰는 값들이 정의된 파일에 접근, 원하는 데이터 읽어와 사용하기
	// config > common.properties 파일에서 
	
	// pageUnit의 값을 읽어와 해당 클래스 내에서 변수 pageUnit로 사용
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	// pageSize의 값을 읽어와 해당 클래스 내에서 변수 pageSize로 사용
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
//	@RequestMapping(value="addProduct", method=RequestMethod.GET)
//	public String addProduct() throws Exception {
//		
//		System.out.println("상품 추가 페이지");
//		
//		return "redirect:/product/addProductView.jsp";
//		
//	}
	
	
	@RequestMapping( value="addProduct", method=RequestMethod.GET)
	public ModelAndView addProduct() throws Exception {
		
		System.out.println("상품 추가 페이지");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/product/addProductView.jsp");
		
		return mv;
		
	}
	

}
