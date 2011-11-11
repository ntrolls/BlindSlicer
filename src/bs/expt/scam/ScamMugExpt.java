package bs.expt.scam;

import bs.data.Source;
import bs.expt.wc.WordCounterTester;
import bs.tool.Experiment;
import bs.tool.c.GCCCompiler;
import bs.tool.common.CLIExecutor;
import bs.tool.common.InputFileTestSuite;
import bs.tool.common.SingleFileSource;
import bs.tool.slicer.GreedySlicer;
import bs.tool.slicer.Slicer;

public class ScamMugExpt implements Experiment
{

	@Override
	public void run() throws Exception
	{
		Source scam = new SingleFileSource("/Users/ntrolls/Projects/BlindSlicer/subjects/scam/scam.c");

		Slicer slicer = new GreedySlicer();
		slicer.setCompiler(new GCCCompiler());
		slicer.setExecutor(new CLIExecutor());
		slicer.setTester(new WordCounterTester());
		slicer.setTestSuite(new InputFileTestSuite("/Users/ntrolls/Projects/BlindSlicer/subjects/scam/testsuite"));

		Source sliced = slicer.slice(scam);
		System.out.println(sliced);

	}

}
