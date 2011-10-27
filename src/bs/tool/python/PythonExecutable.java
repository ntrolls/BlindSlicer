/**
 * 
 */
package bs.tool.python;

import bs.tool.Executable;

/**
 * @author ntrolls
 *
 */
public class PythonExecutable implements Executable
{

	private String path;

	public PythonExecutable(String path)
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

	/* (non-Javadoc)
	 * @see bs.tool.Executable#cmd()
	 */
	@Override
	public String cmd()
	{
		return "python " + path();
	}

}
