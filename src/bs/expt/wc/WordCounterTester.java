/**
 * 
 */
package bs.expt.wc;

import java.util.Collection;

import bs.data.Result;
import bs.data.Test;
import bs.data.TestSuite;
import bs.tool.Executable;
import bs.tool.Executor;
import bs.tool.Tester;

/**
 * @author ntrolls
 * 
 */
public class WordCounterTester implements Tester
{

	/* (non-Javadoc)
	 * @see bs.tool.Tester#test(bs.tool.Executor, bs.tool.Executable, bs.tool.Executable, java.util.Collection)
	 */
	@Override
	public boolean test(Executor executor, Executable original, Executable sliced, TestSuite testsuite)
	{
		Collection<Test> tests = testsuite.getTests();
		for (Test test : tests)
		{
			Result result_original = executor.execute(original, test);
			Result result_sliced = executor.execute(sliced, test);
			if (!isIdentical(result_original, result_sliced))
				return false;
		}
		return true;
	}

	int parseResult(String result)
	{
		String[] tokens = result.split(" ");
		for (String token : tokens)
		{
			if (token.startsWith("@"))
			{
				return Integer.parseInt(token.substring(1));
			}
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see bs.tool.Tester#isIdentical(bs.tool.Result, bs.tool.Result)
	 */
	@Override
	public boolean isIdentical(Result original, Result sliced)
	{

		if (original == null || sliced == null)
			return false;
		System.out.println(original + " vs. " + sliced);
		int o = parseResult(original.toString());
		int s = parseResult(sliced.toString());
		return o == s;
	}
}
