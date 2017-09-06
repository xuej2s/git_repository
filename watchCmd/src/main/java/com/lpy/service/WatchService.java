package com.lpy.service;

import java.util.List;

import com.lpy.entity.AcAsynExecutorCmd;
import com.lpy.entity.AcBizLock;

public interface WatchService {

	List<AcAsynExecutorCmd> selectAllConfig();
	
	List<AcBizLock> getAllConfig();
}
