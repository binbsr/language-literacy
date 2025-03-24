using System;

class Program45
{
    // Message types
    public record Email(string Sender, string Subject);
    public record SMS(string Phone, string Text);
    public record Alert(int Severity, string Message);

    // Process a message with pattern matching
    static string ProcessMessage(object msg) => msg switch
    {
        Email { Sender: var sender, Subject: var subject } => $"Email from {sender}: {subject}",
        SMS { Phone: var phone, Text: var text } => $"SMS from {phone}: {text}",
        Alert { Severity: >= 5, Message: var message, Severity: var severity } => $"High-priority alert: {message} (Severity: {severity})",
        Alert { Message: var message, Severity: var severity } => $"Low-priority alert: {message} (Severity: {severity})",
        _ => "Unknown message type"
    };

    static void Main()
    {
        var messages = new object[]
        {
            new Email("alice@example.com", "Meeting at 3 PM"),
            new SMS("+1234567890", "Call me back!"),
            new Alert(7, "Server down"),
            new Alert(2, "Low disk space"),
            "Random string"  // Fallback case
        };

        foreach (var msg in messages)
        {
            Console.WriteLine(ProcessMessage(msg));
        }
    }
}
