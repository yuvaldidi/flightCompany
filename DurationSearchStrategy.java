
package myCompany;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// מימוש אסטרטגיית חיפוש לפי משך הטיסה
public class DurationSearchStrategy implements SearchStrategy {
	
    private Integer maxDuration;
	
	 public Integer getMaxDuration() {
	        return maxDuration;
	    }

	    public void setMaxDuration(Integer maxDuration) {
	        this.maxDuration = maxDuration;
	    }
	    @Override
	    public Map<String, List<Flight>> search(List<Flight> flights, String departure, String destination, Object a) {
	        Map<String, List<Flight>> result = new HashMap<>();
	        if (a instanceof Integer) { // Check if 'a' is of type Integer
	            Integer maxDuration = (Integer) a; // Cast 'a' to Integer
	            List<Flight> filteredFlights = flights.stream()
	                    .filter(flight -> flight.getDeparture().equals(departure)
	                            && flight.getDestination().equals(destination)
	                            && flight.getDuration() <= maxDuration)
	                    .collect(Collectors.toList());
	            if (!filteredFlights.isEmpty()) {
	                String key = departure + " to " + destination;
	                result.put(key, filteredFlights);
	            }
	        }
	        // Sort the flights based on duration (ascending order)
	        result.forEach((key, value) -> value.sort(Comparator.comparingInt(Flight::getDuration)));
	        return result;
	    }

}
