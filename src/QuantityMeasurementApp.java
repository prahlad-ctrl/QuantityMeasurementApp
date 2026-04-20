public class QuantityMeasurementApp {

    // Enum with all units
    enum LengthUnit {
        FEET(1.0),              // base unit
        INCH(1.0 / 12.0),       // 1 inch = 1/12 feet
        YARD(3.0),              // 1 yard = 3 feet
        CENTIMETER(0.0328084);  // 1 cm = 0.0328084 feet

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toBase(double value) {
            return value * conversionFactor;
        }
    }

    // Same Quantity class (NO CHANGE from UC3)
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        // Yard ↔ Feet
        System.out.println(new Quantity(1.0, LengthUnit.YARD)
                .equals(new Quantity(3.0, LengthUnit.FEET))); // true

        // Yard ↔ Inches
        System.out.println(new Quantity(1.0, LengthUnit.YARD)
                .equals(new Quantity(36.0, LengthUnit.INCH))); // true

        // CM ↔ Inches
        System.out.println(new Quantity(1.0, LengthUnit.CENTIMETER)
                .equals(new Quantity(0.393701, LengthUnit.INCH))); // true

        // Same unit
        System.out.println(new Quantity(2.0, LengthUnit.CENTIMETER)
                .equals(new Quantity(2.0, LengthUnit.CENTIMETER))); // true
    }
}