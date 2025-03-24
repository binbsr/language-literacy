using System.Reflection;

[AttributeUsage(AttributeTargets.Method)]
public class RetryAttribute(int attempts, int delay) : Attribute
{
    public int Attempts { get; } = attempts;
    public int Delay { get; } = delay;
}

public class RetryProxy<T> : DispatchProxy
{
    private T _decorated;

    public static T Create(T decorated)
    {
        object proxy = Create<T, RetryProxy<T>>();
        ((RetryProxy<T>)proxy).SetParameters(decorated);
        return (T)proxy;
    }

    private void SetParameters(T decorated)
    {
        _decorated = decorated;
    }

    protected override object Invoke(MethodInfo targetMethod, object[] args)
    {
        var retryAttr = targetMethod.GetCustomAttribute<RetryAttribute>();
        if (retryAttr != null)
        {
            int attempts = retryAttr.Attempts;
            for (int i = 0; i < attempts; i++)
            {
                try
                {
                    return targetMethod.Invoke(_decorated, args);
                }
                catch (Exception ex)
                {
                    if (i == attempts - 1)
                        throw;
                    Console.WriteLine($"Attempt {i + 1} failed: {ex.Message}");
                }
            }
        }
        return targetMethod.Invoke(_decorated, args);
    }
}

// Example usage

class NetworkService1 {
    private int attemptCount = 0;

    [Retry(attempts: 3, delay: 1000)]
    public void FetchData() {
        attemptCount++;
        if (attemptCount < 3) {
            throw new Exception("Network error");
        }
        Console.WriteLine("Data fetched!");
    }
}

class Program455
{
    static void Main()
    {
        var service = RetryProxy<NetworkService1>.Create(new NetworkService1());
        try
        {
            service.FetchData();
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Final exception: {ex.Message}");
        }
    }
}