import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class jFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3595897893932351336L;
	private JPanel contentPane;
	private JTextField textField;
	private static Publications pub = new Publications();
	

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
			new JPanel();
			
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
			
			
			JLabel lblPleaseImportFile = new JLabel("Please Import File:");
			lblPleaseImportFile.setBounds(15, 11, 197, 16);
			contentPane.add(lblPleaseImportFile);
			
			JButton btnGraphs = new JButton("Display Graphs");
			btnGraphs.setBounds(6, 118, 155, 29);
			btnGraphs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					String search = JOptionPane.showInputDialog ("Please input an author to display graphical data:"); 
					String optionPane = "What data would you like to show?";
					Object[] options = {"Type of Publications", "Publications Per Year", "Conference Papers Per Year", "Journal Articles Per Year", "Number of Co-Authors Per Publicaiton"};
					if(pub.searchAuthor(search) != null)
					{
						int response = JOptionPane.showOptionDialog(null, optionPane, "Academic Paper already in the Collection", JOptionPane.PLAIN_MESSAGE, 1, null, options, options[0]);
						if(response == 0)
						{
							 

							
							double[] data = {countTypes(pub.searchAuthor(search))[0], countTypes(pub.searchAuthor(search))[1]};
							String[] names = {"Conference Papers", "Journals"};
							BarGraph paperTypes = new BarGraph(data, names, "Types of Papers by " + search);
							paperTypes.setVisible(true);
					
							JFrame f = new JFrame();
							f.setSize(500, 300);
							

							f.getContentPane().add(paperTypes);
							f.setVisible(true);

						}
						if(response == 1)
						{
							ArrayList<int[]> years = countYears(pub.searchAuthor(search)); //get data from author
							
							for (int g = 0; g < years.size(); g++) //order data in list by year, most recent year first
							{
								for (int i = 0; i < years.size()-g - 1; ++i)
								{
									if (years.get(i)[0] > years.get(i + 1)[0]) 
									{
										Collections.swap(years, i, i + 1);
									}
								}
							}
							int range = years.get(years.size() -1)[0] - years.get(0)[0];
							double[] pubsInYear = new double[range +1]; //param to pass into BarGraph
							String[] stringYears = new String[range +1]; //param to pass into BarGraph
							
							for(int i = 0; i <= range; i++) //check if there is a pub at that year and find index
							{	
								int index =0;
								boolean containsYear = false;
								for(int j =0; j < years.size(); ++j)
								{
									if(years.get(j)[0] == i + years.get(0)[0])
									{
										index =j;
										containsYear = true;
									}
								}
								if(containsYear) //if there is a pub(s), add how many to the array
								{
									pubsInYear[i] = years.get(index)[1];
								}
								else //if not set the value to zero
								{
									pubsInYear[i]= 0;
								}
								stringYears[i] = "" +(i +years.get(0)[0]); // add the corresponding year to the string array
							}
							
//							for(int i =0; pubsInYear.length > i;++i)
//							{
//								System.out.println(pubsInYear[i]);
//								System.out.println("String Years " + stringYears[i]);
//							}
							BarGraph paperTypes = new BarGraph(pubsInYear, stringYears, "Papers per Year by " + search);
							paperTypes.setVisible(true);
					
							JFrame f = new JFrame();
							f.setSize(1200, 500);
							

							f.getContentPane().add(paperTypes);
							f.setVisible(true);
						}
						if(response == 2)
						{
							ArrayList<int[]> years = countYears(pub.searchAuthor(search)); //get data from author
							
							for (int g = 0; g < years.size(); g++) //order data in list by year, most recent year first
							{
								for (int i = 0; i < years.size()-g - 1; ++i)
								{
									if (years.get(i)[0] > years.get(i + 1)[0]) 
									{
										Collections.swap(years, i, i + 1);
									}
								}
							}
							int range = years.get(years.size() -1)[0] - years.get(0)[0];
							double[] pubsInYear = new double[range +1]; //param to pass into BarGraph
							String[] stringYears = new String[range +1]; //param to pass into BarGraph
							
							for(int i = 0; i <= range; i++) //check if there is a pub at that year and find index
							{	
								int index =0;
								boolean containsYear = false;
								for(int j =0; j < years.size(); ++j)
								{
									if(years.get(j)[0] == i + years.get(0)[0])
									{
										index =j;
										containsYear = true;
									}
								}
								if(containsYear) //if there is a pub(s), add how many to the array
								{
									pubsInYear[i] = years.get(index)[2];
								}
								else //if not set the value to zero
								{
									pubsInYear[i]= 0;
								}
								stringYears[i] = "" +(i +years.get(0)[0]); // add the corresponding year to the string array
							}
							
							
							BarGraph paperTypes = new BarGraph(pubsInYear, stringYears, "Conference Papers per Year by " + search);
							paperTypes.setVisible(true);
					
							JFrame f = new JFrame();
							f.setSize(1200, 500);
							

							f.getContentPane().add(paperTypes);
							f.setVisible(true);	
						}
						if(response == 3)
						{
ArrayList<int[]> years = countYears(pub.searchAuthor(search)); //get data from author
							
							for (int g = 0; g < years.size(); g++) //order data in list by year, most recent year first
							{
								for (int i = 0; i < years.size()-g - 1; ++i)
								{
									if (years.get(i)[0] > years.get(i + 1)[0]) 
									{
										Collections.swap(years, i, i + 1);
									}
								}
							}
							int range = years.get(years.size() -1)[0] - years.get(0)[0];
							double[] pubsInYear = new double[range +1]; //param to pass into BarGraph
							String[] stringYears = new String[range+1]; //param to pass into BarGraph
							
							for(int i = 0; i <= range; i++) //check if there is a pub at that year and find index
							{	
								int index =0;
								boolean containsYear = false;
								for(int j =0; j < years.size(); ++j)
								{
									if(years.get(j)[0] == i + years.get(0)[0])
									{
										index =j;
										containsYear = true;
									}
								}
								if(containsYear) //if there is a pub(s), add how many to the array
								{
									pubsInYear[i] = years.get(index)[3];
								}
								else //if not set the value to zero
								{
									pubsInYear[i]= 0;
								}
								stringYears[i] = "" +(i +years.get(0)[0]); // add the corresponding year to the string array
							}
							
							for(int i =0; pubsInYear.length > i;++i)
							{
								System.out.println(pubsInYear[i]);
								System.out.println("String Years " + stringYears[i]);
							}
							BarGraph paperTypes = new BarGraph(pubsInYear, stringYears, "Journals per Year by " + search);
							paperTypes.setVisible(true);
					
							JFrame f = new JFrame();
							f.setSize(1200, 500);
							

							f.getContentPane().add(paperTypes);
							f.setVisible(true);	
						}
						if(response == 4)
						{
							
						}
						
					}
				}
			});
			contentPane.add(btnGraphs);
			
			JButton btnFindAuthors = new JButton("Find Author");
			btnFindAuthors.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					String search = JOptionPane.showInputDialog("Please intput the author to search for: ");
					if(search == null)
					{
						
					}
					else
					{
					search.trim();
					PubList list = new PubList(pub.searchAuthor(search),pub);
					list.setVisible(true);
					}
					

					
				}
			});
			btnFindAuthors.setBounds(173, 118, 149, 29);
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
						/*for(int i =0; conTest.getSize()>i;++i)
						{
							pub.addPaper(conTest.getPaper(i));
						}
						
						for(int i =0; journalTest.getSize()>i;++i)
						{
							pub.addPaper(journalTest.getPaper(i));
						}*/
						
					}
					if(response ==1)
					{
						
						try {
							pub = binaryReadPapers(fileName);
						} catch (ClassNotFoundException e1) {
						
							e1.printStackTrace();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
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
			
			JButton btnSearchByPaper = new JButton("Search by Paper Title");
			btnSearchByPaper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					String search = JOptionPane.showInputDialog ("Please Input the title to search by:"); 
					if (search == null)
					{
						JOptionPane.showMessageDialog(null, "Title Not Found", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						PubList list = new PubList(pub.searchPaperTitle(search));
						list.setVisible(true);
					}
				}
			});
			btnSearchByPaper.setBounds(334, 118, 174, 29);
			contentPane.add(btnSearchByPaper);
		}
		
		/**
		 * 
		 * @param fileName File to read in
		 * @return An ArrayList<Sting> of the file's lines
		 * 
		 * @throws IOException
		 */
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
		
		/**
		 * Prints a Publications to a Text or Binary File of specified name
		 * 
		 * @param publication Publications to print to a file
		 * 
		 * @throws IOException
		 */
		public static void printToFile(Publications publication) throws IOException
		{
			Object[] options = {"Text", "Binary", "Exit"};
			new BufferedReader(new InputStreamReader( System.in ) );
			int response = JOptionPane.showOptionDialog(null, "Would you like binary output or text output", "File Output", JOptionPane.PLAIN_MESSAGE, 1, null, options, options[0]);
			if(response == 0)
			{
			String fileName = JOptionPane.showInputDialog ("Please input a name for the file"); 
			String blank = "";
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
			if(response ==1)
			{
				String fileName = JOptionPane.showInputDialog ("Please input a name for the file"); 
				FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(pub);
				objectOutputStream.close();
			}
		}
		
		/**
		 * 
		 * @param filename String of the file name to read in
		 * @return A Publications with the data from the file read in
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		public static Publications binaryReadPapers(String filename) throws IOException, ClassNotFoundException
		{
			
			FileInputStream fileInputStream = new FileInputStream(filename);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Publications papers = (Publications) objectInputStream.readObject();
			objectInputStream.close();
			return papers;
		}
		
		/**
		 * 
		 * @param author author to check
		 * @return an array with [0] =  the number of Conference Papers and [1] = the number of Journals
		 */
		public int[] countTypes(Author author)
		{
			int cons = 0;
			int journs = 0;
			for (int i = 0; i < author.getPaperTitles().size(); i++) //go through list of titles
			{
				if (pub.searchPaperTitle(author.getPaperTitles().get(i)).getType().equals("Conference Paper")) //if the paper is a Conference Paper
				{
					cons++;
				}
				else
				{
					journs++;
				}
			}
			
			int[] types =  {cons, journs};
			
			return types;
		}
		
		/**
		 * 
		 * @param author Author object to get data from
		 * @return An ArrayList<int[]> with each int[] formatted as follows:
		 *          [0]: Year     [1]: Papers in that year     
		 *          [2]:  Conference Papers in that year  [3]: Journals in that year
		 *          
		 */
		public ArrayList<int[]> countYears(Author author)
		{
			ArrayList<int[]> data = new ArrayList<int[]>();
			
			ArrayList<String[]> years = new ArrayList<String[]>();
			
			
			for (String title: author.getPaperTitles())
			{
				String[] yearAndType = {pub.searchPaperTitle(title).getDate().split(" ")[1], pub.searchPaperTitle(title).getType()};
				years.add(yearAndType); //add year as a string
			}
			
			while (years.size() != 0)
			{
				int count = 0;
				int cons = 0;
				int journs = 0;
				String check = years.get(0)[0];
				for (int i = 0; i < years.size(); i++)
				{
					if (years.get(i)[0].equals(check))
					{
						if (years.get(i)[1].equals("Conference Paper"))
						{
							cons++;
						}
						else
						{
							journs++;
						}
						years.remove(i);
						i--;
						count++;
					}
				}	
				int[] pair = {Integer.parseInt(check), count, cons, journs};
				data.add(pair);
			}
			
			
			return data;
			
		}
}
