package com.oracle.strutsdemo.dao;

import com.oracle.strutsdemo.model.bean.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.oracle.strutsdemo.model.bean.User;

public class UserDAOImp extends BaseDAOImp implements UserDAO {
	private User u=null;	

	//用户注册
	public boolean add(Object o) {
		User user=(User)o;
		if(user.getShouJiHao()!=null) {
			return mobile_reg(user);
		}
		if(user.getYouXiang()!=null) {
			return email_reg(user);
		}
		System.out.println("没有获取到注册用户信息，userdaoimp");
		return false;
	}

	@Override
	public boolean delete(Object id) {
		return false;
	}

	@Override
	public boolean update(Object o) {
		User user=(User)o;
		PreparedStatement  sta=null;
		boolean result=false;
		try {
			sta=getPreSta("update usermessage set touxiang=? ,youxiang=? ,nicheng=?,zhenshixingming=?,xingbie=?,chusheng_nian=?,chusheng_yue=?,chusheng_ri=?,diqu_sheng=?,diqu_shi=?,diqu_xian=?,xiangxidizhi=?,youbian=?,shoujihao=?,zuoji=? where userid=?");
			sta.setString(1, user.getTouXiang());
			sta.setString(2, user.getYouXiang());
			sta.setString(3, user.getNiCheng());
			sta.setString(4, user.getZhenShiXingMing());
			sta.setString(5, user.getXingBie());
			sta.setInt(6, user.getNian());
			sta.setInt(7, user.getYue());
			sta.setInt(8, user.getRi());
			sta.setString(9, user.getSheng());
			sta.setString(10, user.getShi());
			sta.setString(11, user.getXian());
			sta.setString(12, user.getXiangXiDiZhi());
			sta.setInt(13, user.getYouBian());
			sta.setString(14, user.getShouJiHao());
			sta.setLong(15, user.getZuoJi());		
			sta.setInt(16, user.getUserid());
			int count=sta.executeUpdate();
			result=(count>0)?true:false;		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Object list() {
		return null;
	}

	@Override
	public User login(String shouJiHao, String password) {
		PreparedStatement  sta=null;
		ResultSet rs=null;
		try {
			sta=getPreSta("select *  from usermessage  where  shoujihao=? and password=?");
			sta.setString(1, shouJiHao);
			sta.setString(2, password);
			
			rs=sta.executeQuery();
			//System.out.println(shouJiHao);
			//System.out.println(password);
			while(rs.next()) {
				//System.out.println(rs.getInt("USERID"));
				u=parsetResultToUser(rs);
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return u;
	}
	/**
	 * 手机号注册
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean mobile_reg(User u) {
		boolean result=false;
		try {
			int count=getSta().executeUpdate("insert into  usermessage(userid,shoujihao,password) values (null,'"+u.getShouJiHao()+"','"+u.getPassword()+"')");
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 邮箱注册
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean email_reg(User u) {
		boolean result=false;
		try {
			int count=getSta().executeUpdate("insert into  usermessage(userid,youxiang,password) values (null,'"+u.getYouXiang()+"','"+u.getPassword()+"')");
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * 作为一个工具类，供其他类调用
	 * 这个方法用来处理查询数据库后的结果集
	 */
	public User parsetResultToUser(ResultSet rs) {
		try { 
			u=new User();
			u.setUserid(rs.getInt("USERID"));
			u.setPassword(rs.getString("PASSWORD"));
			u.setTouXiang(rs.getString("TOUXIANG"));
			u.setYouXiang(rs.getString("YOUXIANG"));
			u.setNiCheng(rs.getString("NICHENG"));
			u.setZhenShiXingMing(rs.getString("ZHENSHIXINGMING"));
			u.setXingBie(rs.getString("XINGBIE"));
			u.setNian(rs.getInt("CHUSHENG_NIAN"));
			u.setYue(rs.getInt("CHUSHENG_YUE"));
			u.setRi(rs.getInt("CHUSHENG_RI"));
			u.setSheng(rs.getString("DIQU_SHENG"));
			u.setShi(rs.getString("DIQU_SHI"));
			u.setXian(rs.getString("DIQU_XIAN"));
			u.setXiangXiDiZhi(rs.getString("XIANGXIDIZHI"));
			u.setYouBian(rs.getInt("YOUBIAN"));
			u.setShouJiHao(rs.getString("SHOUJIHAO"));
			u.setZuoJi(rs.getLong("ZUOJI"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public User getUserInfoByUserId(int userid) {
		User  user=null;
		PreparedStatement  sta=null;
		ResultSet rs=null;
		try {
			  sta=getPreSta("select *  from usermessage where userid=?");
			sta.setInt(1, userid);
			rs=sta.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setShouJiHao(rs.getString("shouJiHao"));
				user.setPassword(rs.getString("password"));
				if(rs.getString("image")!=null)
				{
					u=new User();
					u.setUserid(rs.getInt("USERID"));
					u.setPassword(rs.getString("PASSWORD"));
					u.setTouXiang(rs.getString("TOUXIANG"));
					u.setYouXiang(rs.getString("YOUXIANG"));
					u.setNiCheng(rs.getString("NICHENG"));
					u.setZhenShiXingMing(rs.getString("ZHENSHIXINGMING"));
					u.setXingBie(rs.getString("XINGBIE"));
					u.setNian(rs.getInt("CHUSHENG_NIAN"));
					u.setYue(rs.getInt("CHUSHENG_YUE"));
					u.setRi(rs.getInt("CHUSHENG_RI"));
					u.setSheng(rs.getString("DIQU_SHENG"));
					u.setShi(rs.getString("DIQU_SHI"));
					u.setXian(rs.getString("DIQU_XIAN"));
					u.setXiangXiDiZhi(rs.getString("XIANGXIDIZHI"));
					u.setYouBian(rs.getInt("YOUBIAN"));
					u.setShouJiHao(rs.getString("SHOUJIHAO"));
					u.setZuoJi(rs.getLong("ZUOJI"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;	
		}

	
	@Override
	public boolean checkUserExsit(String shoujihao) {
		boolean result=false;
		Statement  sta=null;
		ResultSet  rs=null;
		try {
			sta=getSta();
			  rs=sta.executeQuery("select  count(userid)  from  usermessage where shoujihao='"+shoujihao+"'");
			rs.next();
			result=(rs.getInt(1)>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return result;
	}

	@Override
	public ArrayList<User> listUserByPage(int count, int page) {
		ArrayList<User>  users=new ArrayList<>();
		PreparedStatement  sta=null;
		ResultSet rs=null;
		try {
			  sta=getPreSta("select *  from usermessage order by userid desc limit  ?,?");
			sta.setInt(1, (page-1)*count);
			sta.setInt(2, count);
			rs=sta.executeQuery();
			while(rs.next()) {
				User  user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setShouJiHao(rs.getString("shouJiHao"));
				user.setPassword(rs.getString("password"));
				if(rs.getString("image")!=null)
				{
					u=new User();
					u.setUserid(rs.getInt("USERID"));
					u.setPassword(rs.getString("PASSWORD"));
					u.setTouXiang(rs.getString("TOUXIANG"));
					u.setYouXiang(rs.getString("YOUXIANG"));
					u.setNiCheng(rs.getString("NICHENG"));
					u.setZhenShiXingMing(rs.getString("ZHENSHIXINGMING"));
					u.setXingBie(rs.getString("XINGBIE"));
					u.setNian(rs.getInt("CHUSHENG_NIAN"));
					u.setYue(rs.getInt("CHUSHENG_YUE"));
					u.setRi(rs.getInt("CHUSHENG_RI"));
					u.setSheng(rs.getString("DIQU_SHENG"));
					u.setShi(rs.getString("DIQU_SHI"));
					u.setXian(rs.getString("DIQU_XIAN"));
					u.setXiangXiDiZhi(rs.getString("XIANGXIDIZHI"));
					u.setYouBian(rs.getInt("YOUBIAN"));
					u.setShouJiHao(rs.getString("SHOUJIHAO"));
					u.setZuoJi(rs.getLong("ZUOJI"));
				}
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta,	rs);
		return users;
	}

	@Override
	public int getAllCountOfUser() {
		int count=0;
		Statement  sta=null;
		ResultSet  rs=null;
		try {
			sta=getSta();
			  rs=sta.executeQuery("select  count(userid)  from  usermessage");
			rs.next();
			count=rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return count;
	}
	
}
