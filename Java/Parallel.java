package Java;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

class SumOfSquares {
    class SquareSumTask extends RecursiveTask<Long> {
        private final int start;
        private final int end;

        SquareSumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            var nums = Stream.iterate(start, i -> i + 1).limit(end).toArray(Integer[]::new);
            long sum = Arrays.stream(nums).mapToInt(Integer::intValue).sum();            
            return sum;
        }
    }

    public void main(String[] args) {
        int max = 1_000_000;
        int chunkSize = 250_000; // Split into 4 chunks
        ForkJoinPool pool = new ForkJoinPool(4); // 4 threads

        long startTime = System.nanoTime();

        // Create tasks for each chunk
        SquareSumTask[] tasks = new SquareSumTask[4];
        for (int i = 0; i < 4; i++) {
            int start = i * chunkSize + 1;
            int end = Math.min(start + chunkSize, max + 1);
            tasks[i] = new SquareSumTask(start, end);
            pool.submit(tasks[i]);
        }
        
        // Collect results
        long total = 0;
        for (SquareSumTask task : tasks) {
            total += task.join();
        }

        long endTime = System.nanoTime();

        System.out.printf("Sum of squares: %,d%n", total);
        System.out.printf("Time taken: %.2f seconds%n", (endTime - startTime) / 1_000_000_000.0);
    }
}