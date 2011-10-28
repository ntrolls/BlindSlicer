import sys

def isletter(c):
	if (c >= 'A' and c <= 'Z') or (c >= 'a' and c <= 'z'):
		return True
	else:
		return False
	
if __name__ == "__main__":
	inword = 0
	characters = 0
	lines = 0
	words = 0
	
	c = sys.stdin.read(1)
	while(c != ""):
		characters = characters + 1
		
		if c == '\n':
			lines = lines + 1
		if isletter(c):
			if inword == 0:
				words = words + 1
				inword = 1
		else:
			inword = 0
		c = sys.stdin.read(1)
	
	print "%d " % lines
	print "%d " % words
	print "@%d" % characters #//blindslice

