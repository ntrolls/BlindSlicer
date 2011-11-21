/**
 * 
 */
package bs.main;

import bs.expt.mbe.MBEExpt;
import bs.tool.Experiment;

/**
 * @author ntrolls
 * 
 */
public class Main
{
	public static void main(String[] main) throws Exception
	{
		Experiment e = new MBEExpt();
		e.run();
		System.exit(0);
	}
}
