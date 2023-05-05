package alarmPackages;

public class ConsoleLogger implements Logger {
    @Override
    public void log(PinEvent event) {
        System.out.println(event.getSeverity().toString() + " " + event.getSource().toString() + " " + event.getMessage());
    }
}
