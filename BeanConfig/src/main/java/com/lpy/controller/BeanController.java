package com.lpy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lpy.entity.BeanMethodConfig;
import com.lpy.entity.BeanTypeConfig;
import com.lpy.service.BeanService;

@Controller
public class BeanController {

	@Resource
	private BeanService beanService;

	@RequestMapping("/a.do")
	public String test() {
		System.out.println("----------");
		return "test";
	}

	@RequestMapping("/select.do")
	public String showConfig() {
		return "showConfig";
	}

	@RequestMapping("/showConfig.do")
	public ModelAndView select(BeanTypeConfig beanTypeConfig, BeanMethodConfig beanMethodConfig, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beanTypeConfig", beanTypeConfig);
		map.put("beanMethodConfig", beanMethodConfig);

		List<BeanTypeConfig> list = beanService.getAllConfig(map);
		model.addAttribute("list", list);

		return new ModelAndView("showConfig");
	}

	@RequestMapping("/add.do")
	public String add(Model model) {

		model.addAttribute("beanMethodConfig", beanService.autoCreateServiceId());

		return "addConfig";
	}

	@RequestMapping("/addConfig.do")
	public String addConfig(BeanTypeConfig beanTypeConfig, BeanMethodConfig beanMethodConfig) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beanTypeConfig", beanTypeConfig);
		map.put("beanMethodConfig", beanMethodConfig);
		try {
			if (beanService.insertConfig(map) != 0 && beanService.insertConfig(map) != 1) {
				return "showConfig";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		return null;
	}

}
