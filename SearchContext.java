package myCompany;
import java.util.List;
import java.util.Map;

public class SearchContext {
	private SearchStrategy searchStrategy;
	
	public SearchContext(SearchStrategy searchStrategy) {
	
		this.searchStrategy = searchStrategy;
	}
	
	public Map<String, List<Flight>> executeStrategy(List<Flight> flights, String departure, String destination, Object a){
	      return this.searchStrategy.search(flights, departure, destination, a);
	
	   
	}
}
