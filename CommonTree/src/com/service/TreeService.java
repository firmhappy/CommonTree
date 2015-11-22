package com.service;

import java.util.List;

public interface TreeService {
	public List getChildren(Object vo);
	public Object addEntity(Object vo);
	public Object updateEntity(Object vo);
}
