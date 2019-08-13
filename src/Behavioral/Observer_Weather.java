package Behavioral;
import java.util.ArrayList;
import java.util.List;
public class Observer_Weather {
	public static void main(String[] args) throws InterruptedException {
		Station st1 = new Station(); 
		WeatherData wd1 = new WeatherData();
		st1.setWeatherData(wd1);
		st1.start();
		
		Display statisics = new StatisticsDisplay();
		wd1.subscribe(statisics);
		Display forecast = new ForecastDisplay();
		wd1.subscribe(forecast);
		Thread.sleep(5000);
		wd1.unsubscribe(statisics);
	}
}
//////////////////////////////////////////////////////

class Station extends Thread {
	WeatherData weatherData;
	private double humidity = 60.5;
	private double temperature = 37.5;
	private double pressure = 25;
	public void run() {
		int days = 30;
		while (days != 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			humidity -= 0.5;
			temperature -= 0.5;
			pressure -= 0.5;
			weatherData.setMeasurements(this.temperature, this.humidity, this.pressure);
			days--;
		}
    }
	
	public void setWeatherData(WeatherData weatherData) {
		this.weatherData = weatherData; 
	}
}
////////////////////////////////////////////////////// Subject knows nothing about its subscribers rather than that they are observers(implement Observer_ interface	) 
interface Subject {
	public void subscribe(Observer_ o);
	public void unsubscribe(Observer_ o);
	public void notifySubscribers();
}

class WeatherData implements Subject {
	private double humidity = 0;
	private double temperature = 0;
	private double pressure = 0;
	private List <Observer_> observers = new ArrayList <Observer_>();
	private boolean loopingOnSubscirbers = false;	// Semaphore; removing element from a list while looping on it will cause a concurrency error
	
	public void setMeasurements(double temperature, double humidity, double pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
	
	public void measurementsChanged() {
		loopingOnSubscirbers = true;
		notifySubscribers();
		loopingOnSubscirbers = false;
	}

	public void subscribe(Observer_ d) {
		observers.add(d);
	}
	
	public void unsubscribe(Observer_ d) {
		while(loopingOnSubscirbers);
		int i = observers.indexOf(d);
		if (i>0) observers.remove(i);
	}

	public void notifySubscribers() {
		for (Observer_ observer : observers) {
			observer.update(this.temperature, this.humidity, this.pressure);
		}
	}
}
////////////////////////////////////////////////////// Observer know nothing about the Subject, they don't even know that the Subject exist
interface Observer_ {
	public void update(double temperature, double humidity, double pressure) ;
}

abstract class Display implements Observer_ {
	double temperature;
	double humidity;
	double pressure;
	
	public void update(double temperature, double humidity, double pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		display();
	}
	
	abstract public void display();
}

class CurrentConsitionsDisplay extends Display {
	@Override
	public void display() {
		System.out.println("------------------------------------ \n"
				+ "Current conditions are:- \n"
				+ "temperature:" + temperature + "\n"
				+ "humidity:" + humidity + "\n"
				+ "pressure:" + pressure + "\n");
	}
}

class StatisticsDisplay extends Display {
	@Override
	public void display() {
		System.out.println("------------------------------------ \n"
				+ "Statisitcs:- \n"
				+ "temperature:" + temperature + "\n"
				+ "humidity:" + humidity + "\n"
				+ "pressure:" + pressure + "\n");	
	}
}

class ForecastDisplay extends Display {
	@Override
	public void display() {
		System.out.println("------------------------------------ \n"
				+ "Forecast Today:- \n"
				+ "temperature:" + temperature + "\n"
				+ "humidity:" + humidity + "\n"
				+ "pressure:" + pressure + "\n");	
	}
}