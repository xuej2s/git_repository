package com.lpy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lpy.dao.AcAsynExecutorCmdDao;
import com.lpy.dao.AcBizLockDao;
import com.lpy.entity.AcAsynExecutorCmd;
import com.lpy.entity.AcBizLock;
import com.lpy.service.WatchService;

@Service("watchService")
public class WatchServiceImpl implements WatchService {

	@Resource
	private AcAsynExecutorCmdDao acAsynExecutorCmdDao;
	
	@Resource
	private AcBizLockDao acBizLockDao;
	
	public List<AcAsynExecutorCmd> selectAllConfig() {
		// TODO Auto-generated method stub
		return acAsynExecutorCmdDao.selectAllConfig();
	}

	public List<AcBizLock> getAllConfig() {
		// TODO Auto-generated method stub
		return acBizLockDao.selectAllConfig();
	}

}
