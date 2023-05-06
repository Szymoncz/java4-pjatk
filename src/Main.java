package alarmPackages;
public class Main {
    public static void main(String[] args) {
        
        Alarm alarm = new Alarm() {
            @Override
            public void trigger(PinEvent event) {
                System.out.println("Alarm triggered: " + event.getMessage());
                // Tutaj można dodać kod obsługujący alarm, np. wysyłanie powiadomienia do firmy ochroniarskiej.
            }
        };

        
        Safe safe = new Safe(1234, alarm);

        
        Logger fileLogger = new FileLogger("events.log");
        safe.addLogger(fileLogger);

       
        Logger consoleLogger = new ConsoleLogger();
        safe.addLogger(consoleLogger);

        // Testujemy działanie sejfu
        safe.enterPin(5678); // Błędny PIN, spodziewany wynik: alarm triggered
        safe.close(); // Niepowodzenie, spodziewany wynik: "The safe is already closed."
        safe.enterPin(1234); // Poprawny PIN, spodziewany wynik: "Pin accepted. The safe is now open."
        safe.enterPin(5678); // Błędny PIN, spodziewany wynik: alarm triggered
        safe.close(); // Powodzenie, spodziewany wynik: "The safe is now closed."

  
        safe.removeLogger(fileLogger);
        safe.removeLogger(consoleLogger);
    }
}
