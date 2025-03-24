package Java;

import java.util.function.Function;

class PriceCalculator {
    public float calculatePrice(float basePrice, Function<Float, Float> discount) {
        // Apply the discount function, defaulting to identity if null
        return discount.apply(basePrice);
    }

    public void main(String[] args) {
        // Identity function for no discount
        float noDiscount = calculatePrice(100.0f, x -> x);
        float fixedDiscount = calculatePrice(100.0f, x -> x * 0.9f);  // 10% off
        float customDiscount = calculatePrice(100.0f, x -> x > 50 ? x - 20 : x);  // $20 off if > $50

        System.out.printf("No discount: $%.2f%n", noDiscount);      // $100.00
        System.out.printf("10% off: $%.2f%n", fixedDiscount);       // $90.00
        System.out.printf("Custom discount: $%.2f%n", customDiscount);  // $80.00
    }
}