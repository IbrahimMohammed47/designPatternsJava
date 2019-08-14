import java.util.ArrayList;

/*
 * 1- used Adapter Pattern to adapt goose behavior to ducks behavior
 * 2- used Decorator Pattern to add (counting quacks) behavior
 * 3- used Abstract Factory Pattern to get a factory that create countable ducks because client forgets to wrap ducks with QuackCounter, also having a factory that creates uncountable ducks
 * 4- used Composite Pattern to manage a collection of ducks as one unit that can have different or shared behavior with a single duck (making a tree of ducks and duck families)
 * 5- used Observer Pattern to notify quackologist(Observer) whenever a Quackable object quacks
 * 6- used Strategy Pattern to encapsulate Observable behavior into a separate classes that can be composed with Quackable objects
 * 7- used Null Object Pattern to flag a duck as anonymous(can't be observed) 
 */
public class Compound_patterns {
	public static void main (String [] args) {
		AbstractDuckFactory cdf = new CountingDuckFactory();
		
		Quackable q1 = cdf.createMallardDuck();
		Quackable q2 = cdf.createRedheadDuck();
		Quackable q3 = cdf.createDuckCall();
		Quackable q4 = cdf.createRubberDuck();
		Quackable q5 = cdf.createGooseDuck();
		Flock flock = new Flock();
		Flock sublock = new Flock();
		
		flock.add(q1);
		flock.add(q2);
		flock.add(q3);
		sublock.add(q4);
		sublock.add(q5);
		flock.add(sublock);
		q1.anonymousToggle();
		
		Observer quackologist = new Observer();
		flock.registerObserver(quackologist);
		flock.quack();
		System.out.println("quack counts: "+ QuackCounter.quackCount);
	}
}

abstract class Quackable implements QuackObservable {
	QuackObservable observable;
	QuackObservable currentObservable;

	public Quackable() {
		observable = new Observable(this);
		currentObservable = observable;
	}
	public void quack() {
		System.out.println("quack");
		notifyObservers();
	}
	public void anonymousToggle() {
		if (currentObservable == observable) {
			currentObservable = new NullObservable(this);
		}
		else {
			currentObservable = observable;
		}
	}
	
	public void registerObserver(Observer o) {
		currentObservable.registerObserver(o);
	}

	public void notifyObservers() {
		currentObservable.notifyObservers();
	}
}

class MallardDuck extends Quackable {
	
	public MallardDuck() {
		super();
	}
}

class RedHeadDuck extends Quackable {
	
	public RedHeadDuck() {
		super();
	}	
}

class DuckCall extends Quackable {
	
	public DuckCall() {
		super();
	}
	
	public void quack() {
		System.out.println("kwak");
		notifyObservers();
	}	
}

class RubberDuck extends Quackable {
	
	public RubberDuck() {
		super();	
	}
	
	public void quack() {
		System.out.println("squeak");
		notifyObservers();
	}	
}

class Goose {
	public void honk() {
		System.out.println("honk");
	}
}

class GooseAdapter extends Quackable {
	Goose g;
	
	public GooseAdapter(Goose g) {
		super();
		this.g = g;
	}

	public void quack() {
		g.honk();
		notifyObservers();
	}
}

class QuackCounter extends Quackable {
	Quackable duck;
	static int quackCount;
	
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}
	public void quack() {
		duck.quack();
		quackCount++;
	}
	
	public void registerObserver(Observer o) {
		duck.registerObserver(o);
	}

	public void notifyObservers() {
		duck.notifyObservers();
	}
	
	public void anonymousToggle() {
		duck.anonymousToggle();
	}	
}

class Flock extends Quackable {

	ArrayList<Quackable> quackers = new ArrayList<Quackable>();
	
	public void add(Quackable quacker) {
		quackers.add(quacker);
	}
	
	public void quack() {
		for (Quackable quacker : quackers) {
			quacker.quack();
		}
	}
	
	public void registerObserver(Observer o) {
		for (Quackable quacker : quackers) {
			quacker.registerObserver(o);
		}
	}

	public void notifyObservers() {
		for (Quackable quacker : quackers) {
			quacker.notifyObservers();
		}
	}

	public void anonymousToggle() {
		for (Quackable quacker : quackers) {
			quacker.anonymousToggle();
		}
	}	
}

abstract class AbstractDuckFactory {
	abstract public Quackable createMallardDuck();
	abstract public Quackable createRedheadDuck();
	abstract public Quackable createDuckCall();
	abstract public Quackable createRubberDuck();
	abstract public Quackable createGooseDuck();
}

class DuckFactory extends AbstractDuckFactory {

	public Quackable createMallardDuck() {
		return new MallardDuck();
	}

	public Quackable createRedheadDuck() {
		return new RedHeadDuck();
	}

	public Quackable createDuckCall() {
		return new DuckCall();
	}

	public Quackable createRubberDuck() {
		return new RubberDuck();
	}
	
	public Quackable createGooseDuck() {
		return new GooseAdapter(new Goose());
	}
}

class CountingDuckFactory extends AbstractDuckFactory {

	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck()); 
	}

	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedHeadDuck());
	}

	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}

	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	}
	
	public Quackable createGooseDuck() {
		return new QuackCounter(new GooseAdapter(new Goose()));
	}
}

interface QuackObservable {
	public void registerObserver(Observer o);
	public void notifyObservers();
}

interface QuackObserver {
	public void update(QuackObservable duck);

}

class Observer implements QuackObserver {

	public void update(QuackObservable duck) {
		System.out.println("Screen: " + duck + " just quacked");
	}
}


class Observable implements QuackObservable {

	ArrayList<Observer> observers = new ArrayList<Observer>();
	QuackObservable duck;
	public Observable(QuackObservable duck) {
		this.duck = duck;
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(duck);
		}
	}
}

class NullObservable implements QuackObservable {

	ArrayList<Observer> observers = new ArrayList<Observer>();
	QuackObservable duck;
	public NullObservable(QuackObservable duck) {
		this.duck = duck;
	}
	
	public void registerObserver(Observer o) {
		System.out.println("anonymous ducks can't be observed");
	}

	public void notifyObservers() {
	}
}

