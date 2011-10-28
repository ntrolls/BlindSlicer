/**
 * 
 */
package bs.tool.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import bs.data.Source;
import bs.tool.Compiler;
import bs.tool.Executable;

/**
 * @author ntrolls
 * 
 */
public class GCCCompiler implements Compiler
{

	/* (non-Javadoc)
	 * @see bs.tool.Compiler#compile(bs.tool.Source)
	 */
	@Override
	public Executable compile(Source source)
	{
		String tmpDir = System.getProperty("java.io.tmpdir");
		String cmd = "gcc " + source.path() + " -o " + tmpDir + source.executableName();
		try
		{
			Process gcc = Runtime.getRuntime().exec(cmd);
			gcc.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(gcc.getErrorStream()));
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line + "\n");
			}

			if (gcc.exitValue() == 0)
				return new CExecutable(tmpDir + source.executableName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
