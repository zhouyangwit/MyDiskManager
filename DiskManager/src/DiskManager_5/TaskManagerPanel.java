package DiskManager_5;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class TaskManagerPanel extends JPanel implements ActionListener,DiskManagerBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2029007633031155233L;

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	JButton choosefolder=new JButton("选择文件");
	JButton start=new JButton("开始");
	JButton md5=new JButton("获取MD5");
	JButton hashcode=new JButton("获取hashcode");
	JButton open=new JButton("打开文件");
	JButton clean=new JButton("清除");
	JButton record=new JButton("记录");
	JButton query=new JButton("查询");
	JButton update=new JButton("更新");
	JButton deldir=new JButton("DelDir");
	
	JTextArea jtf=new JTextArea(1,70);
//	JTextArea jta=new JTextArea(16,51);
	JTextArea filelist=new JTextArea(21,35);
	JTextArea folderlist=new JTextArea(21,35);
//	JScrollPane jsp=new JScrollPane(jta);
	
	JScrollPane jsp_filelist=new JScrollPane(filelist);
	JScrollPane jsp_folderlist=new JScrollPane(folderlist);
	JScrollPane jsp_jtf=new JScrollPane(jtf);
	
	boolean b=false;
	int count=0;
	File startfile=new File("D:\\Pictures\\360");
	getMD5 md=new getMD5();
	Database d=new Database();
	
	public TaskManagerPanel()
	{
	
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.add(choosefolder);
		this.add(start);
		this.add(hashcode);
		this.add(md5);
		this.add(open);
		this.add(record);
		this.add(update);
		this.add(query);
		this.add(clean);
		this.add(deldir);
		
		this.add(jsp_folderlist);
		this.add(jsp_filelist);
		
		this.add(jsp_jtf);
//		this.add(jsp);
		jsp_filelist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp_filelist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		jsp_folderlist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp_folderlist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
//		filelist.setLineWrap(true);
//		folderlist.setLineWrap(true);
		record.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						RecordFile(startfile);
						JOptionPane.showMessageDialog(null, "因重复hashcode删除数据"+query_hashcode_count+"\n因重复md5删除数据"+query_md5_count+"\n操作完成");
						
					}
					
				}
				);

		choosefolder.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						JFileChooser jfc=new JFileChooser(startfile);
						jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						jfc.showDialog(new JLabel(),"打开");
						File file=jfc.getSelectedFile();
						if(file!=null)
						{
						folderlist.append(file.getAbsolutePath()+"\n");
						startfile=file;
						getFileNumber(startfile);
						jtf.setText("包含文件"+FileNumber+"，大小"+getFileLength(FileLength));
						jtf.setCaretPosition(jtf.getText().length());
						jtf.paintImmediately(jtf.getBounds());
						updateUI();
						
						}
					}
				}
	
				);
		hashcode.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						if(startfile.isFile())
						{
							filelist.append(startfile.getName()+"  HashCode:"+startfile.hashCode()+"\n");
							filelist.paintImmediately(filelist.getBounds());
						}
					}
					
				}

				);
		md5.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						if(startfile.isFile())
						{
							filelist.append(startfile.getName()+"  MD5:"+md.getNormalFileMD5(startfile)+"\n");
							filelist.paintImmediately(filelist.getBounds());
						}
					}
					
				}	
				);
		open.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						if(startfile.isFile())
						{
							String read="";
							int i=0;
							FileReader fr=null;
							BufferedReader br=null;
							try {
								fr=new FileReader(startfile);
								br=new BufferedReader(fr);
								while((read=br.readLine())!=null)
								{
									i++;
									filelist.append(i+"  "+read+"\n");
									filelist.setCaretPosition(filelist.getText().length());
									filelist.paintImmediately(filelist.getBounds());
								}
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}finally
							{
								try {
									if(br!=null)
										br.close();
									if(fr!=null)
										fr.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
					
				}
				);
		clean.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						filelist.setText("");
						folderlist.setText("");
						jtf.setText("");
						count=0;
						FileNumber=0;
						FileLength=0;
						query_hashcode_count=0;
						query_md5_count=0;
					}
					
				}
				
				);
		query.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						int path=0;
						int file=0;
						String keyword=JOptionPane.showInputDialog(null, "请输入查询内容", "查询", JOptionPane.YES_NO_CANCEL_OPTION);
						if((keyword==null)||(keyword.equals("")))
						{
							JOptionPane.showMessageDialog(null, "请输入有效查询", "查询出错", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							path=query_path(keyword);
							file=query_filename(keyword);
						}
						if(path!=0)
							jtf.append("有效数据"+(100*file/path)+"%");
						
					}
					
				}
				
				
				
				);
		
		update.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						folderlist.append("\n"+count+"\n");
						//TODO
						
						updatePath(startfile);
						JOptionPane.showMessageDialog(null, "数据更新完成");
					}
					
				});
		
		deldir.addActionListener(
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						DeleteBlankDirectory(startfile);
						
					}
				}
				
				
				);
		
		start.addActionListener(this);
	}


	public boolean DeleteBlankDirectory(File path)
	{
		if(path.exists())
		{
			File file[]=path.listFiles();
			for(int i=0;i<file.length;i++)
			{
				if(file[i].isDirectory())
				{
					DeleteBlankDirectory(file[i]);
				}
			}
		}
		boolean flag=path.delete();
		if(flag)
		{
			
			filelist.insert("delete blank folder   "+path+"\n", 0);
			filelist.setCaretPosition(filelist.getText().length());
			filelist.paintImmediately(filelist.getBounds());
		}
		return flag;
	}

	
	public void actionPerformed(ActionEvent e) {
		if(startfile!=null)
		{
			if(startfile.isDirectory())
			{
				listFile(startfile);
				folderlist.append("\n"+count+"\n");
				JOptionPane.showMessageDialog(null, "操作完成");
			}
		}
	}
	Database updatefile=new Database();
	public void updatePath(File file)
	{
		
		if(file.isDirectory())
		{
			File[] fl=file.listFiles();
			for(int i=0;i<fl.length;i++)
			{
				if(fl[i].isDirectory())
				{
					updateUI();
					folderlist.insert(sdf.format(new Date())+" "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+" "+fl[i].getAbsolutePath()+"\n",0);
//					jta.append(sdf.format(new Date())+"  "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+"\t"+fl[i].getAbsolutePath()+"\n");
				
					folderlist.setCaretPosition(folderlist.getText().length());
					folderlist.paintImmediately(folderlist.getBounds());
					updateUI();
					updatePath(fl[i]);
				}
				else if(fl[i].isFile())
				{
					count++;
					if(updatefile.update(fl[i]))
					{
						filelist.insert("update success "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						filelist.setCaretPosition(filelist.getText().length());
						filelist.paintImmediately(filelist.getBounds());
					}
					else
					{
						filelist.insert("no such record, new Record insert into database "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						filelist.setCaretPosition(filelist.getText().length());
						filelist.paintImmediately(filelist.getBounds());
					}
				}
				
				
			}
		}
		
	}
	
	
	
	public void listFile(File file)
	{
		if(file.isDirectory())
		{
			File[] fl=file.listFiles();
			for(int i=0;i<fl.length;i++)
			{
				if(fl[i].isDirectory())
				{
					updateUI();
					folderlist.insert(sdf.format(new Date())+" "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+" "+fl[i].getAbsolutePath()+"\n",0);
//					jta.append(sdf.format(new Date())+"  "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+"\t"+fl[i].getAbsolutePath()+"\n");
				
					folderlist.setCaretPosition(folderlist.getText().length());
					folderlist.paintImmediately(folderlist.getBounds());
					updateUI();
					listFile(fl[i]);
				}
				else if(fl[i].isFile())
				{
					count++;
					filelist.insert((i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
				}
				
				
			}
		}
	}
	long FileLength=0;
	int FileNumber=0;
	public void getFileNumber(File file)
	{
		if(file.isDirectory())
		{
			File[] fl=file.listFiles();
			for(int i=0;i<fl.length;i++)
			{
				if(fl[i].isDirectory())
				{
					getFileNumber(fl[i]);
					
				}
				else if(fl[i].isFile())
				{
					FileNumber++;
					FileLength+=fl[i].length();
				}
			}
		}
		else if(file.isFile()){
			FileNumber++;
			FileLength+=file.length();
		}
	}
	
	Date starttime=null;
	Date endtime=null;
	int query_md5_count=0;
	int query_hashcode_count=0;
	public void RecordFile(File file)
	{
		starttime=new Date();
		String timelast="";
		if(file.isDirectory())
		{
			File[] fl=file.listFiles();
			for(int i=0;i<fl.length;i++)
			{
				if(fl[i].isDirectory())
				{
					updateUI();
					String logString=sdf.format(new Date())+" "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+" "+fl[i].getAbsolutePath();
					folderlist.insert(logString+"\n",0);
//					jta.append(sdf.format(new Date())+"  "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+"\t"+fl[i].getAbsolutePath()+"\n");
					new Log().FolderLog(logString);
					folderlist.setCaretPosition(folderlist.getText().length());
					folderlist.paintImmediately(folderlist.getBounds());
					updateUI();
					RecordFile(fl[i]);
				}
				else if(fl[i].isFile())
				{
					count++;
					String thismd5=md.getBigFileMD5(fl[i]);
					if(!(d.query_md5(thismd5)))
					{
						d.add(fl[i].getName(),fl[i].length(),fl[i].hashCode(),thismd5,sdf.format(new Date((fl[i].lastModified()))),fl[i].getAbsolutePath());
						filelist.insert("录入 "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
					}
					else
					{
						query_md5_count++;
						filelist.insert("MD5重复，删除文件 "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						fl[i].delete();
					}
				}

				filelist.setCaretPosition(filelist.getText().length());
				filelist.paintImmediately(filelist.getBounds());
					
				if(count%5==0)
					{
						endtime=new Date();
						long time=endtime.getTime()-starttime.getTime();
						if(time==0)
							time=1;
						int speed=(int) (5*1000/time);
						int speed1=(int) (5*10000/time)/10;
						int speed2=(int) (5*10000/time)%10;
						if(speed==0)
							speed=1;
						if(FileNumber==0)
							FileNumber=1;
						timelast="average time "+time+"ms ,speed "+speed1+"."+speed2+"/s complete "+count*100/FileNumber+"%  and  last time "+(FileNumber-count)/speed+" s ===> "+(FileNumber-count)/speed/3600+" h "+(FileNumber-count)/speed%3600/60+" min "+(FileNumber-count)/speed%3600%60+" s\n";
						updateUI();
						jtf.setText(timelast);
						jtf.setCaretPosition(jtf.getText().length());
						jtf.paintImmediately(jtf.getBounds());
						updateUI();
						starttime=new Date();
					}	
					
				}
				
				
			}
		}
		

	
	
	public int query_filename(String keyword)
	{
		Date start=new Date();
		int count=0;
		filelist.append("精确查询\n");
		Connection conn=d.getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from fileinfo where filename like '%"+keyword+"%' order by count desc");
			while(rs.next())
			{
				count++;
				filelist.append("[filename]\t"+rs.getString("filename")+"\n[length]\t"+getFileLength(rs.getLong("length"))+"\n[hashcode]\t"+rs.getString("hashcode")+"\n[MD5]\t"+rs.getString("MD5")+"\n[LastModified] "+rs.getString("lastmodified")+"\n[path]\t"+rs.getString("path")+"\n[count]\t"+rs.getString("count")+"\n\n");
				filelist.setCaretPosition(filelist.getText().length());
				filelist.paintImmediately(filelist.getBounds());
			}
			stmt.executeUpdate("update fileinfo set count=count+1 where filename like '%"+keyword+"%'");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
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
		}
		filelist.append("\n查询关键词["+keyword+"]相关记录"+count+"条\n");
		filelist.setCaretPosition(filelist.getText().length());
		filelist.paintImmediately(filelist.getBounds());
		Date end=new Date();
		jtf.append("精确查询"+count+"条，用时"+(end.getTime()-start.getTime())+"ms   ");
		jtf.setCaretPosition(jtf.getText().length());
		jtf.paintImmediately(jtf.getBounds());
		updateUI();
		return count;
	}
	
	public int query_path(String keyword)
	{
		Date start=new Date();
		int count=0;
		folderlist.append("模糊查询\n");
		Connection conn=d.getConnect();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from fileinfo where path like '%"+keyword+"%' order by lastmodified");
			while(rs.next())
			{
				count++;
				folderlist.append("[filename]\t"+rs.getString("filename")+"\n[length]\t"+getFileLength(rs.getLong("length"))+"\n[hashcode]\t"+rs.getString("hashcode")+"\n[MD5]\t"+rs.getString("MD5")+"\n[LastModified] "+rs.getString("lastmodified")+"\n[path]\t"+rs.getString("path")+"\n[count]\t"+rs.getString("count")+"\n\n");
				folderlist.setCaretPosition(filelist.getText().length());
				folderlist.paintImmediately(filelist.getBounds());
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
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
		}
		
		folderlist.append("\n模糊查询关键词["+keyword+"]"+count+"条\n");
		folderlist.setCaretPosition(filelist.getText().length());
		folderlist.paintImmediately(filelist.getBounds());
		Date end=new Date();
		jtf.append("模糊查询"+count+"条，耗时"+(end.getTime()-start.getTime())+"ms ,");
		jtf.setCaretPosition(jtf.getText().length());
		jtf.paintImmediately(jtf.getBounds());
		updateUI();
		return count;
	}
	
	
	public String getFileLength(long length)
	{
		int G=0;
		int M=0;
		int k=0;
		int b=0;
		long sizeG=(long)(1024*1024*1024);
		if(sizeG<length)
		{
//			System.out.println("G");
			G=(int) (length/(1024*1024*1024));
			M=(int) (length%(1024*1024*1024)/(1024*1024));
			k= (int) ((length%(1024*1024*1024)%(1024*1024))/(1024));
			b= (int) ((length%(1024*1024*1024)%(1024*1024))%(1024));
		}
		else if((1024*1024)<length&&length<(1024*1024*1024))
		{
//			System.out.println("M");
			M=(int) (length/(1024*1024));
			k=(int) (length%(1024*1024)/1024);
			b=(int) (length%(1024*1024)%1024);
		}
		else if(1024<length&&length<(1024*1024))
		{
//			System.out.println("k");
			k=(int) (length/1024);
			b=(int) (length%1024);
		}
		else if(length<1024)
		{
//			System.out.println("b");
			b=(int) length;
		}
		
		String filelength="";
		if(G>0)
		{
			filelength+=G+"G ";
		}
		if(M>0)
		{
			filelength+=M+"M ";
		}
		if(k>0)
		{
			filelength+=k+"k ";
		}
		filelength+=b+ "b ";
		return filelength;
	}
	

	public void update(File file)
	{
		Database d=new Database();
		String path=file.getAbsolutePath();
		String md5=new getMD5().getNormalFileMD5(file);
		long hashcode=file.hashCode();
		String sql="";
		if(d.query_hashcode(hashcode))
			{
				if(d.query_md5(md5))
				{
					sql="update fileinfo set path=\""+path+"\" where hashcode="+hashcode;
					filelist.insert("更新path  "+path+"\n",0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
					updateUI();
				}
				else
				{
					sql="update fileinfo set path=\""+path+"\",md5='"+md5+"' where hashcode="+hashcode;
					filelist.insert("更新md5和path值   "+md5+"   "+path+"\n",0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
					updateUI();
				}
			}
			else
			{
				if(d.query_md5(md5))
				{
					sql="update fileinfo set path=\""+path+"\",hashcode="+hashcode+" where md5='"+md5+"'";
					filelist.insert("更新hashcode和path   "+hashcode+"   "+path+"\n",0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
					updateUI();
				}
				else
				{
					d.add(file.getName(),file.length(),hashcode,md5,sdf.format(new Date(file.lastModified())),path);
					filelist.insert("新信息，已记录到数据库中\n",0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
					updateUI();
				}
			}
		if(!sql.isEmpty())
		{
			d.update(sql);
		}
	}
	
	public void update_listfile(File f)
	{
		if(f.isFile())
		{
			update(f);
		}
		else
		{
			File[] fl=f.listFiles();
			for(int i=0;i<fl.length;i++)
			{
				if(fl[i].isDirectory())
				{
					folderlist.insert(fl.length+"/"+i+"/"+fl[i].listFiles().length+"  "+fl[i].getName()+"\n",0);
					folderlist.setCaretPosition(folderlist.getText().length());
					folderlist.paintImmediately(folderlist.getBounds());
					updateUI();
					update_listfile(fl[i]);
				}
				else
				{
					update(fl[i]);
				}
			}
		}
	}
	
}
