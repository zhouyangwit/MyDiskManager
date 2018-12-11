package DiskManager_5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	String absolutepath="e:\\DiskManager\\";
	public void log()
	{
		
	}
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private void write(String path,String log)
	{
		try {
			FileWriter fw=new FileWriter(path,true);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(log);
			bw.newLine();
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	Log()
	{
		
	}
	
	public void FolderLog(String log)
	{
		write(absolutepath+"DiskManager_folder.log",log);
	}
	
	Log(String log)
	{
		write(absolutepath+"DiskManager_log.log",log);
	}
	Log(boolean b,String log)
	{
		write(absolutepath+"SQLite_insert_error_"+sdf.format(new Date())+".sql",log);
	}
	
	public void log_update(String log)
	{
		write(absolutepath+"DiskManager_DataUpdate_canReload_"+sdf.format(new Date())+".sql",log);
	}
	
	public void log_xml(String log)
	{
		write(absolutepath+"DiskManager_DataXML_"+sdf.format(new Date())+".xml",log);
	}
	
	public void log_record(String log)
	{
		write(absolutepath+"DiskManager_DataRecord_"+sdf.format(new Date())+".txt",log);
	}
	
}
