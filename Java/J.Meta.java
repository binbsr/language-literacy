package Java;

import java.lang.annotation.*;
import java.lang.reflect.*;

// Retry annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Retry {
    int maxAttempts() default 3;
    int delayMs() default 1000;
}

// Retry utility
class RetryHandler {
    public static void executeWithRetry(Object instance, String methodName) throws Exception {
        Class<?> clazz = instance.getClass();
        Method method = clazz.getMethod(methodName);
        Retry retry = method.getAnnotation(Retry.class);

        if (retry != null) {
            for (int attempt = 0; attempt < retry.maxAttempts(); attempt++) {
                try {
                    method.invoke(instance);
                    return; // Success
                } catch (Exception e) {
                    System.out.println("Attempt " + (attempt + 1) + " failed: " + e.getCause().getMessage());
                    if (attempt == retry.maxAttempts() - 1) throw e; // Last attempt
                    Thread.sleep(retry.delayMs());
                }
            }
        } else {
            method.invoke(instance); // No retry, just call
        }
    }
}

// Example usage
class NetworkService {
    private int attemptCount = 0;

    @Retry(maxAttempts = 3, delayMs = 1000)
    public void fetchData() {
        attemptCount++;
        if (attemptCount < 3) {
            throw new RuntimeException("Network error");
        }
        System.out.println("Data fetched!");
    }
}

class Main {
    public static void main(String[] args) {
        NetworkService service = new NetworkService();
        try {
            RetryHandler.executeWithRetry(service, "fetchData");
        } catch (Exception e) {
            System.out.println("Failed after retries: " + e.getMessage());
        }
    }
}

