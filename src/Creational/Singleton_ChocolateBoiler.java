package Creational;

public class Singleton_ChocolateBoiler {
	public static void main(String[] args) {
		ChocolateBolier coco = ChocolateBolier.getInstance();
		coco.fill();
		coco.boil();
		coco.drain();
	}
}

class ChocolateBolier {
	private boolean empty;
	private boolean boiled;
	private static ChocolateBolier uniqueBoiler = new ChocolateBolier(); // eager instantiation is a thread-safety pre-caution
	
	private ChocolateBolier() {
		empty = true;
		boiled = false;
	}
	
	public static ChocolateBolier getInstance() {  // synchronized will prevent multiple threads run this method at once and therefore create multiple instances on ChocolateBoiler
		return uniqueBoiler;
	}
	
	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
			System.out.println("filling the container with chocolate and milk...");
		}
	}
	
	public void boil() {
		if (!isEmpty() && !isBoiled()) {
			boiled = true;
			System.out.println("boiling the magic together...");
		}
	}

	public void drain() {
		if (!isEmpty() && isBoiled()) {
			empty = true;
			System.out.println("bringing love out to the light...");
		}
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public boolean isBoiled() {
		return boiled;
	}
}