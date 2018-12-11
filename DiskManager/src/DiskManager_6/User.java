package DiskManager_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class User {
	
	private Connection getConnect()
	{
		String database="/media/zhouyang/文档/DiskManager/UserData.db";
		Connection conn=null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:"+database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private boolean UserReg(String sql)
	{
		boolean isOK=true;
		Connection conn=null;
		conn=getConnect();
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
			isOK=stmt.execute(sql);
			System.out.println("Database Return Value: "+isOK);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isOK;
	}
	
	private boolean Query(String sql)
	{
		boolean flag=false;
		String active="gg";
//		System.out.println(sql);
		Connection conn=null;
		conn=getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
	//			System.out.println(rs.getString("active"));
				active=rs.getString("active");
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(active.equals("gg"))
		{
	//		System.out.println("username OR password ERROR , please try again later !");
			JOptionPane.showMessageDialog(null,"username OR password ERROR , please try again later !");
		}
		else if(active.equals("false"))
		{
			flag=false;
	//		System.out.println("Account is Frozen!");
			JOptionPane.showMessageDialog(null, "Accoutn is Frozen!");
		}
		
		return flag;
	}
	
	
	public void Reg(String username,String password)
	{
		String sql="insert into UserInfo values('"+username+"','"+password+"',datetime('now','+8 hour'));";
		UserReg(sql);
	}
	
	public void deleteUser(String username,String password)
	{
		String sql="delete from UserInfo where username='"+username+"' and password='"+password+"';";
		UserReg(sql);
	}
	
	public void FrozenUser(String username)
	{
		String sql="update from UserInfo set active=false where username='"+username+"';";
		UserReg(sql);
	}
	

	public void getReg()
	{
		System.out.println(UserReg("delete from userinfo where username='zhangsan';"));
	}
	
	public void Login(String username,String password)
	{
		String sql="select * from userinfo where username='"+username+"' and password='"+password+"';";
		boolean stats=Query(sql);
		if(stats)
		{
			System.out.println("Welcome !");
		}
		else
		{
			System.out.println("login error !");
		}
	}
	
	
	public static void main(String[] args)
	{
		new User().Login("zhangsan","123");
		System.out.println("DONE!");
	}
}
