package com.lpy.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.entity.MethodParameter;
import com.lpy.entity.MethodParameterModel;
import com.lpy.entity.MethodResult;
import com.lpy.entity.MethodResultModel;
import com.lpy.service.BeanService;

import net.sf.json.JSONArray;

@Controller
public class BeanController {

	@Resource
	private BeanService beanService;


	/*
	 * 信息搜索显示
	 */
	@RequestMapping("/showConfig.do")
	public ModelAndView select(BeanTypeConfig beanTypeConfig, BeanMethodConfig beanMethodConfig, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beanTypeConfig", beanTypeConfig);
		map.put("beanMethodConfig", beanMethodConfig);

		List<BeanTypeConfig> list = beanService.getAllConfig(map);
		model.addAttribute("list", list);

		return new ModelAndView("showConfig");
	}

	/*
	 * 详细信息显示
	 */
	@RequestMapping("/showAllConfig")
	public String show(String serviceId, Model model) {
		BeanTypeConfig beanTypeConfig = beanService.showAllConfig(serviceId);
		if (beanTypeConfig != null) {

			model.addAttribute("beanTypeConfig", beanTypeConfig);
			
		} else {
			BeanTypeConfig beanTypeConfig2 = beanService.getConfigs(serviceId);
			
			model.addAttribute("beanTypeConfig", beanTypeConfig2);
			
			
		}
		return "showAllConfig";
	}

	/*
	 * 跳转到信息添加页面
	 */
	@RequestMapping("/add.do")
	public String add(Model model) {

		model.addAttribute("beanMethodConfig", beanService.autoCreateServiceId());

		return "addConfig";
	}

	/*
	 * 信息添加
	 */
	@RequestMapping("/addConfig.do")
	public String addConfig(BeanTypeConfig beanTypeConfig, BeanMethodConfig beanMethodConfig,
			String platformName, String beanName, String methodName, Model model,
			MethodParameterModel methodParameterModel, MethodResultModel methodResultModel,
			HttpServletResponse response) throws IOException {
//
		List<MethodParameter> methodParameterList = methodParameterModel.getMethodParameters();
		List<MethodResult> methodResultList = methodResultModel.getMethodResults();
		// System.out.println(methodParameterList);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("beanTypeConfig", beanTypeConfig);
		map.put("beanMethodConfig", beanMethodConfig);
		map.put("methodParameters", methodParameterList);
		map.put("methodResults", methodResultList);
		
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		if (beanService.insertAllConfig(map) != 0) {
			response.getWriter().write("<script language='javascript'>alert('添加成功');window.close();</script>");
			// window.location.href='http://baidu.com';
			return null;
		} else {
			model.addAttribute("platformName", platformName);
			model.addAttribute("beanName", beanName);
			model.addAttribute("methodName", methodName);
			beanMethodConfig.setServiceId(beanService.autoCreateServiceId().getServiceId());
			//model.addAttribute("beanMethodConfig", beanMethodConfig);
			return "addConfig";
		}
	}

	@RequestMapping("/go.do")
	public String go() {
		return "addByXML";
	}

	
	/*
	 * 通过xml文件添加
	 */
	@RequestMapping("addConfigByBatch.do")
	public String addConfigByBatch(@RequestParam(value = "file") CommonsMultipartFile file, String platformName,String creator,HttpServletResponse response) throws IOException {
		// HttpServletRequest request
		File file3 = new File("classpath:\files\test.xml");

		try {
			file.transferTo(file3);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(creator);
			
		beanService.insertByBatch(file3, platformName);
			
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("<script language='javascript'>alert('添加成功');window.close();</script>");
		
		return null;
	}

	/*
	 * 输入信息检验
	 */
	@RequestMapping("/problem.do")
	public void doProblem(BeanMethodConfig beanMethodConfig, HttpServletResponse response) throws IOException {
		int num = beanService.problems(beanMethodConfig);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		switch (num) {
		case 1:
			response.getWriter().print("该平台已存在");
			break;
		case 2:
			response.getWriter().print("该“平台+bean名称”组合已存在");
			break;
		case 3:
			response.getWriter().print("该服务ID已存在");
			break;
		}
	}
	
	@RequestMapping("/del.do")
	public String del(String serviceId){
//		System.out.println(serviceId);
		int i = beanService.deleteByPrimaryKey(serviceId);
//		System.out.println(i);
		return "redirect:showConfig.do";
	}
	
	@RequestMapping("/methodName")
	public void getMethodName(String methodName,HttpServletResponse response) throws IOException{
		List<String> list =beanService.selectMethodName(methodName);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONArray.fromObject(list));
				
	}
	
	@RequestMapping("/beanName")
	public void getBeanName(String beanName,HttpServletResponse response) throws IOException{
		List<String> list =beanService.selectBeanName(beanName);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONArray.fromObject(list));
				
	}
	
	@RequestMapping("/serviceId")
	public void getServiceId(String serviceId,HttpServletResponse response) throws IOException{
		List<String> list =beanService.selectServiceId(serviceId);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSONArray.fromObject(list));
				
	}
	
	@RequestMapping("/a.do")
	public String test(){
		return "test";
	}
	
	@RequestMapping("b.do")
	public String test1(){
		return "test1";
	}

}
