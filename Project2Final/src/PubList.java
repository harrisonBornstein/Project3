import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PubList extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public PubList(Publications data) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 440, 317);
		contentPane.add(scrollPane);
		ArrayList<String> pubInfo = new ArrayList();
		for(int i=0; data.getSize()>i; ++i)
		{
			pubInfo.add("Type: " + data.getPaper(i).getType());
			pubInfo.add("Paper Title: " + data.getPaper(i).getPaperTitle());
			pubInfo.add("Serial Title: " + data.getPaper(i).getSerialTitle());
			pubInfo.add("Authors: " + data.getPaper(i).getAuthors().toString());
			pubInfo.add("Page Numbers: " + data.getPaper(i).getPages());
			pubInfo.add("Publication Date: " + data.getPaper(i).getDate());
			pubInfo.add(" ");
		}
		
		//String[] columnNames = {"Serial Title", "Paper Title", "Authors", "Publication Date"};
//		JTable jTable1 = new javax.swing.JTable(pubInfo, columnNames);
//		scrollPane.setViewportView(jTable1);
		
		
		JList list = new JList(pubInfo.toArray());
		scrollPane.setViewportView(list);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnExit.setBounds(149, 336, 117, 29);
		contentPane.add(btnExit);
	}
}
