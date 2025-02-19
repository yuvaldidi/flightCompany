package myCompany;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// מימוש אסטרטגיית חיפוש לפי מחיר
public class PriceSearchStrategy implements SearchStrategy {
	
    private Double maxPrice;

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
	
    @Override
    public Map<String, List<Flight>> search(List<Flight> flights, String departure, String destination, Object a) {
        Map<String, List<Flight>> result = new HashMap<>();
        if (a instanceof Double) { // Check if 'a' is of type Double
            Double maxPrice = (Double) a; // Cast 'a' to Double
            List<Flight> filteredFlights = flights.stream()
                    .filter(flight -> flight.getDeparture().equals(departure)
                            && flight.getDestination().equals(destination)
                            && flight.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
            if (!filteredFlights.isEmpty()) {
                String key = departure + " to " + destination;
                result.put(key, filteredFlights);
            }
        }
        // Sort the flights based on price (ascending order)
        result.forEach((key, value) -> value.sort(Comparator.comparingDouble(Flight::getPrice)));
        return result;
    }

}