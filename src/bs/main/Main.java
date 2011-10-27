/**
 * 
 */
package bs.main;

import bs.expt.wc.WordCounterInCExpt;
import bs.tool.Experiment;

/**
 * @author ntrolls
 *
 */
public class Main
{
	public static void main(String[] main) throws Exception
	{
		Experiment e = new WordCounterInCExpt();
		e.run();
		System.exit(0);
	}
}
