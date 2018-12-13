package Omap_Location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class getEXIF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File jpegFile=new File("E:\\新洲污普\\新建文件夹\\");
		getEXIF gx=new getEXIF();
		gx.readFile(jpegFile);
	}
	
	
	public void getExif(File jpegFile)
	{
		String exifString=jpegFile.getName();
		try {
			Metadata metadata=JpegMetadataReader.readMetadata(jpegFile);
			for(Directory directory:metadata.getDirectories())
			{
				for(Tag tag:directory.getTags())
				{
		//		if(tag.getTagName().contains("GPS"))
					{
						System.out.print("name : " + tag.getTagName().replaceAll(" ", "") + "  -->");
						System.out.println("desc : " + tag.getDescription().replaceAll(" ", ""));
						if(tag.getTagName().endsWith("Latitude")||tag.getTagName().endsWith("Longitude"))
						{
							exifString=exifString+"\t"+ConvertDuFenMiao(tag.getDescription().replaceAll(" ", ""));
						}
						
					}
				}
			}
			
		} catch (JpegProcessingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(exifString);
//		Write(exifString);
	}
	
	public void readFile(File file)
	{
		File[] fl=file.listFiles();
		for(int i=0;i<fl.length;i++)
		{
			if(fl[i].isDirectory())
			{
				Write(fl[i].getName());
				System.out.println(fl[i].getName());
				readFile(fl[i]);
			}
			else if(fl[i].isFile())
			{
				if(fl[i].getName().endsWith(".jpg"))
				{
					getExif(fl[i]);
				}
			}
		}
	}
	
	public void Write(String exifString)
	{
		String path="C:\\Users\\周洋\\Desktop\\集中式\\照片GPS信息.txt";
		try {
			FileWriter fw=new FileWriter(path,true);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(exifString);
			bw.newLine();
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String ConvertDuFenMiao(String string)
	{
		System.out.println(string);
		DecimalFormat dd=new DecimalFormat("0");
		DecimalFormat df=new DecimalFormat("0.000");
		
		String[] a=string.split("°");
		Double du=null;
		try {
			du=Double.valueOf(a[0]).doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(a[0]+"\t"+a[1]);
		}
		int du_int=(int) Double.parseDouble(dd.format(du));
	
		String[] b=a[1].split("'"); 
		Double fen = null;
		try {
			fen=Double.valueOf(b[0]).doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(b[0]+"\t"+b[1]);
		}
		int fen_int=(int) Double.parseDouble(dd.format(fen));
		
		String[] c=b[1].split("\"");
		Double miaoDouble=null;
		try {
				miaoDouble=Double.valueOf(c[0]).doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
		}
		miaoDouble=Double.parseDouble(df.format(miaoDouble));
		
		String returnString=du_int+"°"+fen_int+"\'"+miaoDouble+"\"";
		return returnString;
	}
	
}
