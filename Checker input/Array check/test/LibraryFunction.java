import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("all")
public class LibraryFunction {
	// static attribute and method
	private static final long MAX_IDX = 10000000;
	private static boolean isSieve = false;
	private static boolean isUsed[];
	private static ArrayList<Long> primesList;
	
	public static void Sieve(){
		primesList = new ArrayList<Long>();
		isUsed = new boolean[(int)MAX_IDX];
		isUsed[0] = false;
		isUsed[1] = false;
		for (int i=2; i<MAX_IDX; i++){
			isUsed[i] = true;
		}
		for (long i=2; i<MAX_IDX; i++){
			if (isUsed[(int)i]){
				primesList.add(new Long(i));
				for (long j=i*i; j<MAX_IDX; j+=i){
					isUsed[(int)j] = false;
				}
			}
		}
		isSieve = true;
	}
	
	public static int sum(ArrayList arr, ArrayList<Boolean> cond){
		int ret = 0;
		for (int i=0; i<arr.size(); i++){
			if (cond.get(i)) ret += (int)arr.get(i);
		}
		return ret;
	}
	public static int max(ArrayList arr, ArrayList<Boolean> cond){
		int ret = -Integer.MAX_VALUE;
		for (int i=0; i<arr.size(); i++){
			if (cond.get(i) && (int)arr.get(i) > ret) ret = (int)arr.get(i);
		}
		return ret;
	}
	public static int min(ArrayList arr, ArrayList<Boolean> cond){
		int ret = Integer.MAX_VALUE;
		for (int i=0; i<arr.size(); i++){
			if (cond.get(i) && (int)arr.get(i) < ret) ret = (int)arr.get(i);
		}
		return ret;
	}
	public static boolean prime(int element){
		if (!LibraryFunction.isSieve){
			LibraryFunction.isSieve = true;
			LibraryFunction.Sieve();
		}
		if (element < 0) return false;
		if (element < MAX_IDX){
			return LibraryFunction.isUsed[(int)element];
		} else {
			for (int i=0; i<LibraryFunction.primesList.size(); i++){
				if (element % LibraryFunction.primesList.get(i) == 0) return false;
			}
			return true;
		}
	}
	public static double abs(double element){
		if (element < 0) return element*-1.0;
		return element;
	}
	public static ArrayList swap(ArrayList arr, int a, int b){
		int s = arr.size();
		ArrayList ret = new ArrayList();
		for (int i=0; i<s; i++){
			if (i == a) ret.add(arr.get(b));
			else if (i == b) ret.add(arr.get(a));
			else ret.add(arr.get(i));
		}
		return ret;
	}
	public static int select(int a, int b){
		return random(a,b);
	}
	public static ArrayList add(ArrayList arr, Object element){
		return include(arr,element);
	}
	public static ArrayList remove(ArrayList arr, ArrayList<Boolean> cond){
		ArrayList ret = new ArrayList();
		for (int i=0; i<arr.size(); i++){
			if (!(cond.get(i))){
				ret.add(arr.get(i));
			}
		}
		return ret;
	}
	public static ArrayList remove(ArrayList arr, int index){
		return exclude(arr,index);
	}
	public static int random(int a, int b){
		Random rand = new Random();
		return rand.nextInt(b-a+1)+a;
	}
	public static int size(ArrayList arr){
		return arr.size();
	}
	public static ArrayList reverse(ArrayList arr){
		ArrayList ret = new ArrayList();
		for (int i=arr.size()-1; i>=0; i--){
			ret.add(arr.get(i));
		}
		return ret;
	}
	public static boolean contain(ArrayList arr, Object element){
		return (arr.contains(element));
	}
	
	public static ArrayList permute(ArrayList arr){
		int s = arr.size();
		boolean perm[] = new boolean[s];
		for (int i=0; i<s; i++){
			perm[i] = false;
		}
		ArrayList ret = new ArrayList();
		int r;
		Random rand = new Random();
		for (int i=0; i<s; i++){
			do {
				r = rand.nextInt(s);
			} while (perm[r]);
			perm[r] = true;
			ret.add(arr.get(r));
		}
		return ret;
	}
	
	public static ArrayList plus(ArrayList arr, int p){
		int s = arr.size();
		ArrayList ret = new ArrayList();
		for (int i=0; i<s; i++){
			ret.add((Integer)arr.get(i)+p);
		}
		return ret;
	}
	
	public static ArrayList multiply(ArrayList arr, int m){
		int s = arr.size();
		ArrayList ret = new ArrayList();
		for (int i=0; i<s; i++){
			ret.add((Integer)arr.get(i)*m);
		}
		return ret;
	}
	
	public static ArrayList invert(ArrayList arr){
		int s = arr.size();
		ArrayList ret = new ArrayList();
		for (int i=0; i<s; i++){
			ret.add((Integer)arr.get(i)*(-1));
		}
		return ret;
	}
	
	public static ArrayList include(ArrayList arr, Object element){
		int s = arr.size();
		ArrayList ret = new ArrayList();
		for (int i=0; i<s; i++){
			ret.add(arr.get(i));
		}
		ret.add(element);
		return ret;
	}
	
	public static ArrayList exclude(ArrayList arr, int index){
		int s = arr.size();
		ArrayList ret = new ArrayList();
		for (int i=0; i<s; i++){
			if (index != i) ret.add(arr.get(i));
		}
		return ret;
	}
	
	public static Pair<ArrayList,ArrayList> compositional(ArrayList arr){
		int s = arr.size();
		ArrayList ret1 = new ArrayList();
		ArrayList ret2 = new ArrayList();
		int r;
		Random rand = new Random();
		for (int i=0; i<s; i++){
			r = rand.nextInt(2);
			if (r%2==0) ret1.add(arr.get(i));
			else ret2.add(arr.get(i));
		}
		Pair<ArrayList,ArrayList> ret = new Pair<ArrayList,ArrayList>(ret1,ret2);
		return ret;
	}
	
	public static ArrayList swapColumn(ArrayList arr, int c1, int c2){
		ArrayList temp = new ArrayList();
		for (int i=0; i<(int)arr.size(); i++){
			ArrayList temp2 = (ArrayList)arr.get(i);
			int a = (int)temp2.get(c1);
			int b = (int)temp2.get(c2);
			temp2.set(c1,b);
			temp2.set(c2,a);
			temp.add(temp2);
		}
		return temp;
	}
	
	public static ArrayList addRow(ArrayList arr){
		ArrayList temp = arr;
		ArrayList newRow = (ArrayList)arr.get(0);
		for (int j=0; j<newRow.size(); j++){
			newRow.set(j,0);
		}
		temp.add(newRow);
		return temp;
	}
	
	public static ArrayList addColumn(ArrayList arr){
		ArrayList temp = new ArrayList();
		for (int i=0; i<arr.size(); i++){
			ArrayList temp2 = (ArrayList)arr.get(i);
			temp2.add(0);
			temp.add(temp2);
		}
		return temp;
	}
}
