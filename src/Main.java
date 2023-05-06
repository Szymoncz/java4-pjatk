/*
Autor: Szymon Czudowski - s26858
Pomoc: stack overflow
Zadanie 4:
Zadaniem jest stworzenie klasy symulującej działanie sejfu. Użytkownik by otworzyć sejf musi podać pin. W wypadku podania błędnego pinu do sejfu, zostanie wszczęty alarm. Działania podjęte w wyniku uruchomienia alarmu nie zależą od wykonawców sejfu. Co się stanie w odpowiedzi na wprowadzenie błędnego pinu zależy, od właściciela sejfu i zasobności jego portfela. Może on wynająć firmę ochroniarską, spuścić kratę, wypuścić psy itp. Sejf powinien akceptować dowolne działania spełniające założenia wyrażone w interfejsie Alarm.

Każdy dostawca urządzenia alarmowego pozwala do niego podłączyć dowolne urządzenie rejestrujące zdarzenia, tzw. Logger. Logger uwzględnia: - poziom ważności zdarzenia: Severity - źródło zdarzenia: EventSource - komunikat typu String

Zadanie 1 (4 punkty)
Zaimplementuj klasę Safe wraz z wymaganymi klasami Alarm i PinEvent, bez systemu rejestracji zdarzeń.

Zadanie 2 (4 punkty)
Do implementacji z zadania 1 dodaj opisany system rejestracji zdarzeń wraz z przykładowymi implementacjami klas FileLogger oraz ConsoleLogger.

Zadanie 3 (2 punkty)
Przeprowadź testy poprawności działania sejfu uwzględniając różne konfiguracje alarmów i urządzeń rejestrujących.

*/

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
