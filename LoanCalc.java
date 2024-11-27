// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double var0, double var2, int var4, double var5) {
		for(int var7 = 0; var7 < var4; ++var7) {
		   var0 = var0 - var5 + (var0 - var5) * (var2 / 100.0);
		}
  
		return var0;
	 }
  
	 public static double bruteForceSolver(double var0, double var2, int var4, double var5) {
		iterationCounter = 0;
		double var7 = var0 / (double)var4;
  
		for(double var9 = endBalance(var0, var2, var4, var7); var9 > 0.0; ++iterationCounter) {
		   var7 += var5;
		   var9 = endBalance(var0, var2, var4, var7);
		}
  
		return var7;
	 }
  
	 public static double bisectionSolver(double var0, double var2, int var4, double var5) {
		iterationCounter = 0;
		double var7 = var0 / (double)var4;
		double var9 = var0;
  
		double var11;
		for(var11 = (var7 + var0) / 2.0; var9 - var7 > var5; ++iterationCounter) {
		   if (endBalance(var0, var2, var4, var11) * endBalance(var0, var2, var4, var7) > 0.0) {
			  var7 = var11;
		   } else {
			  var9 = var11;
		   }
  
		   var11 = (var7 + var9) / 2.0;
		}
  
		return var11;
	}
}