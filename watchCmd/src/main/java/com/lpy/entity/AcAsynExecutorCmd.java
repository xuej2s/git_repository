package com.lpy.entity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AcAsynExecutorCmd extends AcAsynExecutorCmdKey {
   
	private Date gmtCreate;

    private String creator;

    private Date gmtModify;

    private String modifier;

    private String status;

    private String commandType;

    private Integer executeTimes;

    private Date nextExecuteTime;

    private String businessNo;

    private String context;

    private String hostname;

    private String eventContext;

    private String traceId;

    private String logEnv;
    
    private String gmtCreateStr;
    
    private String nextExecuteTimeStr;
    
    private String gmtModifyStr;
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    
    public String getGmtModifyStr() {
		return gmtModifyStr;
	}

	public void setGmtModifyStr(Date gmtModify) {
		String gmtModifyStr = simpleDateFormat.format(gmtModify);
		this.gmtModifyStr = gmtModifyStr;
	}

    
    public String getGmtCreateStr() {
		return gmtCreateStr;
	}

	public void setGmtCreateStr(Date gmtCreate) {
		
		String gmtCreateStr = simpleDateFormat.format(gmtCreate);
		this.gmtCreateStr = gmtCreateStr;
	}

	public String getNextExecuteTimeStr() {
		return nextExecuteTimeStr;
	}

	public void setNextExecuteTimeStr(Date nextExecuteTime) {
		String nextExecuteTimeStr = simpleDateFormat.format(nextExecuteTime);
		this.nextExecuteTimeStr = nextExecuteTimeStr;
	}

	public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType == null ? null : commandType.trim();
    }

    public Integer getExecuteTimes() {
        return executeTimes;
    }

    public void setExecuteTimes(Integer executeTimes) {
        this.executeTimes = executeTimes;
    }

    public Date getNextExecuteTime() {
        return nextExecuteTime;
    }

    public void setNextExecuteTime(Date nextExecuteTime) {
        this.nextExecuteTime = nextExecuteTime;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public String getEventContext() {
        return eventContext;
    }

    public void setEventContext(String eventContext) {
        this.eventContext = eventContext == null ? null : eventContext.trim();
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId == null ? null : traceId.trim();
    }

    public String getLogEnv() {
        return logEnv;
    }

    public void setLogEnv(String logEnv) {
        this.logEnv = logEnv == null ? null : logEnv.trim();
    }
}