#include <cstdio>
#include <cstdlib>
#include <iostream>

/*
 * fungsi faktorial
 * param int n
 * return n!
 */
int fac(int n){
	if (n==0) return 1;
	return n*fac(n-1);
}

int main(){
	int a,b;
	// baca nilai a dan b
	scanf("%d%d",&a,&b);//comment wkwk
	// tulis nilai a + b
	printf("%d\n",a+b);
	int d = fac(a);
	printf("%d %d\n",a,d);
	return 0;
}
