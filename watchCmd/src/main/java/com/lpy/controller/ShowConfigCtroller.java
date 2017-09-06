package com.lpy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lpy.entity.AcAsynExecutorCmd;
import com.lpy.entity.AcBizLock;
import com.lpy.service.WatchService;

@Controller
public class ShowConfigCtroller {
	
	@Resource
	private WatchService watchService;
	
	@RequestMapping("cmd.do")
	public String cmd(ModelMap map){
		
		
		List<AcAsynExecutorCmd> list = watchService.selectAllConfig();
		for (AcAsynExecutorCmd acAsynExecutorCmd : list) {
			acAsynExecutorCmd.setGmtCreateStr(acAsynExecutorCmd.getGmtCreate());
			acAsynExecutorCmd.setGmtModifyStr(acAsynExecutorCmd.getGmtModify());
			acAsynExecutorCmd.setNextExecuteTimeStr(acAsynExecutorCmd.getNextExecuteTime());
		}
		map.addAttribute("cmds", list);
		return "cmd";
	}
	
	@RequestMapping("lock.do")
	public String lock(ModelMap map){
		
		List<AcBizLock> list = watchService.getAllConfig();
		for (AcBizLock acBizLock : list) {
			acBizLock.setGmtCreatedStr(acBizLock.getGmtCreated());
			acBizLock.setGmtModifiedStr(acBizLock.getGmtModified());
			acBizLock.setLockedTimeStr(acBizLock.getLockedTime());
		}
		map.addAttribute("locks", list);
		return "lock";
	}
	
	
}
