public class Complex {
	double re;
	double im;

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public static Complex ComplexAdd(Complex n1, Complex n2) {
		return new Complex(n1.re + n2.re, n1.im + n2.im);
	}

	public static Complex ComplexMul1(Complex n1, Complex n2) {
		Complex res = new Complex(0, 0);
		res.re = n1.re * n2.re;
		res.im = n1.im * n2.im;
		return res;
	}
	
	public static Complex ComplexMul(Complex z1, Complex z2)
    {
        double _real = z1.re*z2.re - z1.im*z2.im;
        double _imaginary = z1.re*z2.im + z1.im*z2.re;
        return new Complex(_real,_imaginary);
    }

	public static double ComplexAbs(Complex n1) {
		//return Math.sqrt(z.re*z.re + z.im*z.im);
		return Math.sqrt(Math.pow(n1.re,2) + Math.pow(n1.im,2));
	}
}