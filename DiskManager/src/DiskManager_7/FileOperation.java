package DiskManager_7;

import java.io.File;

public class FileOperation implements DiskManagerBean{

	public void listfile(File file)
	{
		Database database=new Database();
		if(file.isDirectory())
		{
			File[] f=file.listFiles();
			for(int i=0;i<f.length;i++)
			{
				if(f[i].isFile())
				{
					database.add(file);
				}
				else if(f[i].isDirectory())
				{
					listfile(f[i]);
				}
			}
		}
	}
	
	public void DeleteFile(File file)
	{
		file.delete();
		System.out.println("É¾³ýÎÄ¼þ"+file.getAbsolutePath());
	}
	
	
}
