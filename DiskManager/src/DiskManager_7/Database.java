package DiskManager_7;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Database implements DiskManagerBean {

	String database="E:\\DiskManager\\DiskManager.db";
	
	public Connection getConnect()
	{
		Connection conn=null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:"+database);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
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

	public boolean add(File file)
	{
		boolean flag=false;
		String filename=file.getName();
		String path=file.getAbsolutePath();
		long length=file.length();
		int hashcode=file.hashCode();
		String lastmodified=sdf.format(new Date(file.lastModified()));
		String md5=new getMD5().getBigFileMD5(file);
		String sql=null;
		if(!query_md5(md5))
		{
			sql="insert into FileInfo(fileName,length,hashcode,MD5,lastmodified,path,count) values('"+filename+"',"+length+","+hashcode+",'"+md5+"','"+lastmodified+"','"+path+"',0)";
		}
		else {
			sql="update fileinfo set count=count+1 where md5='"+md5+"'";
		}
		
		Connection connection=getConnect();
		Statement stmt=null;
		try {
			stmt=connection.createStatement();
			stmt.execute(sql);
			flag=true;
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return flag;
	}

	
}
