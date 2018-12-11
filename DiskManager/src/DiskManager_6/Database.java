package DiskManager_6;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class Database implements DiskManagerBean{

	public Connection getConnect()
	{
	//	String database="/home/zhouyangwit/workspace/DiskManager/libs/DiskManager.db";
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
	
	public boolean add(File f)
	{
		boolean flag=false;
		String filename=f.getName();
		long length=f.length();
		long hashcode=f.hashCode();
		String MD5=new getMD5().getNormalFileMD5(f);
		String lastmodified=sdf.format(new Date(f.lastModified()));
		String path=f.getAbsolutePath();
		
		Connection conn=getConnect();
		Statement stmt=null;
		String sql=null;
		if(!query_md5(MD5))
		{
			if(!query_hashcode(hashcode))
			{
				sql="insert into FileInfo(fileName,length,hashcode,MD5,lastmodified,path,count) values('"+filename+"',"+length+","+hashcode+",'"+MD5+"','"+lastmodified+"','"+path+"',0)";
		
			}
			else
				sql="update FileInfo set count=count+1,md5='"+MD5+"' where hashcode="+hashcode;
		}
		else
		{
			if(!query_hashcode(hashcode))
			{
				sql="update FileInfo set count=count+1,hashcode="+hashcode+" where md5='"+MD5+"'";
			}
			else
			{
				f.delete();
			}
		}
		
		
		try {
				stmt=conn.createStatement();
				stmt.execute(sql);
				flag=true;
	//			System.out.println(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	try {
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	return flag;
		
	}
	
	public boolean query_filename_length(String filename,long length)
	{
		boolean flag=false;
		Connection conn=getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from FileInfo where filename='"+filename+"' and length="+length;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean query_hashcode(long hashcode)
	{
		boolean flag=false;
		Connection conn=getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from FileInfo where hashcode like '%"+hashcode+"%';";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				flag=true;
		//		System.out.println("原文件位置："+rs.getString("path"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return flag;
	}
	
	public boolean query_md5(String md5)
	{
		boolean flag=false;
		Connection conn=getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from FileInfo where md5='"+md5+"';";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				flag=true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return flag;
	}

	public boolean update(File file)
	{
		boolean flag=false;
		String path=file.getAbsolutePath();
		String md5=new getMD5().getNormalFileMD5(file);
		long hashcode=file.hashCode();
		Connection conn=getConnect();
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
			if(query_hashcode(hashcode))
			{
				if(query_md5(md5))
				{
					stmt.executeUpdate("update fileinfo set path=\""+path+"\" where hashcode="+hashcode);
				}
				else
					stmt.executeUpdate("update fileinfo set path=\""+path+"\",md5='"+md5+"' where hashcode="+hashcode);
			}
			else
			{
				if(query_md5(md5))
				{
					stmt.executeUpdate("update fileinfo set path=\""+path+"\",hashcode="+hashcode+" where md5='"+md5+"'");
				}
				else
				{
					add(file);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public void getFileNumber(File file) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(String sql)
	{
		Connection conn=getConnect();
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
}
