package DiskManager_5;

import java.io.File;

public class DeleteBlankDir {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		deleteDir(new File("H:\\สำฦต\\"));
	}

	static public boolean deleteDir(File path)
	{
		if(path.exists())
		{
			File files[]=path.listFiles();
			for(int i=0;i<files.length;i++)
			{
				if(files[i].isDirectory())
				{
	//				System.out.println("new directory detected    "+files[i]);
					deleteDir(files[i]);
				}
			}
		}
		boolean flag=path.delete();
		if(flag)
			System.out.println("delete directory   "+path);
		return flag;
	}
	
	public void DeleteFileContain(File path)
	{
		
	}
	
	
}
