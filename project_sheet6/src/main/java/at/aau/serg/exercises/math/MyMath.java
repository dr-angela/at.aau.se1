package at.aau.serg.exercises.math;

public class MyMath {
    public Double add(Double a, Double b) {
        return a + b;
    }

    public Double sub(Double a, Double b) {
        return a - b;
    }

    public Double mul(Double a, Double b) {
        return a * b;
    }

    public Double div(Double a, Double b) {
        if (b == 0) {
            return Double.NaN;
        }
        return a / b;
    }

    public Fraction reduce(Fraction x) {
        Integer ggt = ggt(x.getNumerator(), x.getDenumerator());
        Fraction f = new Fraction(x.getNumerator() / ggt, x.getDenumerator() / ggt);
        return f;
    }

    public Double toDouble(Fraction x) {
        return this.div(x.getNumerator() * 1.0d, x.getDenumerator() * 1.0d);
    }

    private Integer ggt(int zahl1, int zahl2) {
        while (zahl2 != 0) {
            if (zahl1 > zahl2) {
                zahl1 = zahl1 - zahl2;
            } else {
                zahl2 = zahl2 - zahl1;
            }
        }
        return zahl1;
    }
}
