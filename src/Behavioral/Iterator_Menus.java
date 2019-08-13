package Behavioral;
import java.util.ArrayList;
import java.util.Iterator;

public class Iterator_Menus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> lunchMenu = new ArrayList();
		lunchMenu.add("steak");
		lunchMenu.add("soup");
		String[] dinerMenu = {"pizza", "burger"};
		Waitress w = new Waitress();
		w.addMenu(new LunchMenu(lunchMenu));
		w.addMenu(new DinerMenu(dinerMenu));
		
		w.getMenus();

	}

}


// Bad system

//class DinerMenu {
//	String [] menuItems;
//	
//	public DinerMenu (String [] m) {
//		menuItems = m;
//	}
//	public String[] getMenu() {
//		return menuItems;
//	}
//}
//
//class LunchMenu {
//	ArrayList<String> menuItems;
//	
//	public LunchMenu (ArrayList<String> m) {
//		menuItems = m;
//	}
//
//	public ArrayList<String> getMenu() {
//		return menuItems;
//	}
//}
//
//class Waitress {
//	String [] dinerMenuItems = new DinerMenu().getMenu();
//	ArrayList<String> lunchMenuItems = new LunchMenu().getMenu();
//	
//	public void getMenus() {
//		for (int i = 0; i < dinerMenuItems.length; i++) {
//			System.out.println(dinerMenuItems[i]);
//		}
//		
//		for (int i = 0; i < lunchMenuItems.size(); i++) {
//			System.out.println(lunchMenuItems.get(i));
//		}
//	}
//}


// Good system

interface Aggregate {
	public Iterator createIterator();
}

class LunchMenu implements Aggregate {
	ArrayList<String> menuItems;
	int i = 0;

	public LunchMenu (ArrayList<String> m) {
		menuItems = m;
	}
	
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return new LunchMenuIterator(menuItems);
	}
}

class LunchMenuIterator implements Iterator<String> {
	ArrayList<String> menuItems;
	int i = 0;

	public LunchMenuIterator (ArrayList<String> ar) {
		this.menuItems = ar;
	}
	public boolean hasNext() {
		if (i >= menuItems.size() || menuItems.get(i) == null ) return false;
		return true;
	}

	public String next() {
		return menuItems.get(i++);
	}

}

class DinerMenu implements Aggregate {
	String [] menuItems;
	int i = 0;
	
	public DinerMenu (String [] m) {
		menuItems = m;
	}
	
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return new DinerMenuIterator(menuItems);
	}
}

class DinerMenuIterator implements Iterator<String> {
	String [] menuItems;
	int i = 0;

	public DinerMenuIterator (String[] ar) {
		this.menuItems = ar;
	}
	
	public boolean hasNext() {
		if ( i >= menuItems.length || menuItems[i] == null ) return false;
		return true;
	}

	public String next() {
		return menuItems[i++];
	}

}

class Waitress {
	
	ArrayList<Iterator> MenusIterators = new ArrayList();
	
	public void addMenu(Aggregate i) {
		MenusIterators.add(i.createIterator());
	}
	
	public void getMenus() {
		for (Iterator iterator : MenusIterators) {
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		}
	}
	
}