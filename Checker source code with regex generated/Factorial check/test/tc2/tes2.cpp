#include <cstdio>
#include <cstdlib>
#include <iostream>

/*
 * fungsi faktorial
 * param int n
 * return n!
 */
int facs(int n){
	if (n==0) return 1;
	return n*facs(n-1);
}

int main(){
	int a,b;
	// baca nilai a dan b
	scanf("%d%d",&a,&b);//comment wkwk
	// tulis nilai a + b
	printf("%d\n",a+b);
	int d = facs(a);
	printf("%d %d\n",a,d);
	return 0;
}
