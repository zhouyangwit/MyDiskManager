package DiskManager_5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// import javax.swing.JOptionPane;

public class getMD5 {
	private static char[] hexChar={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
	public String getNormalFileMD5(File file)
	{
		String value=null;
		FileInputStream in=null;
		if(file.length()<1*1024*1024*1024){	
			try {
				in=new FileInputStream(file);
				MappedByteBuffer byteBuffer=in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
				MessageDigest md5=MessageDigest.getInstance("MD5");
				md5.update(byteBuffer);
				BigInteger bi=new BigInteger(1,md5.digest());
				value=bi.toString(16).toUpperCase();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
						e.printStackTrace();
			return null;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}catch(IllegalArgumentException e){
				//			e.printStackTrace();
				System.out.print("\t��ȡMD5ֵʧ�ܣ����Է���2\n");
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return getBigFileMD5(file);
			}	
			finally
			{
				if(in!=null)
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
			}
		}
		
	else{
			value=getBigFileMD5(file);
		}
//		System.out.println("��ȡMD5ֵ�ɹ�");
		return value;
	}
	
	
	public String getBigFileMD5(File file)
	{
//		System.out.print("�ļ����󣬲��÷���2��ȡMD5\t");
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
//		System.out.println("\t������ɣ�MD5\t"+value);
		return value;
	}
	
	
	
	
}
