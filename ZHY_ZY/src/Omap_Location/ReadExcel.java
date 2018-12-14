package Omap_Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	
	public static void main(String[] args){
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showDialog(jfc, null);
		File excel=jfc.getSelectedFile();
		new ReadExcel().getExcel(excel);
	}
	
	public void getExcel(File excel)
	{
		List<excel_elements> list=new ArrayList<excel_elements>();
		if(excel.getName().endsWith("xls"))
		{
			list=Read_xls(excel);
		}
		else if(excel.getName().endsWith("xlsx"))
		{
			list=Read_xlsx(excel);
		}
		else
		{
			System.err.println("本程序不支持打开文件类型，"+excel.getName());
		}
		Iterator<excel_elements> iter=list.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next().CompanyName);
		}
		System.out.println(excel.length());
	}
	
	
	
	public List<excel_elements> Read_xls(File excel)
	{
		List<excel_elements> excel_info=null;
		
		InputStream is=null;
		HSSFWorkbook wb=null;
		HSSFSheet sheet=null;
		HSSFRow row=null;
		Cell cell=null;
		try {
			is=new FileInputStream(excel);
			wb=new HSSFWorkbook(is);
			excel_info=new ArrayList<excel_elements>();
			sheet=wb.getSheet(wb.getSheetName(0));
			for(int RowNum=0;RowNum<sheet.getLastRowNum();RowNum++)
			{
				row=sheet.getRow(RowNum);
				if(row==null)
					continue;
				excel_elements elements=new excel_elements();
				elements.setName(row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString());
				excel_info.add(elements);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel_info;
	}
	
	public List<excel_elements> Read_xlsx(File excel)
	{
		List<excel_elements> excel_info=null;
		
		InputStream is=null;
		XSSFWorkbook wb=null;
		XSSFSheet sheet=null;
		XSSFRow row=null;
		Cell cell=null;
		try {
			is=new FileInputStream(excel);
			wb=new XSSFWorkbook(is);
			excel_info=new ArrayList<>();
			sheet=wb.getSheet(wb.getSheetName(0));
			for(int RowNum=0;RowNum<sheet.getLastRowNum();RowNum++)
			{
				row=sheet.getRow(RowNum);
				if(row==null)
					continue;
				excel_elements elements=new excel_elements();
				
				elements.setName(row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString());
				excel_info.add(elements);	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel_info;
	}
	

}


