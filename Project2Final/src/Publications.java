import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	private boolean sortedByAuthors;
	private boolean sortedByBiblio;
	private boolean sortedByPaperTitle;
	private boolean sortedBySerialTitle;
	private boolean sortedByChrono;
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
	 * @param toAdd Paper to add to publications
	 * @return whether or not the add was successful
	 */
	public boolean addPaper(Paper toAdd)
	{
		publications.add(toAdd);
		//for each author of the Paper check to see if we already made an Author object for him
		for (String author: toAdd.getAuthors())
		{
			//if the author already has an object
			if (authors.containsKey(author))
			{
				//add the title to the Author object'list
				authors.get(author).addToList(toAdd.getPaperTitle());
			}
			//if the author does not have an object yet
			else if(!authors.containsKey(author))
			{
				//make list with the paperTitle in it
				List<String> paperTitles = new ArrayList<String>();
				paperTitles.add(toAdd.getPaperTitle());
				
				//make an Author object and add it to the HashMap
				authors.put(author, new Author(author.split(", ")[0], author.split(", ")[1], paperTitles));
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the sort by authors was successful
	 */
	public boolean sortAuthors()
	{
		Collections.sort(this.publications, Paper.compareAuthors());
		sortedByBiblio = false;
		sortedByPaperTitle = false;
		sortedBySerialTitle = false;
		sortedByChrono = false;
		sortedByAuthors = true;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the bibliographic sort was successful
	 */
	public boolean sortBiblio()
	{
		Collections.sort(this.publications, Paper.compareAuthors());
		sortedByBiblio = true;
		sortedByPaperTitle = false;
		sortedBySerialTitle = false;
		sortedByChrono = false;
		sortedByAuthors = false;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the Paper Title sort was successful
	 */
		public boolean sortPaperTitle()
		{
			Collections.sort(this.publications, Paper.comparePaperTitle());
			sortedByBiblio = false;
			sortedByPaperTitle = true;
			sortedBySerialTitle = false;
			sortedByChrono = false;
			sortedByAuthors = false;
			return true;
		}		
	/**
	 * 
	 * @return whether or not the serial Title sort was successful
	 */
	public boolean sortSerialTitle()
	{
		Collections.sort(this.publications, Paper.compareSerialTitle());
		sortedByBiblio = false;
		sortedByPaperTitle = false;
		sortedBySerialTitle = true;
		sortedByChrono = false;
		sortedByAuthors = false;
		return true;
	}
	
	/**
	 * 
	 * @return whether or not the chronological sort was successful
	 */
	public boolean sortChrono()
	{
		Collections.sort(this.publications, Paper.compareChrono());
		sortedByBiblio = false;
		sortedByPaperTitle = false;
		sortedBySerialTitle = false;
		sortedByChrono = true;
		sortedByAuthors = false;
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
		sortedByBiblio = false;
		sortedByPaperTitle = false;
		sortedBySerialTitle = false;
		sortedByChrono = false;
		sortedByAuthors = false;
		return true;
	}
	
	
	/**
	 * 
	 * @param title Title of Paper to search for
	 * @return the index of Paper found or -1 if none found
	 */
	public int searchPaperTitle(String title)
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
				count++;

				if (comparisonValue < 0) 
				{
					left = middleIndex + 1;
				} 
				else if (comparisonValue > 0) 
				{
					right = middleIndex - 1;
				} 
				else 
				{
					System.out.println(count);
					return middleIndex;
				}
			}
			// If the element was not found.
			return -1;
		}	
		else //if unsorted
		{
			for (int i = 0; i < publications.size(); i++)
			{
				count++;
				if (publications.get(i).getPaperTitle().equals(title))
				{
					System.out.println(count);
					return i;
				}
			}
		}
		
		return -1;
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
