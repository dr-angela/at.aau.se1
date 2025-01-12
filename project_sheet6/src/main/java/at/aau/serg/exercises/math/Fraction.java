package at.aau.serg.exercises.math;

public class Fraction {
    private Integer numerator;
    private Integer denumerator;

    public Fraction() {
    }

    public Fraction(Integer numerator, Integer denumerator) {
        this.numerator = numerator;
        this.denumerator = denumerator;
    }

    public Integer getNumerator() {
        return numerator;
    }

    public void setNumerator(Integer numerator) {
        this.numerator = numerator;
    }

    public Integer getDenumerator() {
        return denumerator;
    }

    public void setDenumerator(Integer denumerator) {
        this.denumerator = denumerator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != null ? !numerator.equals(fraction.numerator) : fraction.numerator != null) return false;
        return denumerator != null ? denumerator.equals(fraction.denumerator) : fraction.denumerator == null;
    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denumerator != null ? denumerator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fraction{" + "numerator=" + numerator + ", denumerator=" + denumerator + '}';
    }
}
