package com.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CommonDAO {
	
	public List getChildren(Session session,Object po){
		List list=new ArrayList<Object>();
		String hql="from %s where pid=:pid order by id";
		String name=po.getClass().getName();
		Field pidf;
		try {
			pidf = po.getClass().getDeclaredField("pid");
			pidf.setAccessible(true);
			Object pid=pidf.get(po);
			Query query=session.createQuery(String.format(hql, name));
			query.setParameter("pid", pid);
			list=query.list();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("PO中没有此数据");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("无法访问PO数据");
			e.printStackTrace();
		}
		return list;
	}

}
