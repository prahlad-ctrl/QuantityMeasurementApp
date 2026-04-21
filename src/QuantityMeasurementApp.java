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

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
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

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (!Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = toBaseUnit();
            return new QuantityLength(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException();
            double sum = this.toBaseUnit() + other.toBaseUnit();
            return new QuantityLength(this.unit.convertFromBaseUnit(sum), this.unit);
        }

        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
            double sum = q1.toBaseUnit() + q2.toBaseUnit();
            return new QuantityLength(targetUnit.convertFromBaseUnit(sum), targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (!Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = toBaseUnit();
            return new QuantityWeight(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            if (other == null) throw new IllegalArgumentException();
            double sum = this.toBaseUnit() + other.toBaseUnit();
            return new QuantityWeight(this.unit.convertFromBaseUnit(sum), this.unit);
        }

        public static QuantityWeight add(QuantityWeight q1, QuantityWeight q2, WeightUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
            double sum = q1.toBaseUnit() + q2.toBaseUnit();
            return new QuantityWeight(targetUnit.convertFromBaseUnit(sum), targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        System.out.println(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));

        System.out.println(new QuantityWeight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM));

        System.out.println(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM)));

        System.out.println(QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        ));

        System.out.println(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(12.0, LengthUnit.INCH)));
    }
}