using System;

// Extension Methods
public static class DateTimeExtensions
{
    public static int GetWorkingDaysUntil(this DateTime startDate, DateTime endDate)
    {
        if (startDate > endDate)
        {
            throw new ArgumentException("Start date must be before end date.");
        }

        int workingDays = 0;
        DateTime currentDate = startDate;

        while (currentDate <= endDate)
        {
            if (currentDate.DayOfWeek != DayOfWeek.Saturday && 
                currentDate.DayOfWeek != DayOfWeek.Sunday)
            {
                workingDays++;
            }
            currentDate = currentDate.AddDays(1);
        }

        return workingDays;
    }
}

class Program56464
{
    static void Main()
    {
        DateTime start = new(2025, 3, 24); // Monday
        DateTime end = new(2025, 3, 30);   // Sunday
        int days = start.GetWorkingDaysUntil(end);
        Console.WriteLine($"Working days: {days}");  // Output: Working days: 5
    }
}