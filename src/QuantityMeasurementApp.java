public class QuantityMeasurementApp {

    // Enum with conversion factors (base = FEET)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double toBase(double value) {
            return value * factor;
        }

        public double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }

    // Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toBase(value);
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

            double base = this.toBaseUnit();
            double converted = targetUnit.fromBase(base);

            return new Quantity(converted, targetUnit);
        }

        // Equality (same as UC3/UC4)
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

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        double base = source.toBase(value);
        return target.fromBase(base);
    }

    // Demo methods (optional but good for viva)
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        double result = convert(value, from, to);
        System.out.println(value + " " + from + " = " + result + " " + to);
    }

    public static void demonstrateLengthConversion(Quantity q, LengthUnit to) {
        Quantity converted = q.convertTo(to);
        System.out.println(q + " = " + converted);
    }

    // Main method
    public static void main(String[] args) {

        // Static conversion
        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH)); // 12

        // Instance conversion
        Quantity q = new Quantity(3.0, LengthUnit.YARD);
        System.out.println(q.convertTo(LengthUnit.FEET)); // 9 FEET

        // CM → INCH
        System.out.println(convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH)); // ~0.3937

        // Demo method
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
    }
}