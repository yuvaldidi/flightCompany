package myCompany;
import java.util.ArrayList;
import java.util.List;

// מחלקת חברת תעופה שמייצגת אוסף של טיסות ותת-חברות
public class Airline extends AirlineComponent {
    private List<AirlineComponent> components;
    private List<Employee> employees;


    public Airline(String name) {
        super(name);
        this.components = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    @Override
    public void add(AirlineComponent component) {
        components.add(component);
    }

    public void remove(AirlineComponent component) {
        if (component instanceof Flight) {
            Flight flight = (Flight) component;
            if (components.remove(flight)) {
                flight.setState("Flight " + flight.getName() + " has been canceled."); // הודעה למעקבים על ביטול הטיסה
            }
        } else if (component instanceof Airline) {
            Airline airline = (Airline) component;
            for (AirlineComponent subComponent : airline.getFlights()) {
                remove(subComponent); // קריאה רקורסיבית לפונקציה removeComponent עבור כל רכיב בתוך החברה הזו
            }
        }
    }

    @Override
    public List<Flight> getFlights() {
      List<Flight> flights = new ArrayList<>();
      List<List<Flight>> subAirlineFlights = new ArrayList<>(); // List of sub-airline flight lists

      // Gather flights associated with this airline (exclude sub-airline flights)
      for (AirlineComponent component : components) {
        if (component instanceof Flight && !(component instanceof Airline)) {
          flights.add((Flight) component);
        }
      }

      // Gather flights from sub-airlines recursively
      for (AirlineComponent component : components) {
        if (component instanceof Airline) {
          Airline subAirline = (Airline) component;
          List<Flight> subFlights = subAirline.getFlights();
          if (!subFlights.isEmpty()) {
            subAirlineFlights.add(subFlights); // Add sub-airline flights to separate list
          }
        }
      }

      // Combine flights in the desired order
      List<Flight> allFlights = new ArrayList<>();
      allFlights.addAll(flights); // Add main airline flights first

      // Add sub-airline flights after main airline flights
      for (List<Flight> subFlightList : subAirlineFlights) {
        allFlights.addAll(subFlightList);
      }

      return allFlights;
    }

    public String toString() {
        return getName();
    }

    public List<Employee> getEmployees() {
        for (AirlineComponent component : components) {
            if (component instanceof Flight) {
                Flight flight = (Flight) component;
                employees.addAll(flight.getEmployees());
            } else if (component instanceof Airline) {
                Airline subAirline = (Airline) component;
                employees.addAll(subAirline.getEmployees());
            }
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
   /* public void applyDeal(double discountAmount) {
        for (AirlineComponent component : components) {
            if (component instanceof Flight) {
                Flight flight = (Flight) component;
                double originalPrice = flight.getPrice();
                double discountedPrice = originalPrice - discountAmount;
                if (discountedPrice > 0) {
                    flight.setPrice(discountedPrice);
                    List<Passenger> passengers = flight.getPassengers();
                    passengers.forEach(passenger -> subject.setState("Your state message"));

                } else {
                    System.out.println("Discounted price cannot be negative. Skipping...");
                }
            } else if (component instanceof Airline) {
                Airline subAirline = (Airline) component;
                subAirline.applyDeal(discountAmount); // Recursively apply deal to sub-airlines
            }
        }
    }*/


}
