package DiskManager;

/*
 *数据库设计：md5-主键(String)、filename(String)、length(int)、 lastmodified(datetime)、absolutepath(String)、relativepath(String)、recordtime(datetime)、repeat(int)
 * 
 * 
 */


import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.JDBC;

public class Database {

	public Connection getConnect(String database)
	{
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("JDBC:sqlite:"+database);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动加载异常");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接异常");
			e.printStackTrace();
		}			
		return connection;		
	}
	
	public void DatabaseUpdate(String database,String sql)
	{
		Connection connection=null;
		PreparedStatement pstmt=null;
		connection=getConnect(database);
		int num=0;
		try {
			pstmt=connection.prepareStatement(sql);
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			try {
				connection.rollback();
				System.out.println("数据更新出现错误，现已回滚至原始状态");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("本次操作影响【"+num+"】行数据");
	}
	
	
	public void DatabaseQuery(String database,String sql)
	{
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		connection=getConnect(database);
		try {
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("md5")+"，"+rs.getString("recordtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
	{
		new Database().DatabaseUpdate("D:\\MyDiskManager.db","insert into fileinfo (md5,recordtime) values ('demo',datetime(datetime(),'+8 hour'))");
		new Database().DatabaseQuery("D:\\MyDiskManager.db","select * from fileinfo");
		new Database().DatabaseUpdate("D:\\MyDiskManager.db","delete from fileinfo;");
	}
	
}
