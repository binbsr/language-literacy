import time
import logging
import asyncio

# Configure logging
logging.basicConfig(level=logging.INFO)

# Retry decorator
def retry(max_attempts=3, delay=1):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for attempt in range(max_attempts):
                try:
                    return func(*args, **kwargs)
                except Exception as e:
                    logging.warning(f"Attempt {attempt + 1} failed: {e}")
                    if attempt == max_attempts - 1:
                        raise  # Re-raise the last exception if all attempts fail
                    time.sleep(delay)  # Wait before retrying
        return wrapper
    return decorator

# Example usage
class NetworkService:
    def __init__(self):
        self.attempt_count = 0

    @retry(max_attempts=3, delay=1)
    async def fetch_data(self):
        self.attempt_count += 1
        asyncio.sleep(0.1) # Simulate async work
        if self.attempt_count < 3:
            raise RuntimeError("Network error")
        return "Data fetched!"

# Test it
service = NetworkService()
try:
    result = service.fetch_data()
    print(result)
except Exception as e:
    print(f"Failed after retries: {e}")