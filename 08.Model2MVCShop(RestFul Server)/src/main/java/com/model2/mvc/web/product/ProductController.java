package com.model2.mvc.web.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
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
	
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProduct() throws Exception {
		
		System.out.println("상품 추가 페이지");
		
		return "redirect:/product/addProductView.jsp";
		
	}
	
	
//	@RequestMapping( value="addProduct", method=RequestMethod.GET)
//	public ModelAndView addProduct() throws Exception {
//		
//		System.out.println("상품 추가 페이지");
//		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("redirect:/product/addProductView.jsp");
//		
//		return mv;
//		
//	}
	
	
	@RequestMapping( value="addProduct", method=RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product, Model model ) throws Exception {
		
		System.out.println("상품 등록 실행");
		
		productService.addProduct(product);
		
		model.addAttribute("product", product);
		
		
		return "forward:/product/addProduct.jsp";
	}
	
//	@RequestMapping( value="getProduct" )
//	public String getProduct( @RequestParam("prodNo") int prodNo, Model model ) throws Exception {
//		
//		System.out.println("상품 상세");
//		
//		Product product = productService.getProduct(prodNo);
//		
//		model.addAttribute("product", product);
//		
//		return "redirect:/product";
//	}
	
	@RequestMapping( value="listProduct" )
	public String listProduct( @ModelAttribute("search") Search search, Model model, @RequestParam("menu") String menu) throws Exception {
		
		System.out.println("상품 목록");
		
		if(search.getCurrentPage() == 0 ) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = productService.getProductList(search);
		
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);
		
		
		return "forward:/product/listProduct.jsp";
	}
	
	
	
	@RequestMapping( value="getProduct", method=RequestMethod.GET )
	public String getProduct( @RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu, Model model) throws Exception {
		
		System.out.println("상품 상세 화면");
		
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		model.addAttribute("menu", menu);
		
		String resultView = "forward:/product/getProduct.jsp";
		
		if(menu.equals("manage")) {
			resultView = "forward:/product/updateProduct.jsp";
		}
		
		
		return resultView;
	}
	
	
	
	@RequestMapping( value="updateProduct", method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product) throws Exception {
		
		System.out.println("상품 수정");
		
		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?menu=ok&prodNo=" + product.getProdNo();
	}

}
