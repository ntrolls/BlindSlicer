/**
 * 
 */
package bs.tool;

import bs.data.Result;
import bs.data.Test;

/**
 * @author ntrolls
 *
 */
public interface Executor
{
	public Result execute(Executable executable, Test test);
}
