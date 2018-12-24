package DiskManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Log {
	
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
	
	public void errLog()
	{
		
	}
	

}
