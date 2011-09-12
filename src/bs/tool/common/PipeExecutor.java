/**
 * 
 */
package bs.tool.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import bs.data.Result;
import bs.data.Test;
import bs.tool.Executable;
import bs.tool.Executor;

/**
 * @author ntrolls
 *
 */
public class PipeExecutor implements Executor
{

	/* (non-Javadoc)
	 * @see bs.tool.Executor#execute(bs.tool.Executable)
	 */
	@Override
	public Result execute(Executable executable, Test test)
	{
		String cmd = executable.path();
		Process p;
		try
		{
			p = Runtime.getRuntime().exec(cmd);			
			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			writer.print(test.inputValue());
			writer.close();
			p.waitFor();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();
			String line;
			while((line = reader.readLine()) != null)
			{
				buffer.append(line);
			}
			return new StringResult(buffer.toString());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
