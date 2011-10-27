/**
 * 
 */
package bs.data;

/**
 * @author ntrolls
 *
 */
public interface Mask
{
	public static final int RETAINED = 1;
	public static final int DELETED = 0;
	
	public int get(int index);
	public void set(int index, int state);
	public Source applyToSource(Source original);
	public int size();
	public int length();
}
