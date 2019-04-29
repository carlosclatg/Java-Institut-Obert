package test;

/**
 * 
 * @author Usuario
 *
 */

public class Junit {
	
	/**
	 * This method concatenates two Strings received as parameter
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public String ConcatenatenName(String one, String two) {
		return one + two;
	}

	/**
	 * This method mutiplies two numbers ans returns the results.
	 * @param number1
	 * @param number2
	 * @return result
	 * @throws Exception
	 */
	public int multiply(int number1, int number2) throws Exception {
		if(number1 == -1) {
			throw new Exception("-1");
		}
		
		return number1 * number2;
		
		
	}
	
	/**
	 * Method that returns false
	 * @return false
	 */
	public boolean returnone () {
		return false;
	}

}
