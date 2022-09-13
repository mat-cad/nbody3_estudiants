package util;


// Implementation of a vector of real numbers. This class is implemented to be immutable: once the client program
// initializes a nbody2.util.Vector, it cannot change any of its fields (N or data[i]) either directly or indirectly.
// Immutability is a very desirable feature of a data type.
//
// $ java nbody2.util.Vector
// x        =  (1.0, 2.0, 3.0, 4.0)
// y        =  (5.0, 2.0, 4.0, 1.0)
// x + y    =  (6.0, 4.0, 7.0, 5.0)
// 10x      =  (10.0, 20.0, 30.0, 40.0)
// |x|      =  5.477225575051661
// <x, y>   =  25.0
// |x - y|  =  5.0990195135927845
//
// Note that java.util.nbody2.util.Vector is an unrelated Java library class.
public class Vector {
    private final int n;         // length of the vector
    private double[] data;       // array of vector's components

    // create the zero vector of length n
    public Vector(int n) {
        this.n = n;
        this.data = new double[n];
    }

    // create a vector from an array
    public Vector(double[] data) {
        n = data.length;

        // defensive copy so that client can't alter our copy of data[]
        this.data = new double[n];
        for (int i = 0; i < n; i++)
            this.data[i] = data[i];
    }

    // return the length of the vector
    public int length() {
        return n;
    }

    // return the inner product of this nbody2.util.Vector a and b
    public double dot(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        double sum = 0.0;
        for (int i = 0; i < n; i++)
            sum = sum + (this.data[i] * that.data[i]);
        return sum;
    }

    // return the Euclidean norm of this nbody2.util.Vector
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    // return the Euclidean distance between this and that
    public double distanceTo(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        return this.minus(that).magnitude();
    }

    // return this + that
    public Vector plus(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = this.data[i] + that.data[i];
        return c;
    }

    // return this - that
    public Vector minus(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = this.data[i] - that.data[i];
        return c;
    }

    // return the corresponding coordinate
    public double cartesian(int i) {
        return data[i];
    }

    // create and return a new object whose value is (this * factor)
    @Deprecated
    public Vector times(double factor) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = factor * data[i];
        return c;
    }

    // create and return a new object whose value is (this * factor)
    public Vector scale(double factor) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = factor * data[i];
        return c;
    }

    // return the corresponding unit vector
    public Vector direction() {
        if (this.magnitude() == 0.0)
            throw new ArithmeticException("zero-vector has no direction");
        return this.scale(1.0 / this.magnitude());
    }

    // return a string representation of the vector
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('(');
        for (int i = 0; i < n; i++) {
            s.append(data[i]);
            if (i < n-1) s.append(", ");
        }
        s.append(')');
        return s.toString();
    }

    // test client
    public static void main(String[] args) {
        double[] xdata = { 1.0, 2.0, 3.0, 4.0 };
        double[] ydata = { 5.0, 2.0, 4.0, 1.0 };
        Vector x = new Vector(xdata);
        Vector y = new Vector(ydata);
        System.out.println("x        =  " + x);
        System.out.println("y        =  " + y);
        System.out.println("x + y    =  " + x.plus(y));
        System.out.println("10x      =  " + x.scale(10.0));
        System.out.println("|x|      =  " + x.magnitude());
        System.out.println("<x, y>   =  " + x.dot(y));
        System.out.println("|x - y|  =  " + x.minus(y).magnitude());
    }
}


/*
import java.math.BigDecimal;
import java.math.MathContext;

public class Vector {
    private final int n;         // length of the vector
    private BigDecimal[] data;       // array of vector's components

    // create the zero vector of length n
    public Vector(int n) {
        this.n = n;
        this.data = new BigDecimal[n];
    }

    // create a vector from an array
    public Vector(double[] data) {
        n = data.length;

        // defensive copy so that client can't alter our copy of data[]
        this.data = new BigDecimal[n];
        for (int i = 0; i < n; i++)
            this.data[i] = BigDecimal.valueOf(data[i]);
    }

    // return the length of the vector
    public int length() {
        return n;
    }

    // return the inner product of this nbody2.util.Vector a and b
    public BigDecimal dot(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        BigDecimal sum = BigDecimal.valueOf(0.0);
        for (int i = 0; i < n; i++)
            sum = sum.add(this.data[i].multiply(that.data[i]));
        return sum;
    }

    // return the Euclidean norm of this nbody2.util.Vector
    public BigDecimal magnitude() {
        return (this.dot(this)).sqrt(MathContext.DECIMAL128);
    }

    // return the Euclidean distance between this and that
    public BigDecimal distanceTo(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        return this.minus(that).magnitude();
    }

    // return this + that
    public Vector plus(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = this.data[i].add(that.data[i]);
        return c;
    }

    // return this - that
    public Vector minus(Vector that) {
        if (this.length() != that.length())
            throw new IllegalArgumentException("dimensions disagree");
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = this.data[i].subtract(that.data[i]);
        return c;
    }

    // return the corresponding coordinate
    public double cartesian(int i) {
        return data[i].doubleValue();
    }

    // create and return a new object whose value is (this * factor)
    @Deprecated
    public Vector times(double factor) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = data[i].multiply(BigDecimal.valueOf(factor));
        return c;
    }

    // create and return a new object whose value is (this * factor)
    public Vector scale(double factor) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = data[i].multiply(BigDecimal.valueOf(factor));
        return c;
    }

    public Vector scale(BigDecimal factor) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++)
            c.data[i] = data[i].multiply(factor);
        return c;
    }

    // return the corresponding unit vector
    public Vector direction() {
        if (this.magnitude() == BigDecimal.ZERO)
            throw new ArithmeticException("zero-vector has no direction");
        Vector c = new Vector(n);
        BigDecimal magnitude = this.magnitude();
        for (int i = 0; i < n; i++)
            c.data[i] = data[i].divide(magnitude, MathContext.DECIMAL128);
        return c;
    }

    // return a string representation of the vector
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('(');
        for (int i = 0; i < n; i++) {
            s.append(data[i]);
            if (i < n-1) s.append(", ");
        }
        s.append(')');
        return s.toString();
    }

    // test client
    public static void main(String[] args) {
        double[] xdata = { 1.0, 2.0, 3.0, 4.0 };
        double[] ydata = { 5.0, 2.0, 4.0, 1.0 };
        Vector x = new Vector(xdata);
        Vector y = new Vector(ydata);
        System.out.println("x        =  " + x);
        System.out.println("y        =  " + y);
        System.out.println("x + y    =  " + x.plus(y));
        System.out.println("10x      =  " + x.scale(10.0));
        System.out.println("|x|      =  " + x.magnitude());
        System.out.println("<x, y>   =  " + x.dot(y));
        System.out.println("|x - y|  =  " + x.minus(y).magnitude());
    }
}
*/
