/**
 * 
 */
package bs.tool.common;

import java.util.Vector;

import bs.data.Mask;
import bs.data.Source;
import cern.colt.bitvector.BitVector;

/**
 * @author ntrolls
 *
 */
public class BitMask implements Mask
{
	private BitVector mask = null;
	
	public BitMask(int length)
	{
		this.mask = new BitVector(length);
		this.mask.not();
	}
	
	/* (non-Javadoc)
	 * @see bs.tool.Mask#apply(bs.tool.Source)
	 */
	@Override
	public Source applyToSource(Source original)
	{
		assert(original.length() == mask.size());
		Vector<String> retained = new Vector<String>();
		for(int i = 0; i < mask.size(); i++)
		{
			if(mask.get(i) == true || original.lines().get(i).contains("//blindslice"))
				retained.add(original.lines().get(i));
		}
		return new SingleFileSource("sliced_" + original.name(), retained);
	}
	
	@Override
	public void set(int index, int s)
	{
		mask.put(index, s == 1);
	}
	
	@Override
	public int get(int index)
	{
		return mask.get(index) ? Mask.RETAINED : Mask.DELETED;
	}
	
	public String toString()
	{
		return mask.toString();
	}

	@Override
	public int size() 
	{
		return mask.cardinality();
	}

	@Override
	public int length() 
	{
		return mask.size();
	}
	
	
}
