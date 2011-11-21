#include <stdio.h>
#include <stdlib.h>

int p(int i);
int q(int c);

int f();
int g();
int h(int i);

int _f = 42;
int _g = 42;

int main(int argc, char *argv[])
{
	int i;
	int c;
	int x;
	
	i = (int) strtol(argv[1], NULL, 10);
	c = (int) strtol(argv[2], NULL, 10);
	x = (int) strtol(argv[3], NULL, 10);
	
	printf("1\n");
	while (p(i))
	{
		printf("2\n");
		if (q(c)) 
		{
			printf("3\n");
			x = f();
			printf("4\n");
			c = g();
		}
		printf("5\n");
		i = h(i);
	}
	
	printf("6\n");
	printf("@%d\n", x); //blindslice
}

int p(int i)
{
	if(i % 5 != 0)
		return 1;
	else
		return 0;
}

int q(int c)
{
	if(c > 30) return 1;
	else return 0;
}

int f()
{
	_f += 1;
	return _f;
}

int g()
{
	_g -= 1;
	return _g;
}

int h(int i)
{
	return i + 1;
}
