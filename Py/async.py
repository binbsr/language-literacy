import aiohttp
import asyncio


async def get_webpage_size(url):
    try:
        async with aiohttp.ClientSession() as session:
            async with session.get(url) as response:
                # Get the content length from headers if available
                content_length = response.headers.get('Content-Length')
                if content_length:
                    size = int(content_length)
                else:
                    # If not available, read the full content
                    content = await response.read()
                    size = len(content)
                return size
    except Exception as e:
        print(f"Error: {e}")
        return -1

async def main():
    url = "https://example.com"
    size = await get_webpage_size(url)
    print(f"Size of webpage '{url}': {size} bytes")

# Run the async function
if __name__ == "__main__":
    asyncio.run(main())