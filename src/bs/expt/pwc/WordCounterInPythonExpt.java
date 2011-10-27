/**
 * 
 */
package bs.expt.pwc;

import bs.data.Source;
import bs.expt.wc.WordCounterTester;
import bs.tool.Experiment;
import bs.tool.common.InputFileTestSuite;
import bs.tool.common.PipeExecutor;
import bs.tool.common.SingleFileSource;
import bs.tool.python.PythonCompiler;
import bs.tool.slicer.GreedySlicer;
import bs.tool.slicer.Slicer;

/**
 * @author ntrolls
 *
 */
public class WordCounterInPythonExpt implements Experiment
{

	/* (non-Javadoc)
	 * @see bs.tool.Experiment#run()
	 */
	@Override
	public void run() throws Exception
	{
		Source wc = new SingleFileSource("/Users/ntrolls/Projects/BlindSlicer/subjects/pwc/pwc.py");
		
		Slicer slicer = new GreedySlicer();
		slicer.setCompiler(new PythonCompiler());
		slicer.setExecutor(new PipeExecutor());
		slicer.setTester(new WordCounterTester());
		slicer.setTestSuite(new InputFileTestSuite("/Users/ntrolls/Projects/BlindSlicer/subjects/pwc/testsuite"));
		
//		int length = wc.length();
//		HashMap<Integer, String> mapping = new HashMap<Integer, String>();
//		mapping.put(2, "pass\n");
//		Mask mask = new MappingMask(length, mapping);
//		mask.set(2, 2);
//		
//		System.out.println(mask.applyToSource(wc));
		
		Source sliced = slicer.slice(wc);
		System.out.println(sliced);
	}
}