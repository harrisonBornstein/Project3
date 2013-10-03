import java.util.List;


public class Journal extends Paper 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4970122214947428573L;
	/**
	 * 
	 */
	private String volumeIssueAndPages;
	private final String TYPE;
	
	/**
	 * Constructor //WHY
	 */
	public Journal()
	{
		TYPE = "Journal";//Test
		this.authors = null;
		this.paperTitle = null;
		this.serialTitle = null;;
		this.volumeIssueAndPages = null;;
		this.date = null;;
		this.url = null;;
	}
	
	/**
	 * Constructor 
	 * 
	 * @param authors List of authors for the new Journal
	 * @param paperTitle Paper Title of the new Journal
	 * @param serialTitle Serial Title of the new Journal
	 * @param volumeIssueAndPages String containing the Volume, Issue, and Pages of the Journal
	 * @param date The date the Journal was published
	 * @param url The url of the Journal
	 */
	public Journal(List<String> authors, String paperTitle, String serialTitle,
			String volumeIssueAndPages, String date, String url)
	{
		this.TYPE = "Journal";
		this.authors = authors;
		this.paperTitle = paperTitle;
		this.serialTitle = serialTitle;
		this.volumeIssueAndPages = volumeIssueAndPages;
		this.date = date;
		this.url = url;
	}
	
	/**
	 * 
	 * @return String containing the Volume, Issue, and Pages of the Journal
	 */
	public String getVolumeIssueAndPages()
	{
		return this.volumeIssueAndPages;
	}
	
	/**
	 * 
	 * @param newVIP String that you want to set the Volume, Issue, and Pages of the Journal as
	 */
	public void setVolumeIssueAndPages(String newVIP)
	{
		this.volumeIssueAndPages = newVIP;
	}
	/**
	 * Prints out to console the contents of an object 
	 */
	public void display()
	{
		System.out.print("\nJournal\nAuthors: ");
		for (int i = 0; i < authors.size(); i++)
		{
			if (i < authors.size() - 1)
			{
				System.out.print(authors.get(i) + "; ");
			}
			else 
			{
				System.out.print(authors.get(i));
			}
		}
		System.out.println("\nPaper Title: " + paperTitle);
		System.out.println("Serial Title: " + serialTitle);
		System.out.println("Volume, Issue, and Pages: " + volumeIssueAndPages);
		System.out.println("Date Published: " + date);
		if (url != null)
		{
			System.out.println("URL: " + url);
		}
		else
		{
			System.out.println("URL: none");
		}
	}

	@Override
	/**
	 * @return a string saying the object is a Journal
	 */
	public String getType()
	{
		return this.TYPE;
	}
	@Override
	/**
	 * Makes the object one string to be displayed in JOptionPane
	 */
	public String displayForGUI()
	{
		String authors1 ="";
		String url1;
		for (int i = 0; i < authors.size(); i++)
		{
			if (i < authors.size() - 1)
			{
				authors1 += authors.get(i) + "; ";
			}
			else 
			{
				authors1 += authors.get(i);
			}
		}	
			if (url != null)
			{
				url1 = this.url;
			}
			else
			{
				url1 = "None";
			}	
				
		return "Paper Title: " + paperTitle +"\nSerial Title: " + serialTitle + "\nAuthors: " + authors1 + "\nVolume Issue And Pages" +
				volumeIssueAndPages + "\nDate Published: " + date +"\nUrl: " + url1;
	}

	

	

	
	
}
