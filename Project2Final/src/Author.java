import java.io.Serializable;
import java.util.List;


public class Author implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2105216951242410894L;
	private String lastName;
	private String firstName;
	private List<Paper> papers;
	
	/**
	 * Constructs an Author Object
	 */
	public Author(String lastName, String firstName, List<Paper> papers)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.papers = papers;
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
	public List<Paper> getPapers()
	{
		return papers;
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
	public  void addToList(Paper newPaper)
	{
		papers.add(newPaper);
	}
}
