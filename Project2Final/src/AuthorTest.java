import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;


public class AuthorTest {

	
	@Test
	public void testAuthor() 
	{
		List<Paper> titles = new ArrayList<Paper>();
		titles.add(new Journal());
		
		Author me = new Author("Kevin", "Reimnitz", titles);
		Assert.assertEquals(me.getFirstName(), "Kevin");
		Assert.assertEquals(me.getLastName(), "Reimnitz");
		Assert.assertEquals(me.getPapers(), titles);
	}


	

}
