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
			System.out.println("PO��û�д�����");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("�޷�����PO����");
			e.printStackTrace();
		}
		return list;
	}
	
	public Object addEntity(Session session,Object po){
		session.save(po);
		try {
			Field idf=po.getClass().getDeclaredField("id");
			idf.setAccessible(true);
			idf.set(po, idf.get(po));
		} catch (SecurityException e) {
			System.out.println("�޷�����id����");
			e.printStackTrace();
			return null;
		} catch (NoSuchFieldException e) {
			System.out.println("PO��û��id����");
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
			System.out.println("�޷�����id����");
			e.printStackTrace();
			return null;
		} catch (NoSuchFieldException e) {
			System.out.println("PO��ȱ��id����");
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
