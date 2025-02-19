package myCompany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create flights
        Flight flight1 = new Flight("El-Al", "F100", "TLV", "NY", 100.0, LocalDate.of(2024, 4, 12), LocalTime.of(9, 0), 120, 150);
        Flight flight2 = new Flight("El-Al", "F200", "TLV", "NY", 150.0, LocalDate.of(2024, 4, 12), LocalTime.of(11, 0), 180, 100);
        Flight flight3 = new Flight("Eista", "F300", "NY", "TLV", 200.0, LocalDate.of(2024, 4, 13), LocalTime.of(10, 0), 150, 200);
        Flight flight4 = new Flight("Eista", "F400", "TLV", "LON", 120.0, LocalDate.of(2024, 4, 14), LocalTime.of(12, 0), 90, 120);

        // Create airlines
        Airline airline1 = new Airline("El-Al");
        airline1.add(flight1);
        airline1.add(flight2);

        Airline airline2 = new Airline("El-Al");
        airline2.add(flight3);
        airline2.add(flight4);

        // Add airline2 to airline1
        airline1.add(airline2);

        // Create passengers
        Passenger passenger1 = new Passenger("yuval", new Subject());
        Passenger passenger2 = new Passenger("Gad", new Subject());
        Passenger passenger3 = new Passenger("Didi", new Subject());

        // Create employees
        Employee employee1 = new Employee("Keren", new Subject());
        Employee employee2 = new Employee("Ochia", new Subject());
        Employee employee3 = new Employee("Sagron", new Subject());

        employee1.signUpForUpdates(flight1);
        employee2.signUpForUpdates(flight1);
        employee3.authorizeForFlight(flight3);
        employee3.signUpForUpdates(flight3);
        
        //airline1.registerObserver(employee1);
        //flight1.registerObserver(employee2);
        //flight3.registerObserver(employee3);
        
        // Test payment and booking for Passenger1
        flight1.bookMe(passenger1, 1000.0);
        flight1.bookMe(passenger2, 500.0);
        flight2.bookMe(passenger3, 10.0);

     // Test search and booking for Passenger2
        SearchStrategy priceSearch = new PriceSearchStrategy();

        SearchStrategy durationSearch = new DurationSearchStrategy();

        SearchStrategy departureTimeSearch = new DepartureTimeSearchStrategy();

        SearchContext searchContext = new SearchContext(priceSearch);
        Map<String, List<Flight>> priceSearchResult = searchContext.executeStrategy(airline1.getFlights(), "TLV", "NY", 100.0);
        System.out.println("Price search result: " + priceSearchResult);

        searchContext = new SearchContext(durationSearch);
        Map<String, List<Flight>> durationSearchResult = searchContext.executeStrategy(airline1.getFlights(), "TLV", "NY", 150);
        System.out.println("Duration search result: " + durationSearchResult);

        searchContext = new SearchContext(departureTimeSearch);
        Map<String, List<Flight>> departureTimeSearchResult = searchContext.executeStrategy(airline1.getFlights(), "TLV", "NY", LocalTime.of(8, 0));
        System.out.println("Departure time search result: " + departureTimeSearchResult);

        // Change flight details to test observer notification
        flight1.setPrice(120.0);
        flight3.setDepartureTime(LocalTime.of(11, 0));
        flight2.setDelay(20);
       // airline2.applyDeal(50.0); // Apply a discount of $50 to all flights in airline2


        // Print main airline's flight details using getFlightDetails
        System.out.println("El-Al Flights:");
        for (Flight flight : airline1.getFlights()) {
            flight.printFlightDetails();
        }
        
        List<Passenger> passengers = flight1.getPassengers();
        System.out.println("Passengers on Flight 1:");
        for (Passenger passenger : passengers) {
            System.out.println(passenger.getName());
        }

        // Use getEmployees function
        List<Employee> employees = flight1.getEmployees();
        System.out.println("\nEmployees assigned to Flight 1:");
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
    }
}
