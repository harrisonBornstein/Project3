import java.util.Comparator;
import java.util.List;


public class ConPaper extends Paper implements Comparator<Paper>
{

	private static final long serialVersionUID = 4295941929597929510L;
	private String pages;
	private final String TYPE;

	
	/**
	 * Constructor
	 */
	public ConPaper()
	{
		TYPE = "Conference Paper";//Test
		authors = null;
		paperTitle = null;
		serialTitle = null;
		date = null;
		url = null;
		pages = null;
		
	}
	
	/**
	 * 
	 * @param authors List<String> of authors of the ConPaper
	 * @param paperTitle String containing the Paper's Title 
	 * @param serialTitle String containing the Serial's Title 
	 * @param date String containing the date the ConPaper was published
	 * @param url  String containing the url of the ConPaper
	 * @param pages String containing the pages of the ConPaper in the Serial
	 */
	public ConPaper(List<String> authors, String paperTitle, String serialTitle, String pages,
						String date, String url)
	{
		TYPE = "Conference Paper";
		this.authors  = authors;
		this.paperTitle = paperTitle;
		this.serialTitle = serialTitle;
		this.date = date;
		this.url = url;
		this.pages = pages;
	}
	
	/**
	 * 
	 * @return String containing the Pages of the ConPaper
	 */
	public String getPages()
	{
		return pages;
	}
	
	/**
	 * 
	 * @param newPages String you wish to set as the pages of a ConPaper
	 */
	public void setPages(String newPages)
	{
		pages = newPages;
	}
	
	@Override 
	/**
	 * @return a string saying the object is a Journal
	 */
	public String getType()
	{
		return this.TYPE;
	}
	/**
	 * Prints out to console the contents of an object 
	 */
	public void display()
	{
		System.out.print("\nConference Paper\nAuthors: ");
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
		System.out.println("Pages: " + pages);
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
				
		return "Paper Title: " + paperTitle +"\nSerial Title: " + serialTitle + "\nAuthors: " + authors1 + "\nPages" +
				pages + "\nDate Published: " + date +"\nUrl: " + url1;
	}

	
	
	
}
