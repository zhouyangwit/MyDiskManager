package Omap_Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;





public class ReadExcel {
	
	ReadExcel(){
		read();
	}
	
	public static List<excel_elements> read(){
		InputStream is=null;
		HSSFWorkbook excel=null;
		excel_elements ee=null;
		try {
			is=new FileInputStream("C:\\Users\\周洋\\Desktop\\邾城普查企业名录.xls");
			excel=new HSSFWorkbook(is);
			List<excel_elements> list=new ArrayList<excel_elements>();
			//循环工作表
			for(int numSheet=0;numSheet<excel.getNumberOfSheets();numSheet++)
			{
				HSSFSheet sheet=excel.getSheetAt(numSheet);
				if(sheet==null)
					continue;
				//循环行Row
				for(int rowNum=1;rowNum<sheet.getLastRowNum();rowNum++)
				{
					HSSFRow row=sheet.getRow(rowNum);
					if(row==null)
						continue;
					for(int cIndex=row.getFirstCellNum();cIndex<row.getLastCellNum();cIndex++)
					{
						ee=new excel_elements();
						Cell cell=row.getCell(rowNum);
						if(cell!=null)
							System.out.print(cell+"\t");
					}
					if(row!=null)
						System.out.println();
					
				}
			}
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}
	

}


