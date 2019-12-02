package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ Controller
@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method ���� ����
		
	public UserController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
//	@RequestMapping( value="addUser", method=RequestMethod.GET )
//	public String addUser() throws Exception{
//	
//		System.out.println("/user/addUser : GET");
//		
//		return "redirect:/user/addUserView.jsp";
//	}
	
	@RequestMapping( value="addUser", method=RequestMethod.GET)
	public ModelAndView addUser() throws Exception {
		
		System.out.println("ȸ�� ���� ȭ��");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/user/addUserView.jsp");
		
		return mv;
		
	}
	
	
//	@RequestMapping( value="addUser", method=RequestMethod.POST )
//	public String addUser( @ModelAttribute("user") User user ) throws Exception {
//
//		System.out.println("/user/addUser : POST");
//		//Business Logic
//		userService.addUser(user);
//		
//		return "redirect:/user/loginView.jsp";
//	}
	
	
	@RequestMapping( value="addUser", method=RequestMethod.POST)
	public ModelAndView addUser( @ModelAttribute("user") User user ) throws Exception {
		
		System.out.println("ȸ�� ���� ����");
		
		userService.addUser(user);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/user/loginView.jsp");
		
		return mv;
	}
	

//	@RequestMapping( value="getUser", method=RequestMethod.GET )
//	public String getUser( @RequestParam("userId") String userId , Model model ) throws Exception {
//		
//		System.out.println("/user/getUser : GET");
//		//Business Logic
//		User user = userService.getUser(userId);
//		// Model �� View ����
//		model.addAttribute("user", user);
//		
//		return "forward:/user/getUser.jsp";
//	}
	
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public ModelAndView getUser( @RequestParam("userId") String userId) throws Exception {
		
		System.out.println("ȸ�� ���� ����");
		
		User user = userService.getUser(userId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("/user/getUser.jsp");
		
		return mv;
	}
	

//	@RequestMapping( value="updateUser", method=RequestMethod.GET )
//	public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{
//
//		System.out.println("/user/updateUser : GET");
//		//Business Logic
//		User user = userService.getUser(userId);
//		// Model �� View ����
//		model.addAttribute("user", user);
//		
//		return "forward:/user/updateUser.jsp";
//	}
	
	@RequestMapping( value="updateUser", method=RequestMethod.GET)
	public ModelAndView updateUser( @RequestParam("userId") String userId ) throws Exception {
		
		System.out.println("ȸ�� ���� ���� ȭ��");
		
		User user = userService.getUser(userId);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("/user/updateUser.jsp");
		
		return mv;
	}

//	@RequestMapping( value="updateUser", method=RequestMethod.POST )
////	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{
//	public String updateUser( @ModelAttribute("user") User user , HttpSession session) throws Exception{
//
//		System.out.println("/user/updateUser : POST");
//		//Business Logic
//		userService.updateUser(user);
//		
//		String sessionId=((User)session.getAttribute("user")).getUserId();
//		if(sessionId.equals(user.getUserId())){
//			session.setAttribute("user", user);
//		}
//		
//		return "redirect:/user/getUser?userId="+user.getUserId();
//	}
	
	@RequestMapping( value="updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser( @ModelAttribute("user") User user, ModelAndView mv, HttpSession session) throws Exception{
		
		System.out.println("ȸ�� ���� ���� ����");
		
		userService.updateUser(user);
		
		String sessionId = ((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}
		mv.setViewName("redirect:/user/getUser?userId=" + user.getUserId());
		
		return mv;
		
	}
	
	
//	@RequestMapping( value="login", method=RequestMethod.GET )
//	public String login() throws Exception{
//		
//		System.out.println("/user/logon : GET");
//
//		return "redirect:/user/loginView.jsp";
//	}
	
	
	@RequestMapping( value="login", method=RequestMethod.GET)
	public ModelAndView login(ModelAndView mv) throws Exception {
		System.out.println("�α��� ȭ��");
		
		mv.setViewName("redirect:/user/loginView.jsp");
		
		return mv;
	}
	
//	@RequestMapping( value="login", method=RequestMethod.POST )
//	public String login(@ModelAttribute("user") User user , HttpSession session ) throws Exception{
//		
//		System.out.println("/user/login : POST");
//		//Business Logic
//		User dbUser=userService.getUser(user.getUserId());
//		
//		if( user.getPassword().equals(dbUser.getPassword())){
//			session.setAttribute("user", dbUser);
//		}
//		
//		return "redirect:/index.jsp";
//	}
	
	@RequestMapping( value="login", method=RequestMethod.POST)
	public ModelAndView login( @ModelAttribute("user") User user, HttpSession session) throws Exception {
		
		System.out.println("�α��� ����");
		
		User dbUser = userService.getUser(user.getUserId());
		
		if(user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/index.jsp");
		
		return mv;
	}
		
	
//	@RequestMapping( value="logout", method=RequestMethod.GET )
//	public String logout(HttpSession session ) throws Exception{
//		
//		System.out.println("/user/logout : POST");
//		
//		session.invalidate();
//		
//		return "redirect:/index.jsp";
//	}
	
	@RequestMapping( value="logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session,  ModelAndView mv) throws Exception {
		System.out.println("�α׾ƿ� ����");
		
		session.invalidate();
		
		mv.setViewName("redirect:/index.jsp");
		
		return mv;
	}
	
	
//	@RequestMapping( value="checkDuplication", method=RequestMethod.POST )
//	public String checkDuplication( @RequestParam("userId") String userId , Model model ) throws Exception{
//		
//		System.out.println("/user/checkDuplication : POST");
//		//Business Logic
//		boolean result=userService.checkDuplication(userId);
//		// Model �� View ����
//		model.addAttribute("result", new Boolean(result));
//		model.addAttribute("userId", userId);
//
//		return "forward:/user/checkDuplication.jsp";
//	}
	
	
	@RequestMapping( value="checkDuplication", method=RequestMethod.POST)
	public ModelAndView checkDuplication( @RequestParam("userId") String userId, ModelAndView mv ) throws Exception {
		
		System.out.println("ȸ�� ���̵� �ߺ� �˻� ����");
		
		boolean result = userService.checkDuplication(userId);
		
		mv.addObject("result", result);
		mv.addObject("userId", userId);
		mv.setViewName("/user/checkDuplication.jsp");
		
		return mv;
	}

	
//	@RequestMapping( value="listUser" )
//	public String listUser( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
//		
//		System.out.println("/user/listUser : GET / POST");
//		
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//		
//		// Business logic ����
//		Map<String , Object> map=userService.getUserList(search);
//		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//		
//		// Model �� View ����
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//		
//		return "forward:/user/listUser.jsp";
//	}
	
	
	@RequestMapping( value="listUser" )
	public ModelAndView listUser( @ModelAttribute("search") Search search, ModelAndView mv) throws Exception {
		System.out.println("ȸ�� ����Ʈ");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = userService.getUserList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("���� ������: " + resultPage);
		
		mv.addObject("list", map.get("list"));
		mv.addObject("resultPage", resultPage);
		mv.addObject("search", search);
		mv.setViewName("/user/listUser.jsp");
		
		return mv;
	}
}