package DiskManager_7;

import javax.swing.JFrame;

	public class DiskManager extends JFrame{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2525545273246262954L;
		
		
		public DiskManager()
		{
			DiskManagerPanel panel=new DiskManagerPanel();
			this.add(panel);
			this.setTitle("MyDiskManager 6.0----Joseph Chow");
			this.setLocation(450, 200);
			this.setSize(900, 500);
			this.setVisible(true);
	}
	public static void main(String[] args) {
		DiskManager dm=	new DiskManager();
		dm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
