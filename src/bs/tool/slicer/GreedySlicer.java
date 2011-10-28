package bs.tool.slicer;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import bs.data.Source;
import bs.data.TestSuite;
import bs.tool.Executable;
import bs.tool.Executor;
import bs.tool.common.InputFileTestSuite;
import bs.tool.common.PipeExecutor;
import bs.tool.common.SingleFileSource;

import com.floyd.Floyd;

public class GreedySlicer extends Slicer
{

	@Override
	public Source slice(Source originalSource)
	{
		Executable original = compiler.compile(originalSource);
		Executor executor = new PipeExecutor();
		TestSuite testsuite = new InputFileTestSuite("/Users/ntrolls/Projects/BlindSlicer/subjects/wc/testsuite");

		Source sliced = new SingleFileSource("sliced_" + originalSource.name(), originalSource.lines());
		boolean bDeleted = false;
		Floyd floyd = new Floyd();
		try
		{
			floyd.setCleanUpRule(new File("/Users/ntrolls/Projects/Floyd/rules/c/cleanup.txt"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		int pass = 0;

		do
		{
			System.out.println("pass " + pass);
			bDeleted = false;
			for (int i = 0; i < sliced.length(); i++)
			{

				if (sliced.getLine(i).trim().length() == 0)
					continue;

				String originalLine = sliced.getLine(i);
				sliced.setLine(i, "");

				Executable e = compiler.compile(sliced);
				if (e == null)
				{
					System.out.println("\tdon't compile if deleted");
					sliced.setLine(i, originalLine);
				}
				else
				{
					if (!tester.test(executor, original, e, testsuite))
					{
						System.out.println("\tdon't pass test if deleted");
						sliced.setLine(i, originalLine);
					}
					else
					{
						System.out.println("\tcan be deleted!");
						bDeleted = true;
					}
				}
			}

			floyd.setLines(sliced.lines());
			for (int j = 0; j < sliced.length(); j++)
			{
				floyd.setCurrentLine(j);
				try
				{
					System.out.println("====== Begin Cleaning =================");
					Vector<String> cleaned = floyd.cleanUp();

					sliced.lines().clear();
					sliced.lines().addAll(cleaned);
					sliced.dump();

					System.out.println("======= End Cleaning  ================");
				}
				catch (org.antlr.runtime.RecognitionException e)
				{
					e.printStackTrace();
				}
			}

			System.out.println(sliced);
			pass += 1;
		}
		while (bDeleted);
		return sliced;
	}
}