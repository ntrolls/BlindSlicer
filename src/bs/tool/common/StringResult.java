/**
 * 
 */
package bs.tool.common;

import bs.data.Result;

/**
 * @author ntrolls
 *
 */
public class StringResult implements Result
{
	private String result = null;

	public StringResult(String result)
	{
		this.result = result;
	}
	
	public String toString()
	{
		return result;
	}
}
