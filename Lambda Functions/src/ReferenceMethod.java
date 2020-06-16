import java.util.function.Consumer;

public class ReferenceMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Consumer<String> consumidor = (x) -> System.out.println(x);
		consumidor.accept("123");
		consumidor.accept("hola");

			
		//Consumer normally process data but no return is expected
		Consumer<String> consumer1=s->{
			System.out.println(s+" World.");
		};
		Consumer<String> consumer2=s->{
			System.out.println(s+" Java.");
		};
		
		//Using andThen method
		consumer1.andThen(consumer2).accept("Hello world");
		}
	
		//Hello world World
		//Hello world Java

	

}
