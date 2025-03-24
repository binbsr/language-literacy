using System.Diagnostics;

partial class Program
{
    static long SquareAndSum(int start, int end) 
        => Enumerable.Range(start, end).Sum();

    void Compute()
    {
        int max = 1_000_000;
        int chunkSize = 250_000;  // Split into 4 chunks
        var chunks = Enumerable.Range(0, 4)
            .Select(i => (start: i * chunkSize + 1, end: Math.Min((i + 1) * chunkSize + 1, max + 1)));

        var stopwatch = Stopwatch.StartNew();

        // Parallel computation
        List<long> results = [];
        Parallel.ForEach(chunks, new ParallelOptions { MaxDegreeOfParallelism = 4}, chunk =>
        {            
            var chunkSum = SquareAndSum(chunk.start, chunk.end);
            results.Add(chunkSum);
        });

        long total = results.Sum();
        stopwatch.Stop();

        Console.WriteLine($"Sum of squares: {total:N0}");
        Console.WriteLine($"Time taken: {stopwatch.Elapsed.TotalSeconds:F2} seconds");
    }
}