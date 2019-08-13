package Behavioral;

public class TemplateMethod_CoffeeTea {

	public static void main(String[] args) {
		
		Tea_ t1 = new Tea_();
		Coffee c1 = new Coffee();
		t1.prepareRecipe();	
		c1.prepareRecipe();
	}

}

////////////////////////////////// Bad System ? code duplication
//class Coffee {
//	public void prepareRecipe() {
//		boilWater();
//		brewCoffeeGrinds();
//		pourInCup();
//		addSugarAndMilk();
//	}
//	
//	public void boilWater() {
//		System.out.println("Boiling water...");
//	}
//	
//	public void brewCoffeeGrinds() {
//		System.out.println("Dripping coffee through filter...");
//	}
//	
//	public void pourInCup() {
//		System.out.println("Pouring into cup...");
//	}
//	
//	public void addSugarAndMilk() {
//		System.out.println("Adding sugar and milk...");
//	}
//}
//
//class Tea_ {
//	public void prepareRecipe() {
//		boilWater();
//		steepTeaBag();
//		pourInCup();
//		addLemon();
//	}
//	
//	public void boilWater() {
//		System.out.println("Boiling water...");
//	}
//	
//	public void steepTeaBag() {
//		System.out.println("Steeping the tea...");
//	}
//	
//	public void pourInCup() {
//		System.out.println("Pouring into cup...");
//	}
//	
//	public void addLemon() {
//		System.out.println("Adding lemon...");
//	}
//}

///////////////////////////////// Good System
abstract class CaffeineBeverage {
	
	final public void prepareRecipe() {	// TEMPLATE METHOD	// final prevents subclasses from overriding this method
		boilWater();
		brew();
		pourInCup();
		addCondiments();
		hook();
		System.out.println("------------------------- <3");
	}
	
	abstract void brew();
	
	abstract void addCondiments();
	
	final void boilWater() {
		System.out.println("Boiling water...");
	}
	
	final void pourInCup() {
		System.out.println("Pouring into cup...");
	}
	
	void hook() {}
}

class Coffee extends CaffeineBeverage{
	
	void brew() {
		System.out.println("Dripping coffee through filter...");
	}
	
	void addCondiments() {
		System.out.println("Adding sugar and milk...");
	}
	
	void hook() {
		System.out.println("coffee is love");
	}
}

class Tea_ extends CaffeineBeverage{

	void brew() {
		System.out.println("Steeping the tea...");
	}
	
	void addCondiments() {
		System.out.println("Adding lemon...");
	}
}