package Structural;

public class Decorator_Beverages {
	public static void main (String[] args) {
		Costable beverage1 = new Milk((new Soy((new Tea())))) ;
		Costable beverage2 = new Whip((new Mocha((new Espresso()))));
		System.out.println("ORDER: "+ beverage1.getDescription() + " ==> COST: " + beverage1.cost());
		System.out.println("ORDER: "+ beverage2.getDescription() + " ==> COST: " + beverage2.cost());

	}
}

interface Costable {
	public double cost();
	public String getDescription();
}

abstract class Beverage implements Costable {
	protected String description;
	protected double cost;
	
	public double cost() {
		return this.cost;
	}
	
	public String getDescription() {
		return description;
	}
}

class Tea extends Beverage {
	public Tea() {
		this.cost = .90;
		this.description = "Hot English Tea";
	}
}

class Espresso extends Beverage {
	public Espresso() {
		this.cost = 1.99;
		this.description = "Delicious American Espresso";
	}
}


abstract class Condiment implements Costable{
	protected double cost; 
	protected Costable innerCostable;
	protected String description;
	
	public Condiment(Costable c) {
		innerCostable = c;
	}
	
	public double cost() {
		return this.cost + innerCostable.cost();
	}
	
	public String getDescription() {
		return innerCostable.getDescription() + " + "+ this.description;
	}
}

class Soy extends Condiment {
	public Soy(Costable c) {
		super(c);
		this.cost = .15;
		this.description = "soy";
	}
}

class Milk extends Condiment {
	public Milk(Costable c) {
		super(c);
		this.cost = .10;
		this.description = "milk";
	}
}

class Mocha extends Condiment {
	public Mocha(Costable c) {
		super(c);
		this.cost = .20;
		this.description = "mocha";
	}
}

class Whip extends Condiment {
	public Whip(Costable c) {
		super(c);
		this.cost = .10;
		this.description = "whipped";
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Horrible Design	
//public class Decorator_Beverages {
//	public static void main (String[] args) {
//		System.out.println((new Tea()).setSoy(true).setMilk(true).setMocha(true).cost());
//	}
//}
//
//abstract class Beverage {
//	protected String description;
//	private boolean milk;
//	private boolean soy;
//	private boolean mocha;
//	private boolean whip;
//	
//	
//	public String getDescription() {
//		return description;
//	}
//
//
//	public boolean hasMilk() {
//		return milk;
//	}
//
//
//	public Beverage setMilk(boolean milk) {
//		this.milk = milk;
//		return this;
//	}
//
//
//	public boolean hasSoy() {
//		return soy;
//	}
//
//
//	public Beverage setSoy(boolean soy) {
//		this.soy = soy;
//		return this;
//	}
//
//
//	public boolean hasMocha() {
//		return mocha;
//	}
//
//
//	public Beverage setMocha(boolean mocha) {
//		this.mocha = mocha;
//		return this;
//	}
//
//
//	public boolean hasWhip() {
//		return whip;
//	}
//
//
//	public void setWhip(boolean whip) {
//		this.whip = whip;
//	}
//
//
//	public double cost() {
//		return (hasMilk()? 5:0) + (hasSoy()?3:0) + (hasSoy()?4:0) + (hasMocha()? 7:0);
//	}
//}
//
//class Tea extends Beverage {
//	public Tea() {
//		this.description = "";
//	}
//	
//	public double cost() {	
//		return 5 + super.cost();
//	}
//}

////////////////////////////////////////////////////////////////////////
