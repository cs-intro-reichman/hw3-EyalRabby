// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {

		if (x2 > 0) {
			for (int i = 0; i < x2; i++) {
				x1++;
			}
		} else {
			for (int i = 0; i < -x2; i++) {
				x1--;
			}
		}
		
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2 > 0) {
			for (int i = 0; i < x2; i++) {
				x1--;
			}
		} else {
			for (int i = 0; i < -x2; i++) {
				x1++;
			}
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int ans = 0;
		boolean isNegative = false;

		if (x1 < 0 && x2 > 0) {
			isNegative = true;
			x1 = minus(0, x1); 
		} else if (x2 < 0 && x1 > 0) {
			isNegative = true;
			x2 = minus(0, x2); 
		} else if (x1 < 0 && x2 < 0) {
			x1 = minus(0, x1);
			x2 = minus(0, x2);
		}
	
		for (int i = 0; i < x2; i++) {
			ans = plus(ans, x1);
		}
	
		if (isNegative) {
			ans = minus(0, ans);
		}
		return ans;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int ans = 1;
		for (int i = 0; i < n; i++){
			ans = times(ans,x);
		}
		return ans;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int count = 0;
		boolean isNegative = false;
	
		if (x1 < 0 && x2 > 0) {
			isNegative = true;
			x1 = minus(0, x1);
		} else if (x2 < 0 && x1 > 0) {
			isNegative = true;
			x2 = minus(0, x2);
		} else if (x1 < 0 && x2 < 0) {
			x1 = minus(0, x1);
			x2 = minus(0, x2);
		}
	
		while (x1 >= x2) {
			x1 = minus(x1, x2);
			count++;
		}
	
		if (isNegative) {
			count = minus(0, count);
		}
	
		return count;
	}


	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		
		int count = 1;
		int ans = 1;
		if (x1 == 0) return 0;
		
		while (ans < minus(x1,x2)) {
			ans = times(x2, count);
			count++;
		}

		ans = minus(x1, ans);
			if (ans != x2){
				return ans;
			} else {
				return 0;
			}
		}	
	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int result = 0;
        while (times(result, result) <= x) {
            result++;
        }
        return minus(result, 1);
	}	  	  
}