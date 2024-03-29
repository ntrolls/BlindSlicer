/**
 * 
 */
package bs.expt.wc;

import java.io.IOException;

import bs.data.Source;
import bs.tool.Experiment;
import bs.tool.c.GCCCompiler;
import bs.tool.common.InputFileTestSuite;
import bs.tool.common.PipeExecutor;
import bs.tool.common.SingleFileSource;
import bs.tool.slicer.GreedySlicer;
import bs.tool.slicer.Slicer;

/**
 * @author ntrolls
 *
 */
public class WordCounterInCExpt implements Experiment
{

	/* (non-Javadoc)
	 * @see bs.tool.Experiment#run()
	 */
	@Override
	public void run() throws IOException
	{
		Source wc = new SingleFileSource("/Users/ntrolls/Projects/BlindSlicer/subjects/wc/wc.c");
		
		Slicer slicer = new GreedySlicer();
		slicer.setCompiler(new GCCCompiler());
		slicer.setExecutor(new PipeExecutor());
		slicer.setTester(new WordCounterTester());
		slicer.setTestSuite(new InputFileTestSuite("/Users/ntrolls/Projects/BlindSlicer/subjects/pwc/testsuite"));
		
		Source sliced = slicer.slice(wc);
		System.out.println(sliced);
	}

}
