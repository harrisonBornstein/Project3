import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JList;


public class jFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3595897893932351336L;
	private JPanel contentPane;
	private JTextField textField;
	private Publications pub = new Publications();
	

		/**
		 * Create the frame.
		 * @throws IOException 
		 */
		public jFrame() throws IOException {
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 663, 192);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			JPanel panel = new JPanel();
			
			contentPane.setLayout(null);
			contentPane.setLayout(null);
			textField = new JTextField();
			textField.setBounds(137, 4, 406, 28);
			contentPane.add(textField);
			textField.setColumns(10);
			
			/*Reading in File*/
			
			
			
		
			JButton btnPrintToScreen = new JButton("Print to Screen");
			btnPrintToScreen.setBounds(5, 44, 117, 29);
			btnPrintToScreen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					PubList list = new PubList(pub);
					list.setVisible(true);
				}
			});
			contentPane.add(btnPrintToScreen);
			
			JButton btnPrintToFile = new JButton("Print to File");
			btnPrintToFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						printToFile(pub);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnPrintToFile.setBounds(123, 44, 117, 29);
			contentPane.add(btnPrintToFile);
			
			JLabel lblSortBy = new JLabel("Sort By:");
			lblSortBy.setBounds(15, 82, 61, 16);
			contentPane.add(lblSortBy);
			
			JButton btnRandom = new JButton("Random");
			btnRandom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					pub.sortRandom();
				}
			});
			btnRandom.setBounds(65, 77, 117, 29);
			contentPane.add(btnRandom);
			
			JButton btnPaperTitle = new JButton("Paper Title");
			btnPaperTitle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pub.sortPaperTitle();
				}
			});
			btnPaperTitle.setBounds(181, 77, 117, 29);
			contentPane.add(btnPaperTitle);
			
			JButton btnSerialTitle = new JButton("Serial Title");
			btnSerialTitle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pub.sortSerialTitle();
				}
			});
			btnSerialTitle.setBounds(296, 77, 117, 29);
			contentPane.add(btnSerialTitle);
			
			JButton btnDate = new JButton("Chronological");
			btnDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pub.sortChrono();
				}
			});
			btnDate.setBounds(412, 77, 117, 29);
			contentPane.add(btnDate);
			
			JButton btnAuthors = new JButton("Authors");
			btnAuthors.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pub.sortAuthors();
				}
			});
			btnAuthors.setBounds(527, 77, 117, 29);
			contentPane.add(btnAuthors);
			
			JButton btnFindAuthor = new JButton("Find Author");
			btnFindAuthor.setBounds(243, 44, 117, 29);
			contentPane.add(btnFindAuthor);
			
			JLabel lblPleaseImportFile = new JLabel("Please Import File:");
			lblPleaseImportFile.setBounds(15, 11, 197, 16);
			contentPane.add(lblPleaseImportFile);
			
			JButton btnGraphs = new JButton("Display Graphs");
			btnGraphs.setBounds(65, 118, 191, 29);
			btnGraphs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					String search = JOptionPane.showInputDialog ("Please input an author to display graphical data:"); 
					
				}
			});
			contentPane.add(btnGraphs);
			
			JButton btnFindAuthors = new JButton("Find Authors");
			btnFindAuthors.setBounds(268, 118, 199, 29);
			contentPane.add(btnFindAuthors);
			
			JButton btnImprot = new JButton("Import");
			btnImprot.setBounds(540, 5, 117, 29);
			btnImprot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String fileName = textField.getText();
					Publications conTest = new Publications();
					Publications journalTest = new Publications();
					ArrayList<String> read = new ArrayList<String>();
					Object[] options = {"Text", "Binary", "Exit"};
					String optionPane = "Is the file binary or text data?";
					int response = JOptionPane.showOptionDialog(null, optionPane, "Academic Paper already in the Collection", JOptionPane.PLAIN_MESSAGE, 1, null, options, options[0]);
					if(response ==0)
					{
						try {
							read= readLines(fileName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conTest = conSplit(read);
						journalTest =journalSplit(read);
						for(int i =0; conTest.getSize()>i;++i)
						{
							pub.addPaper(conTest.getPaper(i));
						}
						
						for(int i =0; journalTest.getSize()>i;++i)
						{
							pub.addPaper(journalTest.getPaper(i));
						}
						
					}
					if(response ==1)
					{
						//Binary Read File
					}
					if(response == 2)
					{
						
					}

					textField.setText(null);	
					
				}
			});
			contentPane.add(btnImprot);
			
			JButton btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnExit.setBounds(527, 118, 117, 29);
			contentPane.add(btnExit);
		}
		public static ArrayList<String> readLines(String fileName) throws IOException
		{
			
			FileReader fr = new FileReader(fileName);
			ArrayList<String> myArray = new ArrayList<String>();
			BufferedReader br = new BufferedReader(fr);
			String nextLine;
			nextLine = br.readLine();

			while (nextLine != null)
			{
				myArray.add(nextLine);			
				nextLine = br.readLine();
			}
		
			br.close();
			return myArray;
		}
		/**
		 * Takes the lines as an ArrayList<Sring> from the data file and makes all the Conference Papers an Object
		 * @param lines String from the data file as an ArrayList()
		 * @return a publication of only Conference Papers
		 */
		public Publications conSplit(ArrayList<String> lines)
		{
				Publications pub = new Publications();
				ArrayList<String> conferenceLines = new ArrayList<String>();
			
				for(int i =0; lines.size() > i; ++i)
				{
					if(lines.get(i).toString().equals("Conference Paper"))
					{
						if(lines.get(i+6).toString() != null)
						{
							conferenceLines.add(lines.get(i).toString());
							conferenceLines.add(lines.get(i+1).toString());
							conferenceLines.add(lines.get(i+2).toString());
							conferenceLines.add(lines.get(i+3).toString());
							conferenceLines.add(lines.get(i+4).toString());
							conferenceLines.add(lines.get(i+5).toString());
							conferenceLines.add(lines.get(i+6).toString());
						}
						else
						{
							conferenceLines.add(lines.get(i).toString());
							conferenceLines.add(lines.get(i+1).toString());
							conferenceLines.add(lines.get(i+2).toString());
							conferenceLines.add(lines.get(i+3).toString());
							conferenceLines.add(lines.get(i+4).toString());
							conferenceLines.add(lines.get(i+5).toString());
						}
					}
				}
				for(int j =0; conferenceLines.size() > j;j = j+7)
				{
						ArrayList<String> authors = new ArrayList<String>();
						authors.addAll(Arrays.asList(conferenceLines.get(j+1).toString().split(";")));
						pub.addPaper(new ConPaper(authors,conferenceLines.get(j+2).toString(),conferenceLines.get(j+3).toString(),conferenceLines.get(j+4).toString(),conferenceLines.get(j+5).toString(),conferenceLines.get(j+6).toString()));
				}
				return pub;
						
		
		}
		/**
		 * Takes the lines as an ArrayList<Sring> from the data file and makes all the Journals an object
		 * @param lines Data from the file
		 * @return Publication consisting only of journals
		 */
		public static Publications journalSplit(ArrayList<String> lines)
		{
				Publications journalPub = new Publications();
				ArrayList<String> journalLines = new ArrayList<String>();
			
				for(int i =0; lines.size() > i; ++i)
				{
					if(lines.get(i).toString().equals("Journal Article"))
					{
						if(lines.get(i+6).toString() != null)
						{
							journalLines.add(lines.get(i).toString());
							journalLines.add(lines.get(i+1).toString());
							journalLines.add(lines.get(i+2).toString());
							journalLines.add(lines.get(i+3).toString());
							journalLines.add(lines.get(i+4).toString());
							journalLines.add(lines.get(i+5).toString());
							journalLines.add(lines.get(i+6).toString());
						}
						else
						{
							journalLines.add(lines.get(i).toString());
							journalLines.add(lines.get(i+1).toString());
							journalLines.add(lines.get(i+2).toString());
							journalLines.add(lines.get(i+3).toString());
							journalLines.add(lines.get(i+4).toString());
							journalLines.add(lines.get(i+5).toString());
						}
					}
				}

				for(int j =0; journalLines.size() > j; j = j+7)
				{
						ArrayList<String> authors = new ArrayList<String>();
						authors.addAll(Arrays.asList(journalLines.get(j+1).toString().split(";")));
						journalPub.addPaper(new Journal(authors,journalLines.get(j+2).toString(),journalLines.get(j+3).toString(),journalLines.get(j+4).toString(),journalLines.get(j+5).toString(),journalLines.get(j+6).toString()));
				}
				return journalPub;
		}
		public static void printToFile(Publications publication) throws IOException
		{
			
			BufferedReader inputReader = new BufferedReader(new InputStreamReader( System.in ) );
			System.out.print( "Please give a name for the output file: " );
			String blank = "";
			String fileName = inputReader.readLine();
			FileWriter outfile = new FileWriter(fileName);
			for(int i =0; i < publication.getSize(); ++i)
			{
				/*string starts blank and loop formats all the Papers for the dialog*/
				blank += publication.getPaper(i).getType() + " " +(i+1) + ":\n" + publication.getPaper(i).displayForGUI() + "\n" + "\n"; 

			}
			BufferedWriter bw = new BufferedWriter(outfile);
			bw.write(blank);
			bw.newLine();
			bw.close();
		}	
}
