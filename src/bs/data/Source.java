/**
 * 
 */
package bs.data;

import java.util.Vector;

/**
 * @author ntrolls
 * 
 */
public interface Source
{
	public String name();
	public String executableName();
	public String path();
	public String dump();
	public int length();

	public String getLine(int index);
	public void setLine(int index, String value);

	public Vector<String> lines();
	public String toString();
}