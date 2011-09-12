/**
 * 
 */
package bs.tool.c;

import bs.tool.Executable;

/**
 * @author ntrolls
 *
 */
public class CExecutable implements Executable
{
	private String path = null;
	
	public CExecutable(String path)
	{
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see bs.tool.Executable#path()
	 */
	@Override
	public String path()
	{
		return path;
	}

}
