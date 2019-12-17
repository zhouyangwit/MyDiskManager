package Inspiration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JLabel;


public class Database{

	boolean flag=false;
	String database=null;
	public void getDatabase()
	{
		JFileChooser jfc=new JFileChooser("c:\\Users\\周洋\\Desktop\\");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showDialog(new JLabel(),"选择");
		File file=jfc.getSelectedFile();
		database=file.getAbsolutePath();
		flag=true;
	}
	

	public Connection getConnect()
	{
		if(!flag)
		{
			getDatabase();
		}
		Connection conn=null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:"+database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			flag=true;
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public boolean add(String filename,long length,String md5,String lastmodified,String path,String exif)
	{
		boolean flag=false;
		Connection conn=getConnect();
		Statement stmt=null;
		String sql=null;
		sql="insert into FileInfo(fileName,length,MD5,lastmodified,path,count,exif) values('"+filename+"',"+length+",'"+md5+"','"+lastmodified+"','"+path+"',0,'"+exif+"');";
		try {
				stmt=conn.createStatement();
				stmt.execute(sql);
				flag=true;
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
	
	public boolean query_filename_length(String filename)
	{
		boolean flag=false;
		Connection conn=getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from FileInfo where filename like '%"+filename+"%' ;";
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
	

	public void update(String sql)
	{
		Connection conn=getConnect();
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
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
	
	}
	

}
