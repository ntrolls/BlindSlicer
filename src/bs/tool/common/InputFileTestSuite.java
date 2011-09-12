/**
 * 
 */
package bs.tool.common;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import bs.data.Test;
import bs.data.TestSuite;

/**
 * @author ntrolls
 *
 */
public class InputFileTestSuite implements TestSuite
{
	Vector<Test> tests = new Vector<Test>();
	
	public InputFileTestSuite(String inputFileFolder)
	{
		File testFolder = new File(inputFileFolder);
		File[] testFiles = testFolder.listFiles();
		for(File f: testFiles)
		{
			tests.add(new StringInputTest(f));
		}
	}
	
		
	/* (non-Javadoc)
	 * @see bs.data.TestSuite#getTests()
	 */
	@Override
	public Collection<Test> getTests()
	{
		return tests;
	}

}
