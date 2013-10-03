import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;


public class PublicationsTest {

	@Test
	public void test()
	{
		Publications testPub = new Publications();
		List<String> authors = new ArrayList<String>();
		authors.add("author1");
		authors.add("author2");
		ConPaper test1 = new ConPaper(authors, "aPaperTitle1", "bSerialTitle1",
				"aDate1", " September 2000", "aPages1");
		Journal test2 = new Journal(authors, "bPaperTitle1", "aSerialTitle1","bVolumeAndPages1",
				" July 1990", "bBurl1");
		testPub.addPaper(test2);
		testPub.addPaper(test1);
		testPub.sortAuthors();
		Assert.assertEquals(testPub.getPaper(0), test2); //testing sort author
		testPub.sortBiblio();
		Assert.assertEquals(testPub.getPaper(0), test2); //testing biblio
		testPub.sortPaperTitle();
		Assert.assertEquals(testPub.getPaper(0), test1); //testing sortPaper
		testPub.sortSerialTitle();
		Assert.assertEquals(testPub.getPaper(0), test2); //testing serialTitle
		testPub.sortChrono();
		Assert.assertEquals(testPub.getPaper(0), test2);//testing chrono
		
		
	}

}
