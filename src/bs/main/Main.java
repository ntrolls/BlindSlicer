/**
 * 
 */
package bs.main;

import bs.expt.scam.ScamMugExpt;
import bs.tool.Experiment;

/**
 * @author ntrolls
 * 
 */
public class Main
{
	public static void main(String[] main) throws Exception
	{
		Experiment e = new ScamMugExpt();
		e.run();
		System.exit(0);
	}
}
