public class Complex {
	float re;
	float im;

	public Complex(float re, float im) {
		this.re = re;
		this.im = im;
	}

	public static Complex ComplexAdd(Complex n1, Complex n2) {
		Complex res = new Complex(0, 0);
		res.re = n1.re + n2.re;
		res.im = n1.im + n2.im;
		return res;
	}
	
	public static Complex ComplexMul(Complex n1, Complex n2) {
		Complex res = new Complex(0, 0);
		res.re = n1.re * n2.re;
		res.im = n1.im * n2.im;
		return res;
	}
}