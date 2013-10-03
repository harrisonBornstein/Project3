import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;


public class Driver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		System.out.println("To use GUI type 1\nTo use console type 2");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader( System.in ) );
		String answer = inputReader.readLine();
		if(answer.equals("1"))
			GUI();
		else if(answer.equals("2"))
			console();
		else
			System.exit(0);
		
		
		
	}
	/**
	 * Reads lines from a file and puts each line into an index in an ArrayList
	 * @param fileName name of the file
	 * @return an ArrayList each line is a string
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
	public static Publications conSplit(ArrayList<String> lines)
	{
			Publications pub = new Publications();
			List<String> conferenceLines = new ArrayList<String>();
		
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
					List<String> authors = new ArrayList<String>();
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
			List<String> journalLines = new ArrayList<String>();
		
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
					List<String> authors = new ArrayList<String>();
					authors.addAll(Arrays.asList(journalLines.get(j+1).toString().split(";")));
					journalPub.addPaper(new Journal(authors,journalLines.get(j+2).toString(),journalLines.get(j+3).toString(),journalLines.get(j+4).toString(),journalLines.get(j+5).toString(),journalLines.get(j+6).toString()));
			}
			return journalPub;
	}
	/**
	 * Runs the program through the console
	 * @throws IOException
	 */
	public static void console() throws IOException
	{
		Boolean run = true;
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader( System.in ) );
		System.out.print( "Please Type The Name of The File Where The Data is Stored: " );
		String fileName = inputReader.readLine(); 
		Publications conTest = new Publications();
		Publications journalTest = new Publications();
		ArrayList<String> read = new ArrayList<String>();
		read = readLines(fileName);
		conTest = conSplit(read);
		journalTest =journalSplit(read);
		Publications pub = new Publications();
		/*
		for(int i =0; conTest.getSize()>i; ++i)
		{
			pub.addPaper(conTest.getPaper(i));
		}
		*/
		
		for(int i =0; conTest.getSize()>i;++i)
		{
			pub.addPaper(conTest.getPaper(i));
		}
		
		for(int i =0; journalTest.getSize()>i;++i)
		{
			pub.addPaper(journalTest.getPaper(i));
		}
		
		while(run == true)
		{
			System.out.print("\nTo Sort the data please type:\nBI for bibliographic\nAN for author names\nPT for paper title\nST for serial title\nCH for chronological\nR for random");
			System.out.println("\nAlternatively:\nEnter PS to Print To Screen\nEnter PF to print to file\nEnter S to Search\nIf you want to exit type E");
			String choice = inputReader.readLine();
			if(choice.equalsIgnoreCase("BI"))
			{
				if (pub.sortBiblio())
				{
					System.out.println("Collection Sorted");
				}
				else
				{
					System.out.println("Sort Failed");
				}
			}
			else if(choice.equalsIgnoreCase("AN"))
			{
				if (pub.sortAuthors())
				{
					System.out.println("Collection Sorted");
				}
				else
				{
					System.out.println("Sort Failed");
				}
			}
			else if(choice.equalsIgnoreCase("PT"))
			{
				if (pub.sortPaperTitle())
				{
					System.out.println("Collection Sorted");
				}
				else
				{
					System.out.println("Sort Failed");
				}
			}
			else if(choice.equalsIgnoreCase("ST"))
			{
				if (pub.sortSerialTitle())
				{
					System.out.println("Collection Sorted");
				}
				else
				{
					System.out.println("Sort Failed");
				}
			}
			else if(choice.equalsIgnoreCase("CH"))
			{
				if (pub.sortChrono())
				{
					System.out.println("Collection Sorted");
				}
				else
				{
					System.out.println("Sort Failed");
				}
			}
			else if(choice.equalsIgnoreCase("R"))
			{
				if (pub.sortRandom())
				{
					System.out.println("Collection Sorted");
				}
				else
				{
					System.out.println("Sort Failed");
				}
					
			}
			else if(choice.equalsIgnoreCase("PS"))
			{
				pub.display();
			}
			else if(choice.equalsIgnoreCase("PF"))
			{
				printToFile(pub);
			}
			else if(choice.equalsIgnoreCase("S"))
			{
				System.out.println("Please Input the title to search by:");
				String search = inputReader.readLine();
				int index = pub.searchPaperTitle(search);
				if (index == -1)
				{
					System.out.println("Title not found");
				}
				else
				{
					pub.getPaper(index).display();
				}
			}
			else if(choice.equalsIgnoreCase("E"))
			{
				System.out.println("GoodBye! Thank you for running the Program.");
				System.exit(0);
			}
			
		}
	
				

	}
	
	/**
	 * Writes the publication with a file name provided by user 
	 * @param publication the data to be printed
	 */
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
//		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//		objectOutputStream.writeObject(publication);
//		objectOutputStream.close();
	}
	/**
	 * Separate write file method for the GUI that uses JOptionPane. This is done because input is received with the JOption dialogue
	 * @param publication Data to be written
	 * @param fileName Name of the file to be written
	 * @throws IOException
	 */
	public static void printToFileGUI(Publications publication, String fileName) throws IOException
	{
		String blank ="";
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
//		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//		objectOutputStream.writeObject(publication);
//		objectOutputStream.close();
	}
	/**
	 * Uses JOption pane as a GUI to run the program
	 * @throws IOException
	 */
	public static void GUI() throws IOException
	{
		String fileName; //for publisher search
		boolean runProgram = true;
		
		Object[] options = { "Sort Data", "Search", "Print to Screen or File", "Exit" };
		Object[] options2 = {"Sort Bibliographic", "Sort by Authors", "Sort by Paper Title", "Sort by Serial Title", "Sort Chronologically", "Random Sort", "Exit"};
		Object[] options3 = {"Print to Screen", "Print to File", "Exit"};
		fileName = JOptionPane.showInputDialog ("Please Type The Name of The File Where The Data is Stored: "); 
		Publications conTest = new Publications();
		Publications journalTest = new Publications();
		ArrayList<String> read = new ArrayList<String>();
		read = readLines(fileName);
		conTest = conSplit(read);
		journalTest =journalSplit(read);
		Publications pub = new Publications();
		
		for(int i =0; conTest.getSize()>i;++i)
		{
			pub.addPaper(conTest.getPaper(i));
		}
		
		for(int i =0; journalTest.getSize()>i;++i)
		{
			pub.addPaper(journalTest.getPaper(i));
		}
		
		while(runProgram ==true)
		{	

		int answer = JOptionPane.showOptionDialog(null, "What would you liked to do?", "Confirmation", JOptionPane.PLAIN_MESSAGE, 1, null, options, options[2]);
			if(answer == 0)
			{
				int sort = JOptionPane.showOptionDialog(null, "What would you liked to do?", "Confirmation", JOptionPane.PLAIN_MESSAGE, 1, null, options2, options[2]);
				if (sort == 0)
					{
						if (pub.sortBiblio())
						{
							JOptionPane.showMessageDialog(null, "Collection Sorted.", "", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Sort Failed.", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				else if(sort == 1)
				{
					if (pub.sortAuthors())
					{
						JOptionPane.showMessageDialog(null, "Collection Sorted.", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sort Failed.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(sort == 2)
				{
					if (pub.sortPaperTitle())
					{
						JOptionPane.showMessageDialog(null, "Collection Sorted.", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sort Failed.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(sort == 3)
				{
					if (pub.sortSerialTitle())
					{
						JOptionPane.showMessageDialog(null, "Collection Sorted.", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sort Failed.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(sort == 4)
				{
					if (pub.sortChrono())
					{
						JOptionPane.showMessageDialog(null, "Collection Sorted.", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sort Failed.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(sort ==5)
				{
					if (pub.sortRandom())
					{
						JOptionPane.showMessageDialog(null, "Collection Sorted.", "", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sort Failed.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(sort ==6)
				{
					JOptionPane.showMessageDialog(null, "Goodbye! Thanks for running the program", "", JOptionPane.INFORMATION_MESSAGE);	
					System.exit(0);
				}
			}	
			if(answer == 1)
			{
				String search = JOptionPane.showInputDialog ("Please Input the title to search by:"); 
				int index = pub.searchPaperTitle(search);
				if (index == -1)
				{
					JOptionPane.showMessageDialog(null, "Title Not Found", "", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					String blank = "";
					/*string starts blank and formats the Papers for the dialog*/
					blank += pub.getPaper(index).getType()+":\n" + pub.getPaper(index).displayForGUI() + "\n" + "\n"; 
					JOptionPane.showMessageDialog(null,blank, "", JOptionPane.INFORMATION_MESSAGE);
				}	
			
				
			}
			if(answer == 2)
			{
				String blank = "";
				int print = JOptionPane.showOptionDialog(null, "What would you liked to do?", "Confirmation", JOptionPane.PLAIN_MESSAGE, 1, null, options3, options[2]);
				if(print == 0)
				{
					for(int i =0; i < pub.getSize(); ++i)
					{
						/*string starts blank and loop formats all the Papers for the dialog*/
						blank += pub.getPaper(i).getType() + " " +(i+1) + ":\n" + pub.getPaper(i).displayForGUI() + "\n" + "\n"; 
			
					}
					JOptionPane.showMessageDialog(null,blank, "", JOptionPane.INFORMATION_MESSAGE);
				}
				if(print == 1)
				{

					String file = JOptionPane.showInputDialog ("Please Give a name for the output file:");
					file.trim();
					printToFileGUI(pub,file);
					JOptionPane.showMessageDialog(null, "File Saved", "", JOptionPane.INFORMATION_MESSAGE);	
				}
				if(print ==3)
				{
					JOptionPane.showMessageDialog(null, "Goodbye! Thanks for running the program", "", JOptionPane.INFORMATION_MESSAGE);	
					System.exit(0);
				}
			}
			
			/*Exits if they click exit*/
			if(answer == 3)
			{
				JOptionPane.showMessageDialog(null, "Goodbye! Thanks for running the program.", "", JOptionPane.INFORMATION_MESSAGE);	
				System.exit(0);
			}
		}
	}
			
		

}
