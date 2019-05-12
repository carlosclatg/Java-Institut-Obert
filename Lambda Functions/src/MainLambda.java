import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


interface A{
	void show(String s);
}

public class MainLambda {
	
	void close() {
		System.out.println("Close.");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		MainLambda mainLambda = new MainLambda();
		AutoCloseable aCloseable = mainLambda::close;
		aCloseable.close();
		
		A obj1;
		A obj2;
		
		String s = "hiiiii";
		
		//Option 1 Java <8
		obj1 = new A() {

			@Override
			public void show(String s) {
				System.out.println("Hello");
				
			}
			
		};
		//option 2 java >=8
		obj2 = d -> System.out.println("Hi Java8" + d);;
		
		obj1.show(s);
		obj2.show(s);
		
		
		
		List<String> items = new ArrayList<>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");
		
		items.forEach(item->System.out.println(item));
		
		

		MyInterface myInterface = (String text) -> {
			System.out.println(text);
		}; //only implements the unimplemented methods (void printIt(String text))
		
		String abc= "I love coding, even in weekends";
		myInterface.printIt(abc);
		//Implementing interfaces in Java8 with lambda functions
		
		MyInterface.printItToSystemOut(abc);
		
		myInterface.printUtf8To(abc, new FileOutputStream("data.txt"));
		
		
		
		Comparator comparator = (int a, int b) -> {
			return a > b;
		};
		
		System.out.println(comparator.compare(5, 2));
		System.out.println(comparator.compare(5, 20));
		
		
		Function<Double, Double> square = Hey::square;
		double ans = square.apply(23d);
		System.out.println(ans);
		System.out.println(ans);
	}


}


class Hey {
    public static double square(double num){
       return Math.pow(num, 2);
   }
}
