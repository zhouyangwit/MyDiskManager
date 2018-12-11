package DiskManager_7;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class DiskManagerPanel extends JFrame implements  ActionListener,DiskManagerBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1038429868566833417L;
	
	JButton choosefolder=new JButton("choose file");
	JButton start=new JButton("get start");
	JButton md5=new JButton("get MD5");
	JButton hashcode=new JButton("get hashcode");
	JButton open=new JButton("open file");
	JButton clean=new JButton("clean");
	JButton record=new JButton("Record");
	JButton query=new JButton("Query");
	JButton update=new JButton("update");
	
	JTextArea jtf=new JTextArea(1,75);
	JTextArea filelist=new JTextArea(21,38);
	JTextArea folderlist=new JTextArea(21,38);
	
	JScrollPane jsp_filelist=new JScrollPane(filelist);
	JScrollPane jsp_folderlist=new JScrollPane(folderlist);
	JScrollPane jsp_jtf=new JScrollPane(jtf);
	
	public DiskManagerPanel()
	{
	
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.add(choosefolder);
		this.add(start);
		this.add(hashcode);
		this.add(md5);
		this.add(open);
		this.add(record);
		this.add(update);
		this.add(query);
		this.add(clean);
		
		this.add(jsp_folderlist);
		this.add(jsp_filelist);
		
		this.add(jsp_jtf);
		jsp_filelist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp_filelist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		jsp_folderlist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp_folderlist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
