package Java;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Async {
    public static CompletableFuture<Long> getWebPageSizeAsync(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(response -> {
                    // Check if Content-Length header is available
                    String contentLength = response.headers().firstValue("Content-Length").orElse(null);
                    if (contentLength != null) {
                        try {
                            return Long.parseLong(contentLength);
                        } catch (NumberFormatException e) {
                            // Fallback to body size if header is invalid
                        }
                    }
                    // Get size from response body
                    byte[] body = response.body();
                    return (long) body.length;
                })
                .exceptionally(throwable -> {
                    System.out.println("Error: " + throwable.getMessage());
                    return -1L;
                });
    }

    public static void main(String[] args) {
        String url = "https://example.com";
        CompletableFuture<Long> sizeFuture = getWebPageSizeAsync(url);
        sizeFuture.thenAccept(size -> 
            System.out.println("Size of webpage '" + url + "': " + size + " bytes")
        ).join(); // Wait for the result
    }
}