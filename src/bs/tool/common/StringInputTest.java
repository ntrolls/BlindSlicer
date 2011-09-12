/**
 * 
 */
package bs.tool.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import bs.data.Test;

/**
 * @author ntrolls
 *
 */
public class StringInputTest implements Test
{
	private String input = null;
	
	public StringInputTest(File inputDataFile)
	{
		readFromFile(inputDataFile);
	}
	
	public StringInputTest(String input)
	{
		this.input = input;
	}

	/* (non-Javadoc)
	 * @see bs.tool.Test#inputValue()
	 */
	@Override
	public String inputValue()
	{
		return input;
	}
	
	public void readFromFile(File inputDataFile)
	{
		String line;		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(inputDataFile));
			while((line = reader.readLine()) != null)
				buffer.append(line + "\n");
			this.input = buffer.toString();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
