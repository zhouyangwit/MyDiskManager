package Inspiration;

import java.awt.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageArrange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database dt=new Database();
		JFileChooser jfc=new JFileChooser("d:\\downloads");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(new Label(), "选择");
		File f=jfc.getSelectedFile();
		
		ImageArrange ia=new ImageArrange();
		ia.readFolder(f,dt);
		
	}
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void readFile(File f,Database dt)
	{
		String filename=f.getName();
		String path=f.getParent();
		long length=f.length();
		long time=f.lastModified();
		String lastmodified=sdf.format(time);
		String md5=getMD5(f);
		String exif="";
		if(f.getName().contains(".jp"))
		{
			exif=readEXIF(f);
		}
		if(!dt.query_md5(md5))
		{
			dt.add(filename, length, md5, lastmodified, path, exif);
		}
		else
		{
			dt.update("update fileinfo set count=count+1 where md5='"+md5+"';");
		}
		
	}
	
	int count=0;
	public void readFolder(File f,Database dt)
	{
		if(f.isDirectory())
		{
			count++;
			File[] fl=f.listFiles();
			System.out.println(count+"/"+fl.length+"\t"+f.getPath());
			for(int i=0;i<fl.length;i++)
			{
				if(fl[i].isDirectory())
				{
					readFolder(fl[i],dt);
				}
				else if(fl[i].isFile())
				{
					readFile(fl[i],dt);
				}
			}
		}
		else if(f.isFile())
		{
			readFile(f,dt);
		}
	}

	
	public String getMD5(File file)
	{
		char[] hexChar={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		String value="";
		int bufferSize=256*1024;
		FileInputStream fileInputStream=null;
		DigestInputStream digestInputStream=null;
		try {
			MessageDigest messageDigest=MessageDigest.getInstance("MD5");
			fileInputStream=new FileInputStream(file.toString());
			digestInputStream=new DigestInputStream(fileInputStream,messageDigest);
			byte[] buffer=new byte[bufferSize];
			while(digestInputStream.read(buffer)>0)
			{
				
			}
			messageDigest=digestInputStream.getMessageDigest();
			byte[] resultByteArray=messageDigest.digest();
			StringBuilder sb=new StringBuilder(resultByteArray.length*2);
			for(int n=0;n<resultByteArray.length;n++)
			{
				sb.append(hexChar[(resultByteArray[n]&0xf0)>>>4]);
				sb.append(hexChar[resultByteArray[n]&0x0f]);
			}
			value=sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			return null;
		}finally
		{
			
			try {
					if(digestInputStream!=null)
						digestInputStream.close();
					if(fileInputStream!=null)
						fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
		return value;
	}
	
	
	public String readEXIF(File jpegFile)
	{
		String exif="";
		String info="";
		String exif_ifd0="";
		String gps="";
		String exif_subifd="";
		String jpeg="";
		
		try {
			Metadata metadata=JpegMetadataReader.readMetadata(jpegFile);
			for(Directory directory:metadata.getDirectories())
			{
				for(Tag tag:directory.getTags())
				{
					if(tag.getDirectoryName().contains("Exif IFD0"))
					{
						exif_ifd0+=tag.getDirectoryName()+"\t"+tag.getTagName()+"\t"+tag.getDescription();
					}
					
					else if(tag.getDirectoryName().contains("GPS"))
					{
						gps+=tag.getDirectoryName()+"\t"+tag.getTagName()+"\t"+tag.getDescription();
					}
					
					else if(tag.getDirectoryName().contains("Exif SubIFD"))
					{
						exif_subifd+=tag.getDirectoryName()+"\t"+tag.getTagName()+"\t"+tag.getDescription();
					}
					
					else if(tag.getDirectoryName().contains("JPEG"))
					{
						jpeg+=tag.getDirectoryName()+"\t"+tag.getTagName()+"\t"+tag.getDescription();
					}
					
					info=tag.getDirectoryName()+"\t"+tag.getTagName()+"\t"+tag.getDescription();
					exif=exif+info+"\n";
					info="";	
				}
			}
		} catch (JpegProcessingException | IOException e) {
			e.printStackTrace();
			System.out.println("errorFile name \t"+jpegFile.getName());
		}
		return exif;
	}
	
	
	

}

