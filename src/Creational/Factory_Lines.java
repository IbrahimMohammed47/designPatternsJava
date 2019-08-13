package Creational;
//
////====================================================================| Simple Factory |====================================================================//
//public class Factory_Lines {
//
//	public static void main(String[] args) {
//		Line o1 = LineFactory.orderMeALine(1);
//		o1.printName();
//		Line o2 = LineFactory.orderMeALine(2);
//		o2.printName();
//		Line o3 = LineFactory.orderMeALine(3);
//		o3.printName();		
//	}
//
//}
////////////////////////////////////////////////////////////////////////////////
//
//class LineFactory {
//	//THIS CODE IS NOT LIKELY TO CHANGE IN THE FUTURE
//	public static Line orderMeALine(int type) {
//		Line my_line;
//		my_line = createLine(type);		
//		return my_line;
//	}
//	
//	//THIS CODE MAY CHANGE IN THE FUTURE --> this is the Factory Method
//	private static Line createLine(int type){
//		Line my_line;
//		switch (type) {
//			case 1: my_line = new HorizontalLine();break;
//			case 2: my_line = new VerticalLine();break;
//			case 3: my_line = new SkewLine();break;
//			default:my_line = null;
//		}
//		return my_line;
//	}
//}
////////////////////////////////////////////////////////////////////////////////
//
//abstract class Line{
//	protected int x;
//	protected int y;
//	
//	Line(){
//		x= 0;
//		y= 0;
//	}
//
//	abstract public void printName();
//
//}
//
//class HorizontalLine extends Line{
//	HorizontalLine(){
//		x=1;
//	}
//
//	@Override
//	public void printName() {
//		System.out.println("this line represents a row");
//	}
//}
//
//class VerticalLine extends Line{
//	VerticalLine(){
//		y=1;
//	}
//
//	@Override
//	public void printName() {
//		System.out.println("this line represents a column");
//	}
//}
//
//class SkewLine extends Line{
//	SkewLine(){
//		x=1;
//		y=1;
//	}
//
//	@Override
//	public void printName() {
//		System.out.println("this line represents a skew");
//	}
//}


////====================================================================| Factory Method |====================================================================//
//public class Factory_Lines {
//
//	public static void main(String[] args) {
//		LineFactory straightLineFac = new StraightLineFactory();
//		LineFactory curvedLineFac = new CurvedLineFactory();
//
//		Line o1 = straightLineFac.orderMeALine(1);
//		o1.printName();
//		Line o2 = curvedLineFac.orderMeALine(2);
//		o2.printName();
//	}
//
//}
////////////////////////////////////////////////////////////////////////////////
//
//abstract class LineFactory {
//	public Line orderMeALine(int type) {
//		Line my_line;
//		my_line = createLine(type);		
//		return my_line;
//	}
//	abstract protected Line createLine(int type);
//}
//
//class StraightLineFactory extends LineFactory{
//	Line my_line;
//	protected Line createLine(int type) {
//		switch (type) {
//			case 1: my_line = new HorizontalLine();break;
//			case 2: my_line = new VerticalLine();break;
//			case 3: my_line = new SkewLine();break;
//			default:my_line = null;
//		}
//		sharpen(my_line);	// That's where factories are different
//		// other code that shapes a straight line
//		return my_line;
//	}
//
//	private void sharpen(Line l) {
//		System.out.println("the line is being sharpened...");
//	}
//}
//
//class CurvedLineFactory extends LineFactory{
//	Line my_line;
//	protected Line createLine(int type) {
//		switch (type) {
//			case 1: my_line = new HorizontalLine();break;
//			case 2: my_line = new VerticalLine();break;
//			case 3: my_line = new SkewLine();break;
//			default:my_line = null;
//		}
//		bend(my_line);	 //  That's where factories are different
//		// other code that shapes a curved line
//		return my_line;
//	}
//	private void bend(Line l) {
//		System.out.println("the line is being bended...");
//	}
//}
////////////////////////////////////////////////////////////////////////////////
//
//abstract class Line{
//	protected int x;
//	protected int y;
//	
//	Line(){
//		x= 0;
//		y= 0;
//	}
//
//	abstract public void printName();
//
//}
//
//class HorizontalLine extends Line{
//	HorizontalLine(){
//		x=1;
//	}
//
//	@Override
//	public void printName() {
//		System.out.println("this line represents a row");
//	}
//}
//
//class VerticalLine extends Line{
//	VerticalLine(){
//		y=1;
//	}
//
//	@Override
//	public void printName() {
//		System.out.println("this line represents a column");
//	}
//}
//
//class SkewLine extends Line{
//	SkewLine(){
//		x=1;
//		y=1;
//	}
//
//	@Override
//	public void printName() {
//		System.out.println("this line represents a skew");
//	}
//}

//====================================================================| Abstract Factory |====================================================================//
public class Factory_Lines {

	public static void main(String[] args) {
		LineFactory straightLineFac = new StraightLineFactory();
		LineFactory curvedLineFac = new CurvedLineFactory();

		Line w_s = straightLineFac.orderMeAWhiteLine(1);
		Line b_s = straightLineFac.orderMeABlackLine(1);
		Line w_c = curvedLineFac.orderMeAWhiteLine(1);
		Line b_c = curvedLineFac.orderMeABlackLine(1);
	}

}
//////////////////////////////////////////////////////////////////////////////

abstract class LineFactory {
	public WhiteLine orderMeAWhiteLine(int type) {
		WhiteLine my_line;
		my_line = createWhiteLine(type);		
		return my_line;
	}
	public BlackLine orderMeABlackLine(int type) {
		BlackLine my_line;
		my_line = createBlackLine(type);		
		return my_line;
	}
	abstract protected WhiteLine createWhiteLine(int type);
	abstract protected BlackLine createBlackLine(int type);
}

class StraightLineFactory extends LineFactory{
	Line my_line;
	protected WhiteLine createWhiteLine(int type) {
		switch (type) {
			case 1: my_line = new WhiteHorizontalLine();break;
			case 2: my_line = new WhiteVerticalLine();break;
			default:my_line = null;
		}
		sharpen(my_line);	// That's where factories are different
		// other code that shapes a straight line
		return (WhiteLine) my_line;
	}
	
	protected BlackLine createBlackLine(int type) {
		switch (type) {
			case 1: my_line = new BlackHorizontalLine();break;
			case 2: my_line = new BlackVerticalLine();break;
			default:my_line = null;
		}
		sharpen(my_line);	// That's where factories are different
		// other code that shapes a straight line
		return (BlackLine) my_line;
	}

	private void sharpen(Line l) {
		System.out.println("the line is being sharpened...");
	}
}

class CurvedLineFactory extends LineFactory{
	Line my_line;
	protected WhiteLine createWhiteLine(int type) {
		switch (type) {
			case 1: my_line = new WhiteHorizontalLine();break;
			case 2: my_line = new WhiteVerticalLine();break;
			default:my_line = null;
		}
		bend(my_line);	// That's where factories are different
		// other code that shapes a straight line
		return (WhiteLine) my_line;
	}
	
	protected BlackLine createBlackLine(int type) {
		switch (type) {
			case 1: my_line = new BlackHorizontalLine();break;
			case 2: my_line = new BlackVerticalLine();break;
			default:my_line = null;
		}
		bend(my_line);	// That's where factories are different
		// other code that shapes a straight line
		return (BlackLine) my_line;
	}

	private void bend(Line l) {
		System.out.println("the line is being bended...");
	}
}

//////////////////////////////////////////////////////////////////////////////
interface Line {
	public void printName();
}

abstract class BlackLine implements Line{
	protected int x;
	protected int y;
	
	BlackLine(){
		x= 0;
		y= 0;
	}

	abstract public void printName();

}

class BlackHorizontalLine extends BlackLine{
	BlackHorizontalLine(){
		x=1;
	}

	@Override
	public void printName() {
		System.out.println("this line represents a row");
	}
}

class BlackVerticalLine extends WhiteLine{
	BlackVerticalLine(){
		y=1;
	}

	@Override
	public void printName() {
		System.out.println("this line represents a column");
	}
}


abstract class WhiteLine implements Line{
	protected int x;
	protected int y;
	
	WhiteLine(){
		x= 0;
		y= 0;
	}

	abstract public void printName();

}

class WhiteHorizontalLine extends WhiteLine{
	WhiteHorizontalLine(){
		x=1;
	}

	@Override
	public void printName() {
		System.out.println("this line represents a row");
	}
}

class WhiteVerticalLine extends WhiteLine{
	WhiteVerticalLine(){
		y=1;
	}

	@Override
	public void printName() {
		System.out.println("this line represents a column");
	}
}
