public class LoanCalc {

    static double epsilon = 0.001; // Approximation accuracy
    static int iterationCounter;  // Number of iterations 

    public static void main(String[] args) {	
        if (args.length < 3) {
            System.out.println("Error: Please provide three arguments: loan amount, interest rate, and number of payments.");
            return;
        }

        double loan;
        double rate;
        int n;

        try {
            loan = Double.parseDouble(args[0]);
            rate = Double.parseDouble(args[1]);
            n = Integer.parseInt(args[2]);

            if (loan <= 0 || rate < 0 || n <= 0) {
                System.out.println("Error: Loan amount, rate, and number of payments must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Loan and rate should be numbers; periods should be an integer.");
            return;
        }

        System.out.printf("Loan = %.2f, Interest Rate = %.2f%%, Periods = %d%n", loan, rate, n);

        double payment = 10000;
        double endBalance = endBalance(loan, rate, n, payment);
        System.out.printf("If your periodical payment is %.2f, your ending balance is: %.2f%n", payment, endBalance);

        System.out.printf("%nPeriodical payment (brute force): %.2f%n", bruteForceSolver(loan, rate, n, epsilon));
        System.out.printf("Number of iterations: %d%n", iterationCounter);

        System.out.printf("%nPeriodical payment (bisection): %.2f%n", bisectionSolver(loan, rate, n, epsilon));
        System.out.printf("Number of iterations: %d%n", iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {	
        double balance = loan;
        for (int i = 0; i < n; i++) {
            balance = balance * (1 + rate / 100) - payment;
        }
        return balance;
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double payment = loan / n;
        double balance = endBalance(loan, rate, n, payment);

        while (balance > epsilon) {
            payment += epsilon;
            iterationCounter++;
            balance = endBalance(loan, rate, n, payment);
        }

        return payment;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double low = loan / n;
        double high = loan;
        double mid = (low + high) / 2.0;

        while ((high - low) > epsilon) {
            double balance = endBalance(loan, rate, n, mid);

            if (balance > 0) {
                low = mid; // Increase payment
            } else {
                high = mid; // Decrease payment
            }

            mid = (low + high) / 2.0;
            iterationCounter++;
        }

        return mid;
    }
}