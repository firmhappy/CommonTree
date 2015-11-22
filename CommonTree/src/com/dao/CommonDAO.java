package com.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CommonDAO {
	public int getChildrenCount(Session session,String classname,long id){
		int count=0;
		String hql="select count(*) from %s where pid=:pid";
		Query query=session.createQuery(String.format(hql, classname));
		query.setParameter("pid", id);
		count=Integer.parseInt(query.uniqueResult().toString());
		return count;
	}
	
	public List getChildren(Session session,Object po,int page,int pagesize){
		List list=new ArrayList<Object>();
		String hql="from %s where pid=:pid order by id";
		String name=po.getClass().getName();
		try {
			Field pidf = po.getClass().getDeclaredField("pid");
			pidf.setAccessible(true);
			Object pid=pidf.get(po);
			Query query=session.createQuery(String.format(hql, name));
			query.setParameter("pid", pid);
			query.setFirstResult((page-1)*pagesize);
			query.setMaxResults(pagesize);
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
	
	public Object addEntity(Session session,Object po){
		session.save(po);
		return po;
	}
	
	public Object updateEntity(Session session,Object po){
		session.update(po);
		try {
			Field idf=po.getClass().getDeclaredField("id");
			idf.setAccessible(true);
			po=session.get(po.getClass(), Long.parseLong(idf.get(po).toString()));
			return po;
		} catch (SecurityException e) {
			System.out.println("无法访问id属性");
			e.printStackTrace();
			return null;
		} catch (NoSuchFieldException e) {
			System.out.println("PO中缺少id属性");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	

}
