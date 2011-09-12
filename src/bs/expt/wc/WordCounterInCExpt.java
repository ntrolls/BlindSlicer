/**
 * 
 */
package bs.expt.wc;

import java.io.IOException;

import bs.data.Source;
import bs.data.TestSuite;
import bs.tool.Compiler;
import bs.tool.Executable;
import bs.tool.Executor;
import bs.tool.Experiment;
import bs.tool.Tester;
import bs.tool.c.GCCCompiler;
import bs.tool.common.BitMask;
import bs.tool.common.InputFileTestSuite;
import bs.tool.common.PipeExecutor;
import bs.tool.common.SingleFileSource;

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
		Compiler gcc = new GCCCompiler();
		Executable original = gcc.compile(wc);
		Executor executor = new PipeExecutor();
		TestSuite testsuite = new InputFileTestSuite("/Users/ntrolls/Projects/BlindSlicer/subjects/wc/testsuite");
		Tester tester = new WordCounterTester();
		
		int length = wc.length();
		BitMask bitMask = new BitMask(length);
		
		int sliceSize = wc.length();
		int oldSize = sliceSize;
		do
		{
			for(int i = 0; i < length; i++)
			{
				bitMask.set(i, false);
				Source sliced = bitMask.apply(wc);
				Executable e = gcc.compile(sliced);
				if(e == null)
				{
					bitMask.set(i, true);
				}
				else
				{
					if(!tester.test(executor, original, e, testsuite))
					{
						bitMask.set(i, true);
					}
				}
			}
			oldSize = sliceSize;
			sliceSize = bitMask.apply(wc).length();
		}
		while(sliceSize < oldSize);
		
		Source finalSlice = bitMask.apply(wc);
		System.out.println(finalSlice);
	}

}
