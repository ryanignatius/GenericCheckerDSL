#include <cstdio>
#include <cstdlib>
#include <iostream>

/*
 * fungsi faktorial
 * param int n
 * return n!
 */
int fac(int n){
	int d = 1;
	for (int i=2; i<=n; i++){
		d *= i;
	}
	return d;
}

int main(){
	int a,b;
	// baca nilai a dan b
	scanf("%d%d",&a,&b);//comment wkwk
	// tulis nilai a + b
	printf("%d\n",a+b);
	int n = a;
	int d = fac(n);
	printf("%d %d\n",a,d);
	return 0;
}
