import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;


public class PubList extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public PubList(Publications data) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		ArrayList<String> pubInfo = new ArrayList();
		for(int i=0; data.getSize()>i; ++i)
		{
			pubInfo.add("Type: " + data.getPaper(i).getType());
			pubInfo.add("Paper Title: " + data.getPaper(i).getPaperTitle());
			pubInfo.add("Serial Title: " + data.getPaper(i).getSerialTitle());
			pubInfo.add("Authors: " + data.getPaper(i).getAuthors().toString());
			pubInfo.add("Publication Date: " + data.getPaper(i).getDate());
			pubInfo.add(" ");
		}
		
		//String[] columnNames = {"Serial Title", "Paper Title", "Authors", "Publication Date"};
//		JTable jTable1 = new javax.swing.JTable(pubInfo, columnNames);
//		scrollPane.setViewportView(jTable1);
		
		
		JList list = new JList(pubInfo.toArray());
		scrollPane.setViewportView(list);
	}
	

}
