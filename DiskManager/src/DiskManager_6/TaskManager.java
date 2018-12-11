package DiskManager_6;

import javax.swing.JFrame;

public class TaskManager extends JFrame{
	public TaskManager()
	{
		TaskManagerPanel panel=new TaskManagerPanel();
		this.add(panel);
		this.setTitle("MyDiskManager 6.0----Joseph Chow");
		this.setLocation(450, 200);
		this.setSize(900, 500);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		TaskManager tm=	new TaskManager();
		tm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
	
}
