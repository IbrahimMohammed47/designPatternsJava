package Structural;
import java.util.ArrayList;
import java.util.Iterator;

public class Composite_Menus {
	public static void main(String [] args) {
		MenuComponent panCakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast to start working u little procrastinator");
		MenuComponent dinerMenu = new Menu("DINER MENU", "Dinner because no one sleeps without dinner");
		MenuComponent cafeMenu = new Menu("CAFE MENU", "Cafe and other HOT hot beverages");
		MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert with double s");
		
		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
		allMenus.add(panCakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeMenu);	
		
		dinerMenu.add(new MenuItem("pasta", "Spaghetti with marinara sauce", false, 3.89));
		dinerMenu.add(dessertMenu);
		
		cafeMenu.add(new MenuItem("Mocha", "hot, delicious and lovely", false, 1.89));
		cafeMenu.add(new MenuItem("Latte", "Milky and creamy", false, 1.79));
		cafeMenu.add(new MenuItem("CoffeMix", "love love love", false, 6.89));
		
		dessertMenu.add(new MenuItem("Cinamon", "classic cinamon combined with tender dough and fluffy signature frostin", true, 2.59));
		dessertMenu.add(new MenuItem("Donut", "round things with tasty flavors", false, 1.59));
		
		Waiter w = new Waiter(allMenus);
		w.printMenu();

	}
}

abstract class MenuComponent {
	// composite methods
	public void add (MenuComponent mc) {
		throw new UnsupportedOperationException();
	}
	public void remove (MenuComponent mc) {
		throw new UnsupportedOperationException();
	}
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	// operation methods
	public String getName() {
		throw new UnsupportedOperationException();
	}
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	public boolean isVege() {
		throw new UnsupportedOperationException();
	}
	
	// shared methods
	public void print() {
		throw new UnsupportedOperationException();
	}
}

class MenuItem extends MenuComponent {
	String name;
	String description;
	boolean vege;
	double price;
	
	public MenuItem(String name, String description, boolean vege, double price) {
		this.name = name;
		this.description = description;
		this.vege = vege;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public boolean isVege() {
		return vege;
	}
	public double getPrice() {
		return price;
	}
	public void print() {
		System.out.print(" " + getName());
		if (isVege()) {
			System.out.print("(v)");
		}
		System.out.println(", " + getPrice());
		System.out.println("   --" + getDescription());
	}
}

class Menu extends MenuComponent {
	ArrayList<MenuComponent> mcs = new ArrayList <MenuComponent>();
	String name;
	String description;
	
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public void add(MenuComponent mc) {
		this.mcs.add(mc);
	}
	public void remove(MenuComponent mc) {
		this.mcs.remove(mc);
	}
	public MenuComponent getChild(int nth) {
		return mcs.get(nth);
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("---------------------");
		
		Iterator <MenuComponent> iterator = mcs.iterator();
		while (iterator.hasNext()) {
			iterator.next().print();
		}
	}
}

class Waiter {
	MenuComponent allMenus;
	
	public Waiter(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		allMenus.print();
	}
}