package Omap_Location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	
	public static void main(String[] args){
		JFileChooser jfc=new JFileChooser("C:\\Users\\周洋\\Desktop\\新洲污普工作情况.xlsx");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showDialog(jfc, null);
		File excel=jfc.getSelectedFile();
		
		ReadExcel re=new ReadExcel();
		re.write2kml(re.getExcel(excel));
	}
	
	public List<Excel_Elements> getExcel(File excel)
	{
		List<Excel_Elements> list=new ArrayList<Excel_Elements>();
		if(excel.getName().endsWith(".xls"))
		{
			list=Read_xls(excel);
		}
		else if(excel.getName().endsWith(".xlsx"))
		{
			list=Read_xlsx(excel);
		}
		else
		{
			System.err.println("本程序不支持打开的文件类型，"+excel.getName());
		}
		return list;
	}
	
	
	
	public List<Excel_Elements> Read_xls(File excel)
	{
		List<Excel_Elements> excel_info=null;
		
		InputStream is=null;
		HSSFWorkbook wb=null;
		HSSFSheet sheet=null;
		HSSFRow row=null;
//		Cell cell=null;
		try {
			is=new FileInputStream(excel);
			wb=new HSSFWorkbook(is);
			excel_info=new ArrayList<Excel_Elements>();
			sheet=wb.getSheet(wb.getSheetName(0));
			for(int RowNum=0;RowNum<=sheet.getLastRowNum();RowNum++)
			{
				row=sheet.getRow(RowNum);
				if(row==null)
					continue;
				Excel_Elements elements=new Excel_Elements();
				
				String num=row.getCell(0).toString();
				String VillageID=row.getCell(1).toString();
				String CompanyName=row.getCell(6).toString();
				String VillageName=row.getCell(13).toString();
				String address=row.getCell(14).toString();
				String people=row.getCell(15).toString();
				String phoneNumber=row.getCell(17).toString();
				String Industry_category=row.getCell(18).toString();
				String Longitude=row.getCell(22).toString();
				String latitude=row.getCell(23).toString();
				String EnumeratorID=row.getCell(24).toString();
				String EnumeratorName=row.getCell(25).toString();
				
				elements.setValues(num, VillageID, CompanyName, VillageName, address, people, phoneNumber, Industry_category, Longitude, latitude, EnumeratorName, EnumeratorID);
				excel_info.add(elements);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excel_info;
	}
	
	public List<Excel_Elements> Read_xlsx(File excel)
	{
		List<Excel_Elements> excel_info=null;
		
		InputStream is=null;
		XSSFWorkbook wb=null;
		XSSFSheet sheet=null;
		XSSFRow row=null;
//		Cell cell=null;
		try {
			is=new FileInputStream(excel);
			wb=new XSSFWorkbook(is);
			excel_info=new ArrayList<>();
			sheet=wb.getSheet(wb.getSheetName(0));
			for(int RowNum=1;RowNum<=sheet.getLastRowNum();RowNum++)
			{
				row=sheet.getRow(RowNum);
				if(row==null)
					continue;
				
				Excel_Elements elements=new Excel_Elements();
				
				String num=row.getCell(0).toString();
				String VillageID=row.getCell(1).toString();
				String CompanyName=row.getCell(6).toString();
				String VillageName=row.getCell(13).toString();
				String address=row.getCell(14).toString();
				String people=row.getCell(15).toString();
				String phoneNumber=row.getCell(17).toString();
				String Industry_category=row.getCell(18).toString();
				String Longitude=row.getCell(22).toString();
				String latitude=row.getCell(23).toString();
				String EnumeratorID=row.getCell(24).toString();
				String EnumeratorName=row.getCell(25).toString();
				
				elements.setValues(num, VillageID, CompanyName, VillageName, address, people, phoneNumber, Industry_category, Longitude, latitude, EnumeratorName, EnumeratorID);
				excel_info.add(elements);	
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excel_info;
	}
	
	public void write2kml(List list)
	{
		String startString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"\n "+
				"\txmlns:atom=\"http://www.w3.org/2005/Atom\"\n" +
				"\txmlns:gx=\"http://www.google.com/kml/ext/2.2\" \n"+
				"\t>"+
				"\n\t<Document>"+
				"\n\t\t<name>新洲普查定位数据</name>\n";
		WriteKML(startString);
		String endString="\n\t</Document>\n</kml>";
		
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
		String formatString="";
		
		

		Iterator<Excel_Elements> iter=list.iterator();
		Excel_Elements temp=null;
		while(iter.hasNext())
		{
			temp=iter.next();
			formatString=a1+temp.getNum()+a2+temp.getSumString()+a3+temp.getLongitude()+","+temp.getLatitude()+",0"+a4;
			WriteKML(formatString);
			System.out.println(temp.getCompanyName()+"\t"+temp.getPeople()+"\t"+temp.getLongitude()+"\t"+temp.getLatitude());
			
		}
		
		WriteKML(endString);
	}

	
	public void WriteKML(String bufferString)
	{
		FileWriter fw;
		try {
			fw = new FileWriter("d:\\奥维定位文件.kml",true);
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
	
}


