public class Main{
	public static void main(String[] args){
		ExampleOutputChecker ex = new ExampleOutputChecker();
		//ExampleInputChecker ex = new ExampleInputChecker();
		System.out.println("Score : "+ex.check(args[0]));
	}
}
