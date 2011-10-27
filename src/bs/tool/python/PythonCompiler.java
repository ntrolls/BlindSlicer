/**
 * 
 */
package bs.tool.python;

import bs.data.Source;
import bs.tool.Compiler;
import bs.tool.Executable;

/**
 * @author ntrolls
 *
 */
public class PythonCompiler implements Compiler
{

	/* (non-Javadoc)
	 * @see bs.tool.Compiler#compile(bs.data.Source)
	 */
	@Override
	public Executable compile(Source source)
	{
		return new PythonExecutable(source.path());
	}

}
