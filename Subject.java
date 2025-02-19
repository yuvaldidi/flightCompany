package myCompany;

import java.util.ArrayList;
import java.util.List;

// ממשק לנושאים
class Subject {
	
	protected List<Observer> observers = new ArrayList<Observer>();
	private String state;
	    
	 public void removeObserver(Observer observer){
	        observers.remove(observer);		
	     }    
	 public void registerObserver(Observer observer){
        observers.add(observer);		
     }

     public void notifyAllObservers(){
        for (Observer observer : observers) {
           observer.subject=this;
           observer.update();
        }
     }
     
     public String getState() {
         return state;
      }
     
     public void setState(String state) {
         this.state = state;
         notifyAllObservers();
      }
     public void attach(Observer observer){
         observers.add(observer);		
      }
     
}