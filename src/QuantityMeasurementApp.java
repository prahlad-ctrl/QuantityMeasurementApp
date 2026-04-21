enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.0328084);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}

public class QuantityMeasurementApp {

    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (!Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = toBaseUnit();
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity(converted, targetUnit);
        }

        public Quantity add(Quantity other) {
            if (other == null) throw new IllegalArgumentException();
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = this.unit.convertFromBaseUnit(sumBase);
            return new Quantity(result, this.unit);
        }

        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
            double sumBase = q1.toBaseUnit() + q2.toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new Quantity(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            Quantity other = (Quantity) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) throw new IllegalArgumentException();
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public static void main(String[] args) {

        System.out.println(new Quantity(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCH));

        System.out.println(Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCH),
                LengthUnit.FEET
        ));

        System.out.println(new Quantity(36.0, LengthUnit.INCH)
                .equals(new Quantity(1.0, LengthUnit.YARD)));

        System.out.println(Quantity.add(
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(3.0, LengthUnit.FEET),
                LengthUnit.YARD
        ));

        System.out.println(new Quantity(2.54, LengthUnit.CENTIMETER)
                .convertTo(LengthUnit.INCH));
    }
}