package Structural;

public class Adapter_TurkeyDucks {

	public static void main(String[] args) {
		Duck_ d1 = new MallardDuck_();
		Duck_ d2 = new TurkeyAdapter(new WildTurkey());
		
		d1.fly();
		d2.fly();
		
		d1.quack();
		d2.quack();
	}

}
//////////////////////////////

interface Duck_ {
	public void quack();
	public void fly();
}

class MallardDuck_ implements Duck_ {
	public void quack() {
		System.out.println("quack!");
	}

	public void fly() {
		System.out.println("i'm flying");
	}	
}
///////////////////////////////

interface Turkey {
	public void gooble();
	public void fly();
}

class WildTurkey implements Turkey {

	public void gooble() {
		System.out.println("gooble!");
	}

	public void fly() {
		System.out.println("i'm flying for a short distance");
	}
	
}

/////////////////////////////// we are low on ducks, so we will use our turkeys and make them disguise as ducks  

class TurkeyAdapter implements Duck_ {
	Turkey turkey;
	public TurkeyAdapter (Turkey turkey) {
		this.turkey = turkey;
	}
	public void quack() {
		turkey.gooble();
	}

	public void fly() {
		for (int i = 0; i < 4; i++) {
			turkey.fly();
		}
	}
	
}