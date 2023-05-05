package alarmPackages;
import java.util.ArrayList;
import java.util.List;

public class Safe {
    private int pin;
    private boolean isOpen;
    private Alarm alarm;
    private List<Logger> loggers;

    public Safe(int pin, Alarm alarm) {
        this.pin = pin;
        this.isOpen = false;
        this.alarm = alarm;
        this.loggers = new ArrayList<>();
    }

    public void enterPin(int pin) {
        if (this.isOpen) {
            System.out.println("The safe is already open.");
            return;
        }

        if (this.pin == pin) {
            System.out.println("Pin accepted. The safe is now open.");
            this.isOpen = true;
            logEvent(new PinEvent(EventSource.SAFE, Severity.LOW, "Safe opened."));
        } else {
            System.out.println("Wrong pin entered. Alarm triggered.");
            this.alarm.trigger(new PinEvent(EventSource.SAFE, Severity.HIGH, "Wrong pin entered."));
            logEvent(new PinEvent(EventSource.SAFE, Severity.MEDIUM, "Wrong pin entered."));
        }
    }

    public void close() {
        if (!this.isOpen) {
            System.out.println("The safe is already closed.");
            return;
        }

        System.out.println("The safe is now closed.");
        this.isOpen = false;
        logEvent(new PinEvent(EventSource.SAFE, Severity.LOW, "Safe closed."));
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void addLogger(Logger logger) {
        this.loggers.add(logger);
    }

    public void removeLogger(Logger logger) {
        this.loggers.remove(logger);
    }

    private void logEvent(PinEvent event) {
        for (Logger logger : this.loggers) {
            logger.log(event);
        }
    }
}












