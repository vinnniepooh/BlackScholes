import java.lang.Math;

public class BlackScholes {

    // Standard normal probability density function
    public static double norm_pdf(double x) {
        return (1.0 / (Math.pow(2 * Math.PI, 0.5))) * Math.exp(-0.5 * x * x);
    }

    // An approximation to the cumulative distribution function
    // for the standard normal distribution
    public static double norm_cdf(double x) {
        double k = 1.0 / (1.0 + 0.2316419 * x);
        double k_sum = k * (0.319381530 + k * (-0.356563782 + k * (1.781477937 + k * (-1.821255978 + 1.330274429 * k))));

        if (x >= 0.0) {
            return (1.0 - (1.0 / (Math.pow(2 * Math.PI, 0.5))) * Math.exp(-0.5 * x * x) * k_sum);
        } else {
            return 1.0 - norm_cdf(-x);
        }
    }

    // This calculates d_j, for j in {1,2}. This term appears in the closed
    // form solution for the European call or put price
    public static double d_j(int j, double S, double K, double r, double v, double T) {
        return (Math.log(S / K) + (r + (Math.pow(-1, j - 1)) * 0.5 * v * v) * T) / (v * (Math.pow(T, 0.5)));
    }

    // Calculate the European vanilla call price based on
    // underlying S, strike K, risk-free rate r, volatility of
    // underlying sigma and time to maturity T
    public static double call_price(double S, double K, double r, double v, double T) {
        return S * norm_cdf(d_j(1, S, K, r, v, T)) - K * Math.exp(-r * T) * norm_cdf(d_j(2, S, K, r, v, T));
    }

    // Calculate the European vanilla put price based on
    // underlying S, strike K, risk-free rate r, volatility of
    // underlying sigma and time to maturity T
    public static double put_price(double S, double K, double r, double v, double T) {
        return -S * norm_cdf(-d_j(1, S, K, r, v, T)) + K * Math.exp(-r * T) * norm_cdf(-d_j(2, S, K, r, v, T));
    }

    public static void main(String[] args) {
        // First we create the parameter list
        double S = 100.0;  // Option price
        double K = 100.0;  // Strike price
        double r = 0.05;   // Risk-free rate (5%)
        double v = 0.2;    // Volatility of the underlying (20%)
        double T = 1.0;    // One year until expiry

        // Then we calculate the call
        double call = call_price(S, K, r, v, T);
        double put = put_price(S, K, r, v, T);

        System.out.println("Underlying:      " + S);
        System.out.println("Strike:          " + K);
        System.out.println("Risk-Free Rate:  " + r);
        System.out.println("Volatility:      " + v);
        System.out.println("Maturity:        " + T);

        System.out.println("Call Price:      " + call);
        System.out.println("Put Price:       " + put);
    }
}