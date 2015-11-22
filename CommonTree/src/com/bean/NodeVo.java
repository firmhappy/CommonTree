package com.bean;

public class NodeVo {
	private long id;
	private String name;
	private String other;
	private String path;
	private long pid;
	private String sex;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public NodeVo(long id, String name, String other, String path, long pid,
			String sex) {
		super();
		this.id = id;
		this.name = name;
		this.other = other;
		this.path = path;
		this.pid = pid;
		this.sex = sex;
	}
	public NodeVo() {
		super();
	}
	

}
