package Java;

import java.util.List;

class Main
{
    // Message types
    record Email(String sender, String subject) { }
    record SMS(String phone, String text) { }
    record Alert(int severity, String message) { }

    // Process a message with pattern matching
    public static String processMessage(Object msg)
    {
        return switch (msg)
        {
            case Email(var sender, var subject) -> "Email from " + sender + ": " + subject;
            case SMS(var phone, var text) -> "SMS from " + phone + ": " + text;
            case Alert(var severity, var message) when severity >= 5->
                "High-priority alert: " + message + " (Severity: " + severity + ")";
            case Alert(var severity, var message) -> 
                "Low-priority alert: " + message + " (Severity: " + severity + ")";
            default -> "Unknown message type";
        }
        ;
    }

    public static void main(String[] args)
    {
        List<Object> messages = List.of(
            new Email("alice@example.com", "Meeting at 3 PM"),
            new SMS("+1234567890", "Call me back!"),
            new Alert(7, "Server down"),
            new Alert(2, "Low disk space"),
            "Random string"  // Fallback case
        );

        System.out.println("Using instanceof:");
        for (Object msg : messages)
        {
            System.out.println(processMessage(msg));
        }
    }
}
