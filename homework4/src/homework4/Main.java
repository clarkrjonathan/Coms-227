package homework4;

public class Main {
	public static void main(String args[]) {
		System.out.println("Hello World");
		System.out.println("Github Test");
		int[] intarray = {1};
		test(intarray);
		System.out.println(intarray[0]);
	}
	
	
	public static void test(int[] intarray) {
		intarray[0] += 1;
	}

}
