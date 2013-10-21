import java.util.List;


public class Author {
	
	
	private String lastName;
	private String firstName;
	private List<String> paperTitles;
	
	/**
	 * Constructs an Author Object
	 */
	public Author(String lastName, String firstName, List<String> listOfPapers)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.paperTitles = listOfPapers;
	}
	
	public String getFirstName() 
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	public List<String> getPaperTitles()
	{
		return paperTitles;
	}
	public void setFirstName(String newFirstName)
	{
		firstName = newFirstName;
	}
	public void setLastName(String newLastName)
	{
		lastName = newLastName;
	}
	public  void addToList(String newTitle)
	{
		paperTitles.add(newTitle);
	}
}
