package com.oracle.strutsdemo.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.oracle.strutsdemo.dao.DrDAOImp;
import com.oracle.strutsdemo.dao.DrDao;
import com.oracle.strutsdemo.model.bean.Dr;
import com.oracle.strutsdemo.util.Responser;

public class DrAction {
	private Dr  dr;
	
	public Dr getDr() {
		return dr;
	}
	public void setCar(Dr dr) {
		this.dr = dr;
	}
	private DrDao dao;
	public DrAction() {
		dao=new DrDAOImp();
	}
	private int page;
	private int rows;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 分页显示查询戒指信息的方法
	 */
	public void listCarsByPage() {
		ArrayList<Dr> drs=dao.listDrByPage(page, rows);
		JSONArray  js=new JSONArray();
		for(Dr c:drs) {
			try {
				JSONObject  j=new JSONObject();
				j.put("drId", c.getDrId());
				j.put("drBrand", c.getDrBrand());
				j.put("drSeries", c.getDrSeries());
				j.put("drPrice", c.getDrPrice());
				j.put("drSales", c.getDrSales());
				j.put("drSize", c.getDrSize());
				j.put("drNumber", c.getDrNumber());
				j.put("drWeight", c.getDrWeight());
				j.put("drCleanliness", c.getDrCleanliness());
				j.put("drColor", c.getDrColor());
				j.put("drCutting", c.getDrCutting());
				j.put("drMaterial", c.getDrMaterial());
				j.put("DRFirstPicture", "<img src='"+c.getDRFirstPicture()+"'  style='width:20px;height:20px'/>");
				js.put(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//System.out.println(js.toString());
		try {
			Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), js.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据id删除车辆信息的方法
	 */
	public void deleteCarInfoByCarId() {
		boolean result=dao.delete(dr.getDrId());
		System.out.println(dr.getDrId());
		System.out.println("delete result:"+result);
		try {
			Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), result+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
