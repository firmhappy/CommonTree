package com.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.CommonDAO;
import com.db.HibernateSessionFactory;

public class TreeServiceImpl implements TreeService {

	@Override
	public List getChildren(Object vo) {
		List list = new ArrayList<Object>();
		Object po=this.voToPo(vo,true);
		Session session=HibernateSessionFactory.getSession();
		CommonDAO cdao=new CommonDAO();
		list=cdao.getChildren(session, po);
		session.close();
		return list;
	}
	
	public Object addEntity(Object vo){
		Session session=HibernateSessionFactory.getSession();
		CommonDAO cdao=new CommonDAO();
		Transaction transaction=session.beginTransaction();
		Object po=cdao.addEntity(session, this.voToPo(vo,true));
		if(po==null){
			transaction.rollback();
			session.close();
			return null;
		}else{
			transaction.commit();
			vo=this.voToPo(po, false);
			session.close();
			return vo;
		}
	}
	
	public Object updateEntity(Object vo){
		Object po=this.voToPo(vo, true);
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		CommonDAO cdao=new CommonDAO();
		po=cdao.updateEntity(session, po);
		if(po==null){
			transaction.rollback();
			session.close();
			return null;
		}else{
			vo=this.voToPo(po, false);
			transaction.commit();
			session.close();
			return vo;
		}
	}

	/**
	 * 
	 * @param vo
	 * @param f f==true:VO到PO；f==false:PO到VO
	 * @return
	 */
	private Object voToPo(Object vo,boolean f) {
		String voname = vo.getClass().getName();
		String poname =f? voname.substring(0, voname.length() - 2):voname+"Vo";
		try {
			Object po = Class.forName(poname).newInstance();
			Field[] vfs = vo.getClass().getDeclaredFields();
			for (Field field : vfs) {
				field.setAccessible(true);
				try {
					Field pof = po.getClass().getDeclaredField(field.getName());
					pof.setAccessible(true);
					pof.set(po, field.get(vo));
				} catch (SecurityException e) {
					System.out.println("无法读取PO属性:" + field.getName());
					e.printStackTrace();
					continue;
				} catch (NoSuchFieldException e) {
					System.out.println("PO中不存在" + field.getName());
					e.printStackTrace();
					continue;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return po;
		} catch (ClassNotFoundException e) {
			System.out.println("没有 " + poname + " PO");
			e.printStackTrace();
			return null;
		} catch (InstantiationException e1) {
			System.out.println("PO初始化失败");
			e1.printStackTrace();
			return null;
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

}
