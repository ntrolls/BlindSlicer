package bs.tool.slicer;

import bs.data.Source;
import bs.data.TestSuite;
import bs.tool.Executor;
import bs.tool.Tester;
import bs.tool.Compiler;

public abstract class Slicer 
{
	Tester tester = null;
	Compiler compiler = null;
	Executor executor = null;
	TestSuite testsuite;
	
	public void setTester(Tester tester)
	{
		this.tester = tester;
	}
	
	public void setTestSuite(TestSuite testsuite)
	{
		this.testsuite = testsuite;
	}
	
	public void setExecutor(Executor executor)
	{
		this.executor = executor;
	}
	
	public void setCompiler(Compiler compiler)
	{
		this.compiler = compiler;
	}
	
	public abstract Source slice(Source original);
}
