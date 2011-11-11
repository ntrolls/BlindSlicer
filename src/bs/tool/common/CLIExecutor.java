package bs.tool.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.Timer;

import bs.data.Result;
import bs.data.Test;
import bs.tool.Executable;
import bs.tool.Executor;

public class CLIExecutor implements Executor, ActionListener
{
	static Process p = null;

	/* (non-Javadoc)
	 * @see bs.tool.Executor#execute(bs.tool.Executable)
	 */
	@Override
	public Result execute(Executable executable, Test test)
	{
		String cmd = executable.cmd() + " " + test.inputValue();
		try
		{
			p = Runtime.getRuntime().exec(cmd);
			Timer t = new Timer(3000, this);
			t.start();

			InputStream in = p.getInputStream();
			p.waitFor();
			t.stop();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line);
			}
			p = null;
			return new StringResult(buffer.toString());
		}
		catch (IOException e)
		{
			return null;
		}
		catch (InterruptedException e)
		{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (p != null)
		{
			System.err.println("timeout!");
			p.destroy();
		}
	}
}
