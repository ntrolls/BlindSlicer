package bs.tool.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import bs.data.Mask;
import bs.data.Source;

public class MappingMask implements Mask 
{
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	private Vector<Integer> mask = null;
	
	public MappingMask(int length)
	{
		this.mask = new Vector<Integer>(length);
		for(int i = 0; i < length; i++)
			this.mask.add(Mask.RETAINED);
	}
	
	public MappingMask(int length, HashMap<Integer, String> mapping)
	{
		this.mask = new Vector<Integer>(length);
		for(int i = 0; i < length; i++)
			this.mask.add(Mask.RETAINED);
		this.map.putAll(mapping);
	}
	
	public void addMapping(int key, String contents)
	{
		map.put(key, contents);
	}
	
	@Override
	public Source applyToSource(Source original) {
		assert(original.length() == mask.size());
		Vector<String> applied = new Vector<String>();
		for(int i = 0; i < mask.size(); i++)
		{
			if(mask.get(i) == Mask.RETAINED || original.lines().get(i).contains("//blindslice"))
				applied.add(original.lines().get(i));
			else if(mask.get(i) == Mask.DELETED)
				applied.add("\n");
			else if(map.containsKey(mask.get(i)))
			{
				String ol = original.getLine(i);
				String ws = ol.substring(0, getLeadingWhiteSpace(ol));
				applied.add(ws + map.get(mask.get(i)));
			}
		}
		return new SingleFileSource("sliced_" + original.name(), applied);
	}

	@Override
	public int size() {
		int count = 0;
		Iterator<Integer> it = mask.iterator();
		while(it.hasNext())
			count += it.next() == Mask.RETAINED ? 1 : 0;
		return count;
	}

	@Override
	public int length() {
		return mask.size();
	}

	@Override
	public int get(int index) 
	{
		return mask.get(index);
	}

	@Override
	public void set(int index, int state) 
	{
		mask.set(index, state);
	}
	
	int getLeadingWhiteSpace(CharSequence str) 
	{
		int whitespace = 0;
		loop: 
		for (; whitespace < str.length();) 
		{
			switch (str.charAt(whitespace)) {
			case ' ':
			case '\t':
				whitespace++;
				break;
			default:
				break loop;
			}
		}
		return whitespace;
	}
}
