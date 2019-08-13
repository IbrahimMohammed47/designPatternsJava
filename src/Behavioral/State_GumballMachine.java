package Behavioral;

public class State_GumballMachine {

	public static void main(String[] args) {
		
//		// old system test
//		GumballMachine gm = new GumballMachine(5);
//		System.out.println(gm);
//		
//		gm.insertQuarter();
//		gm.turnCrank();
//		
//		System.out.println(gm);
//		
//		gm.insertQuarter();
//		gm.ejectQuarter();
//		gm.turnCrank();
//		
//		System.out.println(gm);
//		
//		gm.insertQuarter();
//		gm.turnCrank();
//		gm.insertQuarter();
//		gm.turnCrank();
//		gm.ejectQuarter();
//		
//		System.out.println(gm);
//		
//		gm.insertQuarter();
//		gm.insertQuarter();
//		gm.turnCrank();
//		gm.insertQuarter();
//		gm.turnCrank();
//		gm.insertQuarter();
//		gm.turnCrank();
//		
//		System.out.println(gm);
	}

}



//  // Old System
//class GumballMachine {
//	final static int SOLD_OUT = 0;
//	final static int NO_QUARTER = 1;
//	final static int HAS_QUARTER = 2;
//	final static int SOLD = 3;
//	
//	int state = SOLD_OUT;
//	int count = 0;
//	
//	public GumballMachine (int count) {
//		this.count = count;
//		if (count > 0) {
//			state = NO_QUARTER;
//		}
//	}
//	
//	private void changeState(String action) {
//		switch (action) {
//		case "insert": state = HAS_QUARTER; break;
//		case "eject":  state = NO_QUARTER;  break;
//		case "crank":  state = SOLD; ;break;
//		case "dispense": state = count == 0 ? SOLD_OUT: NO_QUARTER; break;
//		default: System.out.println("undefined operation"); break;
//		}
//	}
//	
//	public void insertQuarter () {
//		if (state == NO_QUARTER) {
//			changeState("insert");
//			System.out.println("inserted quarter");
//		}
//		else
//			System.out.println("unallowed operation");
//	}
//	
//	public void ejectQuarter () {
//		if (state == HAS_QUARTER) {
//			changeState("eject");
//			System.out.println("ejected quarter");
//		}
//		else 
//			System.out.println("unallowed operation");
//
//	}
//	
//	public void turnCrank () {
//		if (state == HAS_QUARTER) {
//			changeState("crank");
//			System.out.println("turned crank");
//			dispense();
//		}
//		else 
//			System.out.println("unallowed operation");
//
//	}
//	
//	private void dispense() {
//		if (state == SOLD) {
//			count--;
//			changeState("dispense");
//			System.out.println("dispensed gumball");
//		}
//		else 
//			System.out.println("unallowed operation");
//
//	}
//	
//	public String toString() {
//		return this.state + "";
//	}
//}


// // New System
class GumballMachine {

	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	
	State state;
	int count = 0;
	
	public GumballMachine (int count) {
		
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
		this.count = count;
		if (count > 0) 
			state = new NoQuarterState(this);
	}
	
	public State getSoldOutState() {
		return soldOutState;
	}

	public State getNoQuarterState() {
		return noQuarterState;
	}

	public State getHasQuarterState() {
		return hasQuarterState;
	}

	public State getSoldState() {
		return soldState;
	}

	public void insertQuarter() {
		state.insertQuarter();
	}
	
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}
	
	public int getCount() {
		return count;
	}
	
	public void setState (State s) {
		this.state = s;
	}
	
	public String toString() {
		return this.state + "";
	}
	
	public void decrement() {
		if (count != 0)
			count --;
	}
}
	
interface State {
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}

class SoldState implements State{
	GumballMachine gm;
	
	public SoldState (GumballMachine gm) {
		this.gm = gm;
	}

	public void insertQuarter() {
		System.out.println("wait u lil slack");
	}
	public void ejectQuarter() {
		System.out.println("WTH are u trying to eject");
	}
	public void turnCrank() {
		System.out.println("crank is already turned");
	}
	public void dispense() {
		System.out.println("gumball is being dispensed...");	
		if (gm.getCount() == 0) {
			gm.setState(gm.getSoldOutState());
		}
		else {
			gm.decrement();
			gm.setState(gm.getNoQuarterState());
		}
			
	}
	
}

class SoldOutState implements State {
	GumballMachine gm;
	
	public SoldOutState (GumballMachine gm) {
		this.gm = gm;
	}
	public void insertQuarter() {
		System.out.println("no gumballs in here :(");
	}
	public void ejectQuarter() {
		System.out.println("you're literally ejecting nothing, no gumballs here");
	}
	public void turnCrank() {
		System.out.println("don't waste your effort, no gumballs here");
	}
	public void dispense() {
		System.out.println("nothing to dispense");
	}
	
}

class NoQuarterState implements State {
	GumballMachine gm;
	
	public NoQuarterState (GumballMachine gm) {
		this.gm = gm;
	}
	public void insertQuarter() {
		System.out.println("inserting the quarter...");
		gm.setState(gm.getHasQuarterState());
	}
	public void ejectQuarter() {
		System.out.println("nothing to eject");
	}
	public void turnCrank() {
		System.out.println("insert the quarter first");
	}
	public void dispense() {
		System.out.println("insert the quarter first then turn up the crank");
	}
	
}

class HasQuarterState implements State {
	GumballMachine gm;
	
	public HasQuarterState (GumballMachine gm) {
		this.gm = gm;
	}
	public void insertQuarter() {
		System.out.println("your current gumball isn't dispensed yet");
	}
	public void ejectQuarter() {
		System.out.println("ejecting your quarter back...");
		gm.setState(gm.getNoQuarterState());
	}
	public void turnCrank() {
		System.out.println("turning up the crank...");
		gm.setState(gm.getSoldState());
	}
	public void dispense() {
		System.out.println("crank need to be turned first");
	}
	
}
