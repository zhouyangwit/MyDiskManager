package DiskManager_5;

import javax.swing.JFrame;

public class TaskManager extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -828756653762133904L;


	public TaskManager()
	{
		TaskManagerPanel panel=new TaskManagerPanel();
		this.add(panel);
		this.setTitle("MyDiskManager 5.0");
		this.setLocation(450, 200);
		this.setSize(840, 520);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		TaskManager tm=	new TaskManager();
		tm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
	
}
