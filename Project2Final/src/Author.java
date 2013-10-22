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
	
	/**
	 * 
	 * @return String containing the First Name of the Author object
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	
	/**
	 * 
	 * @return String containing the Last Name of the Author object
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * 
	 * @return List<String> of the Paper Titles written or co-written by that Author
	 */
	public List<String> getPaperTitles()
	{
		return paperTitles;
	}
	
	/**
	 * 
	 * @param newFirstName String to be set as the First name of the Author
	 */
	public void setFirstName(String newFirstName)
	{
		firstName = newFirstName;
	}
	
	/**
	 * 
	 * @param newLastName String to be set as the Last name of the Author
	 */
	public void setLastName(String newLastName)
	{
		lastName = newLastName;
	}
	
	/**
	 * 
	 * @param newTitle String to be added to the Title list of the Author
	 */
	public  void addToList(String newTitle)
	{
		paperTitles.add(newTitle);
	}
}
