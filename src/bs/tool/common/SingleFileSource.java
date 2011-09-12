/**
 * 
 */
package bs.tool.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import bs.data.Source;

/**
 * @author ntrolls
 *
 */
public class SingleFileSource implements Source
{
	private String name = null;
	private Vector<String> lines = new Vector<String>();
	private String path = null;
	
	public SingleFileSource(String path) throws IOException
	{
		File sourceFile = new File(path);
		String line;
		
		BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
		while((line = reader.readLine()) != null)
			lines.add(line + "\n");
		this.name = sourceFile.getName();
	}
	
	public SingleFileSource(String name, Vector<String> lines)
	{
		this.name = name;
		this.lines.addAll(lines);
	}

	/* (non-Javadoc)
	 * @see bs.tool.Source#name()
	 */
	@Override
	public String name()
	{
		// TODO Auto-generated method stub
		return name;
	}

	/* (non-Javadoc)
	 * @see bs.tool.Source#path()
	 */
	@Override
	public String path()
	{
		if(path == null)
		{
			String tmpDir = System.getProperty("java.io.tmpdir");
			File tmpSource = new File(tmpDir + name);
			FileWriter writer;
			try
			{
				writer = new FileWriter(tmpSource);
				for(String line: lines)
					writer.write(line);
				writer.flush();
				writer.close();
				this.path = tmpSource.getAbsolutePath();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return path;
	}

	/* (non-Javadoc)
	 * @see bs.tool.Source#executableName()
	 */
	@Override
	public String executableName()
	{
		int dot = name.indexOf('.');
		return name.substring(0, dot);
	}

	/* (non-Javadoc)
	 * @see bs.tool.Source#length()
	 */
	@Override
	public int length()
	{
		return lines.size();
	}

	/* (non-Javadoc)
	 * @see bs.tool.Source#lines()
	 */
	@Override
	public Vector<String> lines()
	{
		return lines;
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		for(String line: lines)
			buffer.append(line);
		return buffer.toString();
	}

}
