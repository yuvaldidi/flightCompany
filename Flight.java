package myCompany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flight extends AirlineComponent {
    private double price;
    private LocalTime departureTime;
    private int duration; // בדקות

//    private List<Observer> observers;
    private String destination;
    private String departure;
    private String airline;
    private LocalDate date;
    private int available;
    private List<Employee> employees;
    private List<Passenger> passengers;
    
    public Flight(String airline,String name, String departure, String destination, double price, LocalDate date, LocalTime departureTime, int duration,int available) {
    	super(name);
    	this.price = price;
        this.departureTime = departureTime;
        this.duration = duration;
        this.destination = destination;
        this.departure = departure;
//        this.observers = new ArrayList<>();
        this.airline = "";
        this.date = LocalDate.now();
        this.available = 150;
        this.employees = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }
   
    @Override
    public void add(AirlineComponent component) {
        // לא ניתן להוסיף רכיב לטיסה
    }

    @Override
    public void remove(AirlineComponent component) {
        // לא ניתן להסיר רכיב מטיסה
    }

    @Override
    public List<Flight> getFlights() {
        return Collections.singletonList(this);
    }
    
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        // Notify observers about the price change
        setState("Price changed to: " + price);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        // Notify observers about the duration change
        setState("Duration changed to: " + duration);
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalTime of){
        this.departureTime= of;
        // Notify observers about the price change
        setState("Time changed to: " + of);
    }

    public void setDelay(int delayMinutes) {
        this.departureTime = this.departureTime.plusMinutes(delayMinutes);
        // Notify observers about the delay
        setState("Departure delayed by: " + delayMinutes + " minutes");
    }
       

    public Iterable<Observer> getObservers() {
        return this.observers;
    }
    @Override
    public String toString() {
      return  getName() + " (" + getDeparture() + " -> " + getDestination() + 
             ", Departure: " + getDepartureTime() + 
             ", Duration: " + getDuration() + " mins, Price: " + getPrice() + ")";
    }
    public void printFlightDetails() {
        System.out.println("Flight details:");
        System.out.println("Name: " + getName());
        System.out.println("Price: " + getPrice());
        System.out.println("Departure time: " + getDepartureTime());
        System.out.println("Duration: " + getDuration() + " minutes");
    }
    
    public boolean bookMe(Passenger passenger,double amount) {
        if (available > 0) {
        	passenger.makePayment(amount, this);
            if (passenger.hasPaid()) {
            	this.registerObserver(passenger);
                passengers.add(passenger);
                available--;
                passenger.connectToFlight(this);
                return true;
            } else {
                System.out.println("Payment not received. Unable to book the passenger to flight:" + this.getName());
                return false;
            }
        } else {
            System.out.println("Sorry, the flight is full.");
            return false;
        }
    }

	public void addEmployee(Employee employee) {
        employees.add(employee);		
	}
}