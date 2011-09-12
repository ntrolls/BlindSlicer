/**
 * 
 */
package bs.tool;

import bs.data.Result;
import bs.data.TestSuite;

/**
 * @author ntrolls
 *
 */
public interface Tester
{
	public boolean test(Executor executor, Executable original, Executable sliced, TestSuite testsuite);
	public boolean isIdentical(Result original, Result sliced);
}
