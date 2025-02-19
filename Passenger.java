package myCompany;
// מחלקה שמייצגת נוסע שמעוניין לקבל עדכונים
public class Passenger extends Observer {
    private String name;
    private boolean paid;
	private Flight flight;


    public Passenger(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
        this.subject.attach(this);
        this.paid = false; // Initially, passenger has not paid

    }
    public String getName() {
        return name;
    }
    public void connectToFlight(Flight flight) {
        this.flight = flight;
    }
    @Override
    public void update() {
        if (flight != null) {
            System.out.println("Passenger update " + name + " from " + flight.getName() + ": " + subject.getState());
        } else {
            System.out.println("No flight has been found");
        }
    }
    @Override
    public String toString() {
        return getName(); 
    }
    
    public boolean hasPaid() {
        return paid;
    }

    // Method to simulate payment
    public void makePayment(double amount, Flight flight) {
        // Here you can implement the logic to process the payment
        // For simplicity, let's just set the paid status to true
       if(amount>=flight.getPrice()) {
    	   paid = true;
        System.out.println(name + " has paid " + flight.getPrice() + " successfully.");
       }
       else {
       System.out.println(name + " payment was unsuccessful");
       }
    }
}