package bs.tool.slicer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;

import com.floyd.Floyd;

import bs.data.Mask;
import bs.data.Source;
import bs.data.TestSuite;
import bs.tool.Executable;
import bs.tool.Executor;
import bs.tool.common.InputFileTestSuite;
import bs.tool.common.MappingMask;
import bs.tool.common.PipeExecutor;
import bs.tool.common.SingleFileSource;

public class GreedySlicer extends Slicer {

	@Override
	public Source slice(Source originalSource) 
	{
		Executable original = compiler.compile(originalSource);
		Executor executor = new PipeExecutor();
		TestSuite testsuite = new InputFileTestSuite("/Users/ntrolls/Projects/BlindSlicer/subjects/pwc/testsuite");
		
		int length = originalSource.length();
		HashMap<Integer, String> mapping = new HashMap<Integer, String>();
		//mapping.put(2, "pass\n");
		Mask mask = new MappingMask(length, mapping);
		
		int pass = 1;
		int oldMaskDigit = 0;
		
		Source sliced = new SingleFileSource("sliced_" + originalSource.name(), originalSource.lines());
		boolean bDeleted = false;
		Floyd floyd = new Floyd();
		try {
			floyd.setCleanUpRule(new File("/Users/ntrolls/Projects/Floyd/rule.txt"));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		do
		{
			System.out.println("pass " + pass);
			bDeleted = false;
			for(int i = 0; i < sliced.length(); i++)
			{
				
				if(mask.get(i) == Mask.DELETED || originalSource.getLine(i).trim().length() == 0)
					continue;
				System.out.println(sliced.getLine(i).replace("\n", ""));
				
				oldMaskDigit = mask.get(i);
				mask.set(i, Mask.DELETED);

				sliced = mask.applyToSource(originalSource);
				Executable e = compiler.compile(sliced);
				if(e == null)
				{
					System.out.println("\tdon't compile if deleted");
					mask.set(i, oldMaskDigit);
				}
				else
				{
					if(!tester.test(executor, original, e, testsuite))
					{
						System.out.println("\tdon't pass test if deleted");
						mask.set(i, oldMaskDigit);
					}
					else
					{
						System.out.println("\tcan be deleted!");
						bDeleted = true;
						floyd.setLines(sliced.lines());
					}
				}
				

			}
			pass += 1;
		}
		while(bDeleted);
		return mask.applyToSource(originalSource);
	}
}