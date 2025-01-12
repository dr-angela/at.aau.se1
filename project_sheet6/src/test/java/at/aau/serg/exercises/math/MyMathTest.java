package at.aau.serg.exercises.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyMathTest {

    private MyMath myMath;

    @BeforeEach
    public void setUp() {
        myMath = new MyMath();
    }

    @Test
    public void testAddition() {
        Double result = myMath.add(1d, 2d);
        assertEquals(Double.valueOf(3.0), result); // using Double.valueOf
    }

    @Test
    public void testSubtraction() {
        Double result = myMath.sub(10d, 5d);
        assertEquals(Double.valueOf(5.0), result);
    }

    @Test
    public void testMultiplication() {
        Double result = myMath.mul(10d, 5d);
        assertEquals(Double.valueOf(50.0), result);
    }

    @Test
    public void testDivision() {
        Double result = myMath.div(10d, 5d);
        assertEquals(Double.valueOf(2.0), result);
    }

    @Test
    public void testFractionReduction1() {
        Fraction f = new Fraction(1, 1);
        Fraction reduced = myMath.reduce(f);
        assertEquals(Integer.valueOf(1), reduced.getNumerator());
        assertEquals(Integer.valueOf(1), reduced.getDenumerator());
    }

    @Test
    public void testFractionReduction2() {
        Fraction f = new Fraction(10, 6);
        Fraction reduced = myMath.reduce(f);
        assertEquals(Integer.valueOf(5), reduced.getNumerator());
        assertEquals(Integer.valueOf(3), reduced.getDenumerator());
    }

    @Test
    public void testFractionReduction3() {
        Fraction f = new Fraction(10, 5);
        Fraction reduced = myMath.reduce(f);
        assertEquals(Integer.valueOf(2), reduced.getNumerator());
        assertEquals(Integer.valueOf(1), reduced.getDenumerator());
    }

    @Test
    public void testFractionToDouble1() {
        Fraction f = new Fraction(10, 5);
        Double result = myMath.toDouble(f);
        assertEquals(Double.valueOf(2.0), result);
    }

    @Test
    public void testFractionToDouble2() {
        Fraction f = new Fraction(10, 4);
        Double result = myMath.toDouble(f);
        assertEquals(Double.valueOf(2.5), result);
    }
}