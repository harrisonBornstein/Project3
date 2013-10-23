import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Publications implements Serializable
{
	
	private static final long serialVersionUID = -28491357966762297L;
	/**
	 * 
	 */
	private List<Paper> publications;
	private boolean sortedByPaperTitle;
	private HashMap<String, Author> authors;
 	
	/**
	 * Constructor
	 */
	public Publications()
	{
		publications = new ArrayList<Paper>();
		authors = new HashMap<String, Author>();
	}
	
	/**
	 * 
	 * @param toAdd Paper to add to publications and Hash Map
	 * @return whether or not the add was successful
	 */
	public boolean addPaper(Paper toAdd)
	{
		publications.add(toAdd);
		//for each author of the Paper check to see if we already made an Author object for him
		for (int i =0; i < toAdd.getAuthors().size(); ++i)
		{
			String author = toAdd.getAuthors().get(i).trim();
			
			//if the author does not have an object yet
			if(!authors.containsKey(author))
			{
				//make list with the paperTitle in it
				List<String> paperTitles = new ArrayList<String>();
				paperTitles.add(toAdd.getPaperTitle());
				
				
				if (author.split(", ").length > 1) 
				{
					//make an Author object and add it to the HashMap
					authors.put(author, new Author(author.split(", ")[0],
							author.split(", ")[1], paperTitles));
				}
				else
				{
					authors.put(author, new Author(author.split(", ")[0],
							"", paperTitles));
				}
			}
			else if (authors.containsKey(author)) //if the author already has an object
			{
				//add the title to the Author object list if it's not already there
				if (!authors.get(author).getPaperTitles().contains(toAdd.getPaperTitle()))
				{
					authors.get(author).getPaperTitles().add(toAdd.getPaperTitle());
				}
			}
			
		}
		return true;
	}
	
	/**
	 * 
	 * @param author author full name: Last, First MI. (if they have a MI)
	 * @return the author object from the Hash Map corresponding to that author
	 */
	public Author searchAuthor(String author)
	{
		return authors.get(author);
	}
	
	/**
	 * 
	 * @return whether or not the sort by authors was successful
	 */
	public boolean sortAuthors()
	{
		Collections.sort(this.publications, Paper.compareAuthors());
		sortedByPaperTitle = false;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the bibliographic sort was successful
	 */
	public boolean sortBiblio()
	{
		Collections.sort(this.publications, Paper.compareAuthors());
		sortedByPaperTitle = false;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the Paper Title sort was successful
	 */
		public boolean sortPaperTitle()
		{
			Collections.sort(this.publications, Paper.comparePaperTitle());
			sortedByPaperTitle = true;
			return true;
		}		
	/**
	 * 
	 * @return whether or not the serial Title sort was successful
	 */
	public boolean sortSerialTitle()
	{
		Collections.sort(this.publications, Paper.compareSerialTitle());
		sortedByPaperTitle = false;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the chronological sort was successful
	 */
	public boolean sortChrono()
	{
		Collections.sort(this.publications, Paper.compareChrono());
		sortedByPaperTitle = false;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the list randomization was successful
	 */
	public boolean sortRandom()
	{
		for (int i = 0; i < publications.size() - 1; i++)
		{
			Collections.swap(publications, i, (int) (Math.random()*(publications.size() - i) + i));
		}
		sortedByPaperTitle = false;
		return true;
	}
	
	
	/**
	 * 
	 * @param title Title of Paper to search for
	 * @return the Paper if found, or null if nothing was found
	 */
	public Paper searchPaperTitle(String title)
	{
		int count = 0;
		if (sortedByPaperTitle) 
		{
			int left = 0;
			int right = publications.size() - 1;
			// While there are elements in the range [left,.., right].
			while (right - left + 1 > 0) {
				// Pick the middle point of the range [left, ... , right].

				int middleIndex = (left + right) / 2;
				Paper middleElement = publications.get(middleIndex);
				int comparisonValue = middleElement.getPaperTitle().compareTo(
						title);
				

				if (comparisonValue < 0) 
				{
					left = middleIndex + 1;
					count++;
				} 
				else if (comparisonValue > 0) 
				{
					right = middleIndex - 1;
					count++;
				} 
				else 
				{
					System.out.println(count);
					return publications.get(middleIndex);
				}
			}
			// If the element was not found.
			return null;
		}	
		else //if unsorted
		{
			for (int i = 0; i < publications.size(); i++)
			{
				count++;
				if (publications.get(i).getPaperTitle().equals(title))
				{
					System.out.println(count);
					return publications.get(i);
				}
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * Prints the contents of publications to the console
	 */
	public void display()
	{
		for (Paper p: publications)
		{
			p.display();
		}
	}
	
	
	
	/**
	 * 
	 * @param index int containing the index of the Paper you wish to get
	 * @return The Paper at that index
	 */
	public Paper getPaper(int index)
	{
		return this.publications.get(index);
	}
	
	/**
	 * 
	 * @return the size of the List
	 */
	public int getSize()
	{
		return this.publications.size();
	}
	
}
