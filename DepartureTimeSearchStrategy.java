
package myCompany;import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// מימוש אסטרטגיית חיפוש לפי זמן המראה
public class DepartureTimeSearchStrategy implements SearchStrategy {
	
    private LocalTime earliestDepartureTime;

    public LocalTime getEarliestDepartureTime() {
        return earliestDepartureTime;
    }

    public void setEarliestDepartureTime(LocalTime earliestDepartureTime) {
        this.earliestDepartureTime = earliestDepartureTime;
    }
    
    @Override
    public Map<String, List<Flight>> search(List<Flight> flights, String departure, String destination, Object a) {
        Map<String, List<Flight>> result = new HashMap<>();
        if (a instanceof LocalTime) { // Check if 'a' is of type LocalTime
            LocalTime earliestDepartureTime = (LocalTime) a; // Cast 'a' to LocalTime
            List<Flight> filteredFlights = flights.stream()
                    .filter(flight -> flight.getDeparture().equals(departure)
                            && flight.getDestination().equals(destination)
                            && flight.getDepartureTime().compareTo(earliestDepartureTime) >= 0)
                    .collect(Collectors.toList());
            if (!filteredFlights.isEmpty()) {
                String key = departure + " to " + destination;
                result.put(key, filteredFlights);
            }
        }
        // Sort the flights based on departure time (ascending order)
        result.forEach((key, value) -> value.sort(Comparator.comparing(Flight::getDepartureTime)));
        return result;
    }

}
