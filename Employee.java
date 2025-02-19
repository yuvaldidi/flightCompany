package myCompany;
import java.util.ArrayList;
import java.util.List;

// Class representing an employee who wants to receive updates
public class Employee extends Observer {
    private String name;
    private boolean signedUp; // Flag to track if employee has signed up for updates
    private List<Flight> flights; // Reference to the airline the employee is registered with
	private Flight flight;

    public Employee(String name, Subject subject) {
        this.name = name;
        this.signedUp = false;
        this.flights = new ArrayList<>();
        this.subject = subject;
        this.subject.attach(this);
    }

    public String getName() {
        return name;
    }

    public boolean isSignedUp() {
        return signedUp;
    }

    // Method to authorize employee for a flight
    public boolean authorizeForFlight(Flight flight) {
        List<Employee> employeeList = flight.getEmployees();
        flight.addEmployee(this);
//        flight.registerObserver(this);
        return employeeList.contains(this);
    }
   
    // Method to sign up for updates from an airline
    public void signUpForUpdates(Flight flight) {
        if (authorizeForFlight(flight)) {
//            this.subject = flight;
            this.flights.add(flight);
            this.signedUp = true;
            this.flight = flight;
            flight.registerObserver(this);
            System.out.println(name + " signed up for updates from " + flight.getName());
        } else {
            System.out.println(name + " is not authorized to sign up for updates from " + flight.getName());
        }
    }
    
   /* public boolean authorizeForAirline(Airline airline) {
        List<Employee> employeeList = airline.getEmployees();
        signUpForUpdates(airline);
        return employeeList.contains(this);
    }

    
    // Method to sign up for updates from all flights in an airline
    public void signUpForUpdates(Airline airline) {
        for (Flight flight : airline.getFlights()) {
        	authorizeForFlight(flight);
            signUpForUpdates(flight);
        }
    }*/

    // Method to receive updates
    @Override
    public void update() {
        if (flight != null) {
            System.out.println("Employee update " + name + " from " + flight.getName() + ": " + subject.getState());
        } else {
            System.out.println("No flight has been found");
        }
    }


 // Method to see the employee list and passengers of a flight
    public void viewFlightDetails(Flight flight) {
        if (authorizeForFlight(flight)) {
            System.out.println("Employee " + name + " is authorized to view details of " + flight.getName());
            System.out.println("Employee List:");
            for (Observer observer : flight.getObservers()) {
                if (observer instanceof Employee) {
                    System.out.println(observer);
                }
            }
            System.out.println("Passenger List:");
            for (Observer observer : flight.getObservers()) {
                if (observer instanceof Passenger) {
                    System.out.println(observer);
                }
            }
        } else {
            System.out.println("Employee " + name + " is not authorized to view details of " + flight.getName());
        }
    }


    @Override
    public String toString() {
        return "Employee: " + name;
    }
}
