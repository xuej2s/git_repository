package com.lpy.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AcBizLock extends AcBizLockKey {
    private Date gmtCreated;

    private Date gmtModified;

    private String bizId;

    private String bizType;

    private String lockType;

    private Date lockedTime;

    private String masterLockId;

    private String gmtCreatedStr;

    private String gmtModifiedStr;

    private String lockedTimeStr;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    
    public String getGmtCreatedStr() {
		return gmtCreatedStr;
	}

	public void setGmtCreatedStr(Date gmtCreated) {
		String gmtCreatedStr = simpleDateFormat.format(gmtCreated);
		this.gmtCreatedStr = gmtCreatedStr;
	}

	public String getGmtModifiedStr() {
		return gmtModifiedStr;
	}

	public void setGmtModifiedStr(Date gmtModified) {
		String gmtModifiedStr = simpleDateFormat.format(gmtModified);
		this.gmtModifiedStr = gmtModifiedStr;
	}

	public String getLockedTimeStr() {
		return lockedTimeStr;
	}

	public void setLockedTimeStr(Date lockedTime) {
		String lockedTimeStr = simpleDateFormat.format(lockedTime);
		this.lockedTimeStr = lockedTimeStr;
	}

	public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType == null ? null : lockType.trim();
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getMasterLockId() {
        return masterLockId;
    }

    public void setMasterLockId(String masterLockId) {
        this.masterLockId = masterLockId == null ? null : masterLockId.trim();
    }
}