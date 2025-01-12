package at.aau.serg.exercises.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyMathTest {

    @Test
    public void test1() {
        MyMath myMath = new MyMath();
        Double add = myMath.add(1d, 2d);

        if (!add.equals(3d)) {
            throw new AssertionError("Not equals!");
        }
    }

    @Test
    public void allWorks() {
        Double x = 10d;
        Double y = 5d;
        Double add = new MyMath().add(x, y);
        Double sub = new MyMath().sub(x, y);
        Double mul = new MyMath().mul(x, y);
        Double div = new MyMath().div(x, y);

        assertEquals(add, new Double(15));
        assertEquals(sub, new Double(5));
        assertEquals(mul, new Double(50));
        assertEquals(div, new Double(2));
    }

    @Test
    public void x2() {
        Fraction f = new Fraction(1, 1);
        MyMath mm = new MyMath();
        Fraction reduced = mm.reduce(f);
        assertEquals(Integer.valueOf(1), reduced.getNumerator());
        assertEquals(Integer.valueOf(1), reduced.getDenumerator());

        f = new Fraction(10, 6);
        mm = new MyMath();
        reduced = mm.reduce(f);
        assertEquals(Integer.valueOf(5), reduced.getNumerator());
        assertEquals(Integer.valueOf(3), reduced.getDenumerator());

        f = new Fraction(10, 5);
        mm = new MyMath();
        reduced = mm.reduce(f);
        assertEquals(Integer.valueOf(2), reduced.getNumerator());
        assertEquals(Integer.valueOf(1), reduced.getDenumerator());

        f = new Fraction(10, 5);
        mm = new MyMath();
        Double aDouble = mm.toDouble(f);
        assertEquals(new Double(2), aDouble);

        f = new Fraction(10, 4);
        mm = new MyMath();
        aDouble = mm.toDouble(f);
        assertEquals(new Double(2.5d), aDouble);
    }
}
