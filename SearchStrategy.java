package myCompany;
import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    Map<String, List<Flight>> search(List<Flight> flights, String departure, String destination, Object a);
    
    
}
