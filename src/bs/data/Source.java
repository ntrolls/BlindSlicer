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
	public int length();
	public Vector<String> lines();
	public String toString();
}