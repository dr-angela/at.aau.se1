package at.aau.serg.exercises.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyMathTest {

    private MyMath myMath;

    @BeforeEach
    public void setUp() {
        myMath = new MyMath(); // Setup für alle Tests
    }

    @Test
    public void test1() {
        Double add = myMath.add(1d, 2d);
        assertEquals(Double.valueOf(3.0), add); // Explizit Double.valueOf verwenden
    }

    @Test
    public void allWorks() {
        double x = 10d;
        double y = 5d;

        double add = myMath.add(x, y);
        double sub = myMath.sub(x, y);
        double mul = myMath.mul(x, y);
        double div = myMath.div(x, y);

        assertEquals(15.0, add);
        assertEquals(5.0, sub);
        assertEquals(50.0, mul);
        assertEquals(2.0, div);
    }

    @Test
    public void x2() {
        Fraction f = new Fraction(1, 1);
        Fraction reduced = myMath.reduce(f);
        assertEquals(Integer.valueOf(1), reduced.getNumerator());
        assertEquals(Integer.valueOf(1), reduced.getDenumerator());

        f = new Fraction(10, 6);
        reduced = myMath.reduce(f);
        assertEquals(Integer.valueOf(5), reduced.getNumerator());
        assertEquals(Integer.valueOf(3), reduced.getDenumerator());

        f = new Fraction(10, 5);
        reduced = myMath.reduce(f);
        assertEquals(Integer.valueOf(2), reduced.getNumerator());
        assertEquals(Integer.valueOf(1), reduced.getDenumerator());

        f = new Fraction(10, 5);
        Double aDouble = myMath.toDouble(f);
        assertEquals(Double.valueOf(2.0), aDouble); // Explizit Double.valueOf verwenden

        f = new Fraction(10, 4);
        aDouble = myMath.toDouble(f);
        assertEquals(Double.valueOf(2.5), aDouble); // Explizit Double.valueOf verwenden
    }
}