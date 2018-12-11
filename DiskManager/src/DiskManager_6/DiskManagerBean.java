package DiskManager_6;

import java.io.File;
import java.text.SimpleDateFormat;

public interface DiskManagerBean {
	public int FileNumber=0;
	public long sumFileLength=0;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String database="E:\\DiskManager\\DiskManager.db";
	
	public void getFileNumber(File file);
	
	

}
