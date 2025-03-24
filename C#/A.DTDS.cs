bool emailSent = true;

byte x1 = byte.MaxValue;
short x2 = short.MaxValue;
int x3 = int.MaxValue;
long x4 = long.MaxValue;
Half f5 = Half.MaxValue;
float x5 = float.MaxValue;
double x6 = double.MaxValue;
decimal x7 = decimal.MaxValue;

char x8 = char.MaxValue;
string x9 = "something";

var info = $"{emailSent}, {x1}, {x2}, {x3}, {x4}, {x5}, {x6}, {x7}, {x8}, {x9}";
Console.Write(info);


// Collections: Sequences, Set, Maps
(int, string, float) tuple = (3, "yes", 7.5f);
int[][] numbers = [[1, 2], [2, 3]];
List<string> names = ["Bishnu", "Brahma", "Mahesh"];
HashSet<float> weights = [23.4f, 45.6f];
Dictionary<string, float> people = new() { ["bsr"] = 2.0f };

Console.Write(numbers);


// Funtions
bool SendEmail(
    List<string> addresses,
    string sender,
    List<string>? cc,
    string subject,
    List<string>? body,
    EmailType emailType = EmailType.Internal
) => emailSent;

// Querying, Projections etc.
int[] nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
var result = nums.Where(x => x % 2 == 0)
                  .Select(x => x * x);

// Asynchronous Patterns
async Task<int> FindSizeOfPageAsync(Uri uri) 
{
    var client = new HttpClient();
    byte[] data = await client.GetByteArrayAsync(uri);
    return data.Length;
}


enum EmailType { Internal = 1, Confedential, Public }





