import java.io.Serializable;
import java.util.List;
import java.util.Comparator;


public abstract class Paper implements Comparator<Paper>, Serializable
{

	private static final long serialVersionUID = 4582182765986115613L;
	protected List<String> authors;
	protected String paperTitle;
	protected String serialTitle;
	protected String date;
	protected String url;
	
	/**
	 * 
	 * @return List<String> of the authors of the Paper
	 */
	public List<String> getAuthors()
	{
		return this.authors;
	}
	
	/**
	 * 
	 * @return a String containing the Paper's title
	 */
	public String getPaperTitle()
	{
		return this.paperTitle;
	}
	
	/**
	 * 
	 * @return a String containing the Serial title the Paper was in
	 */
	public String getSerialTitle()
	{
		return this.serialTitle;
	}
	
	/**
	 * 
	 * @return a String containing the date the Paper was published
	 */
	public String getDate()
	{
		return this.date;
	}
	
	/**
	 * 
	 * @return a String containing the Paper's url
	 */
	public String getUrl()
	{
		return this.url;
	}
	
	/**
	 * 
	 * @param newAuthors List<String> to be set as the Paper's authors
	 */
	public void setAuthors(List<String> newAuthors)
	{
		this.authors = newAuthors;
	}
	
	/**
	 * 
	 * @param newPaperTitle String to be set as the Paper's title
	 */
	public void setPaperTitle(String newPaperTitle)
	{
		this.paperTitle = newPaperTitle;
	}
	
	/**
	 * 
	 * @param newSerialTitle String to be set as the serial's title that the Paper was published in
	 */
	public void setSerialTitle(String newSerialTitle)
	{
		this.serialTitle = newSerialTitle;
	}
	
	/**
	 * 
	 * @param newDate String to be set as the date the Paper was published
	 */
	public void setDate(String newDate)
	{
		this.date = newDate;
	}
	
	/**
	 * 
	 * @param newUrl String to be set as the url of the Paper
	 */
	public void setUrl(String newUrl)
	{
		this.url = newUrl;
	}
	
	/**
	 * Compares one paper to another by using the PaperTitle
	 * @return an int 0 is equal, Less than zero Lexicographically less,Larger then zero Lexicographically greater
	 */
	public int compare(Paper paper1, Paper paper2)
    {
    	String paperTitle1 = paper1.getPaperTitle();
		String paperTitle2 = paper2.getPaperTitle();
		return paperTitle1.compareTo(paperTitle2);
    }
	/**
	 * Prints object to console
	 */
	public void display()
	{
		
	}
	
	/**
	 * Compares papers alphabetically using the Paper Title
	 * @return a comparator
	 */
	public static Comparator<Paper> comparePaperTitle() 
	{
		  return new Comparator<Paper>() 
        {
        public int compare(Paper paper1, Paper paper2)
        {
        	String paperTitle1 = paper1.getPaperTitle();
			String paperTitle2 = paper2.getPaperTitle();

			return paperTitle1.compareTo(paperTitle2);
        }
        
		};
    }
	/**
	 *Compares papers alphabetically using the Serial Title
	 *@return a comparator
	 */
	public static Comparator<Paper> compareSerialTitle() 
	{
		  return new Comparator<Paper>() 
        {
        public int compare(Paper paper1, Paper paper2)
        {
        	String paperTitle1 = paper1.getSerialTitle();
			String paperTitle2 = paper2.getSerialTitle();

			return paperTitle1.compareTo(paperTitle2);
        }
        
		};
    }
	/**
	 * Compares papers using the Authors. Alphabetically compares last Names then first names
	 * @return a comparator
	 */
	public static Comparator<Paper> compareAuthors() 
	{
		  return new Comparator<Paper>() 
        {
        public int compare(Paper paper1, Paper paper2)
        {
        	String paperTitle1 = "";
        	String paperTitle2 = "";
        	for(int i =0; paper1.getAuthors().size() > i; ++i)
        	{
        	
        		for (int j = 0; j < paper1.getAuthors().size(); j++)
        		{
        			paperTitle1 += paper1.getAuthors().get(j);
        		}
        		
        		for (int j = 0; j < paper2.getAuthors().size(); j++)
        		{
        			paperTitle2 += paper2.getAuthors().get(j);
        		}
        	
        	}
        	return paperTitle1.compareTo(paperTitle2);
        	
        }
        
		};
    }
	/***
	 * Compares papers chronologically. First by year then by date.
	 * @return a comparator
	 */
	public static Comparator<Paper> compareChrono() 
	{
		  return new Comparator<Paper>() 
        {
        public int compare(Paper paper1, Paper paper2)
        {

        		String[] date1 = paper1.getDate().split(" ");
				String[] date2 = paper2.getDate().split(" ");
				fixDate(date1);
				fixDate(date2);
        		
        		return date1[1].compareTo(date2[1]);
       
        }
        
		};
    }
	/**
	 * In order to compare chronologically it adds a letter to the end of the year based upon the order of the month
	 * @param date 
	 */
	public static void fixDate(String[] date)
	{
		if (date[0].equals("January"))
		{
			date[1] += "a";
		}
		else if (date[0].equals("February"))
		{
			date[1] += "b";
		}
		else if (date[0].equals("March"))
		{
			date[1] += "c";
		}
		else if (date[0].equals("April"))
		{
			date[1] += "d";
		}
		else if (date[0].equals("May"))
		{
			date[1] += "e";
		}
		else if (date[0].equals("June"))
		{
			date[1] += "f";
		}
		else if (date[0].equals("July"))
		{
			date[1] += "g";
		}
		else if (date[0].equals("August"))
		{
			date[1] += "h";
		}
		else if (date[0].equals("September"))
		{
			date[1] += "i";
		}
		else if (date[0].equals("October"))
		{
			date[1] += "j";
		}
		else if (date[0].equals("November"))
		{
			date[1] += "k";
		}
		else if (date[0].equals("December"))
		{
			date[1] += "l";
		}
	}
	
	/**
	 * Display method for GUI. Had to be seperate because JOptionPane needed a returned string
	 * @return A string which describes the paper
	 */
	public String displayForGUI()
	{
		return null;
	}
	
	/**
	 * @return The type of paper either Conference or Journal
	 */
	public String getType() 
	{
		 return null;
	}

			

	

}
