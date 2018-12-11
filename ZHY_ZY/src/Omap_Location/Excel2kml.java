package Omap_Location;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
/*
 * 本程序主要实现功能为将指定Excel中的信息读取出来，写成相应的奥维地图导出文件kml格式(Json格式)
 * 主要分为以下功能：
 * 		1.以对话框的形式选择读取的文件
 * 		2.弹出对话框设置输出文件名
 * 		3.设置内置数据集名称
 * 		4.文件名自带格式化时间标记
 * 
 * Version 1.0
 * @Author 周洋
 * @Time 2018-08-23 1:49
 * 
 * */
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Excel2kml {

	//定义全局变量，用于控制文本选择，写入文件命名，是否写入文件
	boolean write=true;
	String choosefile;
	String writefilenameString="C:\\Users\\周洋\\Desktop\\"+JOptionPane.showInputDialog("请设置输出kml文件名")+timetoName()+".kml";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("hello word!");
		
		new  Excel2kml().readExcel(new Excel2kml().ChooseFile().toString());
		JOptionPane.showMessageDialog(null, "操作完毕!");
	}

	public void readExcel(String excel)
	{
		String nameString=new File(excel).getName();
//		System.out.println(nameString);
		String[] filename=nameString.split(".");
//		System.out.println(filename);
		String startString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
											"<kml xmlns=\"http://www.opengis.net/kml/2.2\"\n "+
											"\txmlns:atom=\"http://www.w3.org/2005/Atom\"\n" +
											"\txmlns:gx=\"http://www.google.com/kml/ext/2.2\" \n"+
											"\t>"+
											"\n\t<Document>"+
											"\n\t\t<name>"+nameString+"</name>\n";
		System.out.println(startString);
		String readString="";
		String endString="\n\t</Document>\n</kml>";
		if(write)
		{
			WriteKML(startString);
		}
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(excel);
			br=new BufferedReader(fr);
			
			try {
				while((readString=br.readLine())!=null)
				{
	//				String s=br.readLine();
	//				System.out.println(readString);
					cutString(readString);
	//				break;
	//				System.out.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(endString);
		if(write)
		{
			WriteKML(endString);
		}
	
		
	}
	
	public void cutString(String string)
	{
		String a1="<Placemark>"+
							"\n\t<name>";
		String a2="</name>"+
								"\n\t\t<description>";
		String a3="</description>"+
								"\n\t\t\t<Style>"+
										"\n\t\t\t\t<IconStyle>"+
											"\n\t\t\t\t\t<Icon>"+
												"\n\t\t\t\t\t<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>"+
											"\n\t\t\t\t\t</Icon>"+
												"\n\t\t\t\t<OvIcon>1</OvIcon>"+
													"\n\t\t\t\t<OvIconNum>0</OvIconNum>"+
												"\n\t\t\t\t<color>ffffffff</color>"+
													"\n\t\t\t\t<scale>1.0</scale>"+
												"\n\t\t\t\t</IconStyle>"+
													"\n\t\t\t</Style>"+
												"\n\t<Point>"+"\n\t\t<coordinates>";
		String a4="</coordinates>\n\t</Point>\n</Placemark>";
		String busString=null;
		String[] str=string.split("\t");
		busString=a1+str[0]+a2+str[2]+"\n  "+str[3]+" "+str[4]+" "+str[5]+"  "+str[6]+a3+str[11]+",0"+a4;
		System.out.println(busString);
		if(write)
		{
			WriteKML(busString);
		}
//	    for(int i=0,len=str.length;i<len;i++)
//		{
			
	//		System.out.println(str[i]);
//		}
	}
	
	public void WriteKML(String bufferString)
	{
		FileWriter fw;
		try {
			fw = new FileWriter(writefilenameString,true);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(bufferString);
			bw.newLine();
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public File ChooseFile()
	{
		JFileChooser jfc=new JFileChooser("i:\\");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(new JLabel(),"打开");
		File file=jfc.getSelectedFile();
		return file;
	}
	
	public String timetoName()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(new Date());
	}
	
	public void initinal()
	{
		
	}
}
