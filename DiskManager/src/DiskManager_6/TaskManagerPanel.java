package DiskManager_6;



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
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	JButton choosefolder=new JButton("choose file");
	JButton start=new JButton("get start");
	JButton md5=new JButton("get MD5");
	JButton hashcode=new JButton("get hashcode");
	JButton open=new JButton("open file");
	JButton clean=new JButton("clean");
	JButton record=new JButton("Record");
	JButton query=new JButton("Query");
	JButton update=new JButton("update");
	
	JTextArea jtf=new JTextArea(1,75);
//	JTextArea jta=new JTextArea(16,51);
	JTextArea filelist=new JTextArea(21,38);
	JTextArea folderlist=new JTextArea(21,38);
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
						JOptionPane.showMessageDialog(null, "Operation Complete!");
						
					}
					
				}
				);

		choosefolder.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						JFileChooser jfc=new JFileChooser(startfile);
						jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						jfc.showDialog(new JLabel(),"open");
						File file=jfc.getSelectedFile();
						if(file!=null)
						{
						folderlist.append(file.getAbsolutePath()+"\n");
						startfile=file;
						getFileNumber(startfile);
						jtf.setText(" Search file "+FileNumber+"records and total size "+getFileLength(FileLength));
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
							filelist.append(startfile.getName()+"  MD5:"+md.getBigFileMD5(startfile)+"\n");
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
					}
					
				}
				
				);
		query.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						int path=0;
						int file=0;
						String keyword=JOptionPane.showInputDialog(null, "input the keyword to query", "QUERY", JOptionPane.YES_NO_CANCEL_OPTION);
						if((keyword==null)||(keyword.equals("")))
						{
							JOptionPane.showMessageDialog(null, "Illegel input , again", "QUERY FAILED", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							path=query_path(keyword);
							file=query_filename(keyword);
						}
						if(path!=0)
							jtf.append("Correct Information Percent "+(100*file/path)+"%");
						
					}
					
				}
				
				
				
				);
		
		update.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						folderlist.append("\n"+count+"\n");
						//TODO
						update_listfile(startfile);
						JOptionPane.showMessageDialog(null, "Information Update Complete!");
					}
					
				});
		
		start.addActionListener(this);
	}



	
	public void actionPerformed(ActionEvent e) {
		if(startfile!=null)
		{
			if(startfile.isDirectory())
			{
				listFile(startfile);
				folderlist.append("\n"+count+"\n");
				JOptionPane.showMessageDialog(null, "Operation Complete!");
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
					folderlist.insert(sdf.format(new Date())+" "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+" "+fl[i].getAbsolutePath()+"\n",0);
//					jta.append(sdf.format(new Date())+"  "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+"\t"+fl[i].getAbsolutePath()+"\n");
				
					folderlist.setCaretPosition(folderlist.getText().length());
					folderlist.paintImmediately(folderlist.getBounds());
					updateUI();
					RecordFile(fl[i]);
				}
				else if(fl[i].isFile())
				{
					count++;
					
					if(!(d.query_hashcode(fl[i].hashCode())))
					{
						if(!(d.query_md5(md.getBigFileMD5(fl[i]))))
						{
							d.add(fl[i]);
							filelist.insert("-----Record Successful  "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						}
						else
						{
							filelist.insert("get its MD5 record in database delete file "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
							fl[i].delete();
						}
					}
					else
					{
						filelist.insert("get its HASHCODE record in database delete file "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						fl[i].delete();
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
		
	}	
	
	public void newRecord(File file)
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
					folderlist.insert(sdf.format(new Date())+" "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+" "+fl[i].getAbsolutePath()+"\n",0);
//					jta.append(sdf.format(new Date())+"  "+(i+1)+"/"+fl.length+"/"+fl[i].listFiles().length+"\t"+fl[i].getAbsolutePath()+"\n");
				
					folderlist.setCaretPosition(folderlist.getText().length());
					folderlist.paintImmediately(folderlist.getBounds());
					updateUI();
					RecordFile(fl[i]);
				}
				else if(fl[i].isFile())
				{
					count++;
					
					if(!(d.query_hashcode(fl[i].hashCode())))
					{
						if(!(d.query_md5(md.getNormalFileMD5(fl[i]))))
						{
							d.add(fl[i]);
							filelist.insert("-----Record Successful  "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						}
						else
						{
							filelist.insert("get its MD5 record in database delete file "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
							fl[i].delete();
						}
					}
					else
					{
						filelist.insert("get its HASHCODE record in database delete file "+(i+1)+"/"+fl.length+"  "+fl[i].getName()+"  "+fl[i].length()+"\n", 0);
						fl[i].delete();
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
	}
	
	public int query_filename(String keyword)
	{
		Date start=new Date();
		int count=0;
		filelist.append("Accurate Query\n");
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
		filelist.append("\nkeyword ["+keyword+"] related records "+count+"\n");
		filelist.setCaretPosition(filelist.getText().length());
		filelist.paintImmediately(filelist.getBounds());
		Date end=new Date();
		jtf.append("AQ information "+count+" records spending "+(end.getTime()-start.getTime())+"ms   ");
		jtf.setCaretPosition(jtf.getText().length());
		jtf.paintImmediately(jtf.getBounds());
		updateUI();
		return count;
	}
	
	public int query_path(String keyword)
	{
		Date start=new Date();
		int count=0;
		folderlist.append("Fuzzy Query\n");
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
		
		folderlist.append("\nget keyword ["+keyword+"]"+count+" records\n");
		folderlist.setCaretPosition(filelist.getText().length());
		folderlist.paintImmediately(filelist.getBounds());
		Date end=new Date();
		jtf.append(" FQ infomation "+count+" records spending "+(end.getTime()-start.getTime())+"ms ,");
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
					filelist.insert("update PATH "+path+"\n",0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
					updateUI();
				}
				else
				{
					sql="update fileinfo set path=\""+path+"\",md5='"+md5+"' where hashcode="+hashcode;
					filelist.insert("update MD5 and PATH "+md5+"   "+path+"\n",0);
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
					filelist.insert("update HASHCODE and PATH "+hashcode+"   "+path+"\n",0);
					filelist.setCaretPosition(filelist.getText().length());
					filelist.paintImmediately(filelist.getBounds());
					updateUI();
				}
				else
				{
					d.add(file);
					filelist.insert("new file has been scanning and add to database\n",0);
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
