#include <cstdio>
#include <cstdlib>
#include <iostream>

int main(){
	int a,b;
	// baca nilai a dan b
	scanf("%d%d",&a,&b);//comment wkwk
	// tulis nilai a + b
	printf("%d\n",a+b);
	int d = 1;
	for (int i=2; i<=a; i++){
		d *= i;
	}
	printf("%d %d\n",a,d);
	return 0;
}
