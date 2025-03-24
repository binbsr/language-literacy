from multiprocessing import Pool
import time

def square_and_sum(range_chunk):
    # Calculate sum of squares for a given range.
    return sum(x * x for x in range_chunk)

if __name__ == "__main__":
    numbers = range(1, 1_000_001)  # 1 to 1,000,000
    chunk_size = 250_000  # Split into 4 chunks
    chunks = [range(i, min(i + chunk_size, 1_000_001)) for i in range(1, 1_000_001, chunk_size)]

    start_time = time.time()

    # Use a pool of 4 processes
    with Pool(processes=4) as pool:
        results = pool.map(square_and_sum, chunks)
    
    total = sum(results)
    end_time = time.time()

    print(f"Sum of squares: {total:,}")
    print(f"Time taken: {end_time - start_time:.2f} seconds")