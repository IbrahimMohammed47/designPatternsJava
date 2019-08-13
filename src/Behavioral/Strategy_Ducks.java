package Behavioral;
public class Strategy_Ducks {
	public static void main(String[] args) {
		Duck reddy = new RedHeadDuck();
		reddy.performFly();
		reddy.setFlyBehavior(new FlyLikeB());
		reddy.performFly();
	}
}

/*
 * When we see a BEHAVIOR that VARY a lot across subclasses, we need to SEPARATE those behaviors
 * and ENCAPSULATE them into their own classes away from their clients, the client will have access
 * to these behaviors using COMPOSITION (as instances), this will let us add dozens of sub-clients 
 * without headaches and also have the ability to change their behavior at the RUNTIME
 * */

/////////////////////////////////////////////////////
interface FlyBehavior {
	public void fly();
}

class FlyLikeA implements FlyBehavior {
	public void fly() {
		System.out.println("fly the A way");
	}
}

class FlyLikeB implements FlyBehavior {
	public void fly() {
		System.out.println("fly the B way");
	}
}

class FlyLikeC implements FlyBehavior {
	public void fly() {
		System.out.println("fly the C way");
	}
}
///////////////////////////////////////////////////
interface QuackBehavior {
	public void quack();
}

class QuackLikeD implements QuackBehavior {
	public void quack() {
		System.out.println("quack the D way");
	}
}

class QuackLikeE implements QuackBehavior {
	public void quack() {
		System.out.println("quack the E way");
	}
}
/////////////////////////////////////////////////////
abstract class Duck {
	private FlyBehavior fb;
	private QuackBehavior qb;
	
	public Duck() {
		
	}
	
	public void swim() {
		System.out.println("gluglugluglgu");
	}
	
	public void performFly() {
		fb.fly();				//Delegate to behavior class 
	}
	
	public void performQuack() {
		qb.quack();				//Delegate to behavior class
	}
	
	public void setFlyBehavior (FlyBehavior fb) {
		this.fb = fb;
	}
	
	public void setQuackBehavior (QuackBehavior qb) {
		this.qb = qb;
	}
	
	abstract public void display();
}

class RedHeadDuck extends Duck {
	public RedHeadDuck() {
		setFlyBehavior(new FlyLikeA());
		setQuackBehavior(new QuackLikeD());
	}
	
	public void display() {
		System.out.println("reddish");
	}
}

class MallardDuck extends Duck {
	public MallardDuck() {
		setFlyBehavior(new FlyLikeB());
		setQuackBehavior(new QuackLikeD());
	}
	
	public void display() {
		System.out.println("mallardish");
	}
}

class RubberDuck extends Duck {
	public RubberDuck() {
		setFlyBehavior(new FlyLikeC());
		setQuackBehavior(new QuackLikeE());
	}
	
	public void display() {
		System.out.println("rubberish");
	}
}
