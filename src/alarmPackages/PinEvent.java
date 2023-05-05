package alarmPackages;

public class PinEvent {
    private EventSource source;
    private Severity severity;
    private String message;

    public PinEvent(EventSource source, Severity severity, String message) {
        this.source = source;
        this.severity = severity;
        this.message = message;
    }

    public EventSource getSource() {
        return this.source;
    }

    public Severity getSeverity() {
        return this.severity;
    }

    public String getMessage() {
        return this.message;
    }
}
