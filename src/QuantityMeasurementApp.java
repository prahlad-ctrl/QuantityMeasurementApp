public class QuantityMeasurementApp {

    // Inner class for Feet
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {

            // Same reference check
            if (this == obj) return true;

            // Null and type check
            if (obj == null || this.getClass() != obj.getClass()) return false;

            // Cast and compare
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method (for testing)
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Are equal? " + f1.equals(f2)); // true
    }
}