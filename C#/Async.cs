using System;
using System.Net.Http;
using System.Threading.Tasks;

class Scroller
{
    static async Task<long> GetWebPageSizeAsync(string url)
    {
        try
        {
            using HttpClient client = new();
            // Send the request and get the response
            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode(); // Throws if not successful

            // Check Content-Length header
            if (response.Content.Headers.ContentLength.HasValue)
            {
                return response.Content.Headers.ContentLength.Value;
            }
            else
            {
                // Fallback: read the content
                byte[] content = await response.Content.ReadAsByteArrayAsync();
                return content.Length;
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error: {ex.Message}");
            return -1;
        }
    }

    static async Task Main()
    {
        string url = "https://example.com";
        long size = await GetWebPageSizeAsync(url);
        Console.WriteLine($"Size of webpage '{url}': {size} bytes");
    }
}