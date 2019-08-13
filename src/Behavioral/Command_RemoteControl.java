package Behavioral;
import com.sun.tools.javac.util.Pair;

public class Command_RemoteControl {	
	// Client
	public static void main(String [] args) {						
		
		// Invoker
		RemoteControl remote = new RemoteControl();					
		
		// Receivers
		Pulp pulp = new Pulp(); 									
		Sprinkler sprinkler = new Sprinkler();
		TV tv = new TV();
		GarageDoor garageDoor = new GarageDoor();
		
		// Commands initialized
		Command lightOnCommand = new LightOnCommand(pulp);
		Command lightOffCommand = new LightOffCommand(pulp);
		Command tvOnCommand = new TVOnCommand(tv); 
		Command tvOffCommand = new TVOffCommand(tv);
		Command garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor); 
		Command garageDoorOffCommand = new GarageDoorCloseCommand(garageDoor);
		Command sprinklerOnCommand = new SprinklerOnCommand(sprinkler); 
		Command sprinklerOffCommand = new SprinklerOffCommand(sprinkler);
		
		Command [] partyOn = {lightOnCommand,tvOnCommand};
		Command [] partyOff = {lightOffCommand,tvOffCommand};
		Command partyOnCommand = new MacroCommand(partyOn);
		Command partyOffCommand = new MacroCommand(partyOff);
		
		// Set commands to the invoker: Configuring remote control
		remote.setCommand(lightOnCommand, lightOffCommand);
		remote.setCommand(tvOnCommand, tvOffCommand);
		remote.setCommand(garageDoorOpenCommand, garageDoorOffCommand);
		remote.setCommand(sprinklerOnCommand, sprinklerOffCommand);
		remote.setCommand(partyOnCommand, partyOffCommand);
	
		// Client asks invoker to execute the commands
		remote.pressOnButton(0);
		remote.pressOffButton(2);
		remote.undo();
		remote.pressOnButton(3);
		remote.undo();
		remote.pressOnButton(4);
		remote.undo();
	}
}
//////////////////////// RemoteControl (Invoker)

class RemoteControl {
	private Pair<Command,Command> [] buttons;
	private static int currentSlot;
	private Command lastCommandUsed;
	public RemoteControl() {
		buttons = new Pair [5];
		currentSlot = 0;
		lastCommandUsed = new NoCommand();
		for (int i = 0; i < 5; i++) {
			buttons[i] = new Pair<Command, Command>(new NoCommand(), new NoCommand());
		}
	}
	public void setCommand(Command onCommand, Command offCommand) {
		buttons[currentSlot] = new Pair <Command,Command>(onCommand,offCommand);
		currentSlot++;
	}
	
	public void pressOnButton(int slot) {
		buttons[slot].fst.execute();
		lastCommandUsed = buttons[slot].fst;
	}
	
	public void pressOffButton(int slot) {
		buttons[slot].snd.execute();
		lastCommandUsed = buttons[slot].snd;
	}
	
	public void undo() {
		lastCommandUsed.undo();
	}
}
//////////////////////// Vendor classes (Receivers)


class TV {
	
	public void on() {
		System.out.println("TV is on");
	}
	public void off() {
		System.out.println("TV is off");
	}
}

class Pulp {
	
	public void turnOn() {
		System.out.println("pulp is on");
	}
	public void turnOff() {
		System.out.println("pulp is off");
	}
}

class GarageDoor {

	public void up() {
		System.out.println("door is open");
	}
	public void down() {
		System.out.println("door is closed");
	}
	public void stop() {
		
	}
	
}

class Sprinkler {

	public void on() {
		System.out.println("sprinkler is open");
	}
	public void off() {
		System.out.println("sprinkler is off");
	}

}

//////////////////////// Commands new'ed by Client / Client will compose them with Invoker

interface Command {
	public void execute();
	public void undo();
}

class TVOnCommand implements Command {
	TV tv;
	
	public TVOnCommand(TV tv) {
		this.tv = tv;
	}
	
	public void execute() {
		tv.on();
	}	
	
	public void undo() {
		tv.off();
	}	
}

class TVOffCommand implements Command {
	TV tv;
	
	public TVOffCommand(TV tv) {
		this.tv = tv;
	}	
	
	public void execute() {
		tv.off();
	}
	public void undo() {
		tv.on();
	}
}

class LightOnCommand implements Command {
	Pulp pulp;
	
	public LightOnCommand(Pulp pulp) {
		this.pulp = pulp;
	}
	
	public void execute() {
		pulp.turnOn();	
	}
	
	public void undo() {
		pulp.turnOff();
	}
}

class LightOffCommand implements Command {
	Pulp pulp;
	
	public LightOffCommand(Pulp pulp) {
		this.pulp = pulp;
	}
	
	public void execute() {
		pulp.turnOff();	
	}
	
	public void undo() {
		pulp.turnOn();
	}
}

class GarageDoorOpenCommand implements Command {
	GarageDoor garageDoor ;
	
	public GarageDoorOpenCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}
	
	public void execute() {
		garageDoor.up();
	}
	
	public void undo() {
		garageDoor.down();
	}
}

class GarageDoorCloseCommand implements Command {
	GarageDoor garageDoor ;
	
	public GarageDoorCloseCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}
	
	public void execute() {
		garageDoor.down();
	}
	
	public void undo() {
		garageDoor.up();
	}
}

class SprinklerOnCommand implements Command {
	Sprinkler sp;
	
	public SprinklerOnCommand(Sprinkler sp) {
		this.sp = sp;
	}
	
	public void execute() {
		sp.on();
	}
	
	public void undo() {
		sp.off();
	}
}

class SprinklerOffCommand implements Command {
	Sprinkler sp;
	
	public SprinklerOffCommand(Sprinkler sp) {
		this.sp = sp;
	}
	
	public void execute() {
		sp.off();
	}
	
	public void undo() {
		sp.on();
	}
}

class NoCommand implements Command {

	public void execute() {
		System.out.println("unset button");
	}
	
	public void undo() {
		System.out.println("unset button");
	}
	
}

class MacroCommand implements Command {
	Command[] commands;
	public MacroCommand(Command[] commands) {
		this.commands = commands;
	}
	
	public void execute() {
		for (Command command : commands) {
			command.execute();
		}
	}
	
	public void undo() {
		for (Command command : commands) {
			command.undo();
		}
	}
	
}