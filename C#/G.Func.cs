public class PriceCalculator
{
    public float CalculatePrice(float basePrice, Func<float, float>? discount = null)
    {
        // Default to identity function if no discount provided
        discount ??= x => x;
        return discount(basePrice);
    }

    void Main()
    {
        float noDiscount = CalculatePrice(100.0f);
        float fixedDiscount = CalculatePrice(100.0f, x => x * 0.9f);  // 10% off
        float customDiscount = CalculatePrice(100.0f, x => x > 50 ? x - 20 : x);  // $20 off if > $50

        Console.WriteLine($"No discount: ${noDiscount:F2}");      // $100.00
        Console.WriteLine($"10% off: ${fixedDiscount:F2}");       // $90.00
        Console.WriteLine($"Custom discount: ${customDiscount:F2}");  // $80.00
    }   
}