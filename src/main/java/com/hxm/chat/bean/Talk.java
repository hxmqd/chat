package com.hxm.chat.bean;

import java.sql.Date;

public class Talk {
 
    private String id;
    
    private String srcUser;

    private String destUser;

    private String content;
    
    private	Date time;
    
    private Integer type;
    
    public Talk(){
    	
    }
    public Talk(String id, String srcUser, String destUser, String content, int type){
    	this.id = id;
    	this.srcUser = srcUser;
    	this.destUser = destUser;
    	this.content = content;
    	this.type = type;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser == null ? null : srcUser.trim();
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser == null ? null : destUser.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}