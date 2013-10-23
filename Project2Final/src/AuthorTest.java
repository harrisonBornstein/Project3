import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;


public class AuthorTest {

	
	@Test
	public void testAuthor() 
	{
		List<String> titles = new ArrayList<String>();
		titles.add("Cool Book");
		
		Author me = new Author("Kevin", "Reimnitz", titles);
		Assert.assertEquals(me.getFirstName(), "Kevin");
		Assert.assertEquals(me.getLastName(), "Reimnitz");
		Assert.assertEquals(me.getPaperTitles(), titles);
	}


	

}
