from dataclasses import dataclass
from typing import Any

# Message types
@dataclass
class Email:
    sender: str
    subject: str

@dataclass
class SMS:
    phone: str
    text: str

@dataclass
class Alert:
    severity: int
    message: str

# Process a message with pattern matching
def process_message(msg: Any) -> str:
    match msg:
        case Email(sender, subject):
            return f"Email from {sender}: {subject}"
        case SMS(phone, text):
            return f"SMS from {phone}: {text}"
        case Alert(severity, message) if severity >= 5:
            return f"High-priority alert: {message} (Severity: {severity})"
        case Alert(severity, message):
            return f"Low-priority alert: {message} (Severity: {severity})"
        case _:
            return "Unknown message type"

# Usage
messages = [
    Email("alice@example.com", "Meeting at 3 PM"),
    SMS("+1234567890", "Call me back!"),
    Alert(7, "Server down"),
    Alert(2, "Low disk space"),
    "Random string"  # Fallback case
]

for msg in messages:
    print(process_message(msg))