package myCompany;
import java.util.ArrayList;
import java.util.List;

// מחלקה אבסטרקטית לכל ישות במודל ההיררכי
public abstract class AirlineComponent extends Subject {
    private String number;
//    protected List<Observer> observers; // רשימת הצופים

    public AirlineComponent(String number) {
        this.number = number;
        this.observers = new ArrayList<>();
    }

    public String getName() {
        return number;
    }

    public abstract void add(AirlineComponent component);
    public abstract void remove(AirlineComponent component);
    public abstract List<Flight> getFlights();

    // מימוש מתודות ממשק Subject
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

//    @Override
//    public void notifyAllObservers() {
//
//        for (Observer observer : observers) {
//            observer.update();
//        }
//    }
}
