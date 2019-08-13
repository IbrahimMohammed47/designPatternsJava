package Structural;

public class Facade_HomeTheater {

	public static void main(String[] args) {
		
		HomeTheater ht = new HomeTheater(new DvdPlayer(), new Projector(), new Screen(), new Lights(), new PopcornPopper(), new Amplifier());
		ht.watchMovie("Rookie");
	}

}

class HomeTheater {
	DvdPlayer dvd;
	Projector pj;
	Screen sc;
	Lights lit;
	PopcornPopper pp;
	Amplifier amp;
	public HomeTheater (DvdPlayer dvd, Projector pj, Screen sc, Lights lit, PopcornPopper pp, Amplifier amp) {
		this.dvd = dvd;
		this.pj = pj;
		this.sc = sc;
		this.lit = lit;
		this.pp = pp;
		this.amp = amp;
	}
	
	public void watchMovie(String movie) {
		System.out.println("Get ready to watch " + movie);
		pp.on();
		pp.pop();
		lit.dim(10);
		sc.down();
		pj.on();
		pj.wideScreenMode();
		amp.on();
		amp.setDvd(dvd);
		amp.setSurroundSound();
		amp.setVol(5);
		dvd.on();
		dvd.play(movie);
	}
	
	public void endMovie(String movie) {
		System.out.println("Shutting down theater...");
		pp.off();
		lit.on();
		sc.up();
		pj.off();
		amp.off();
		dvd.stop();
		dvd.eject();
		dvd.on();
	}
}

class Amplifier {
	public void on() {}
	public void off() {}
	public void setDvd(DvdPlayer dvd) {}
	public void setVol(int vol) {}
	public void setSurroundSound() {}
}

class DvdPlayer {
	public void on() {}
	public void off() {}
	public void play(String movie) {}
	public void stop() {}
	public void eject() {}
}

class Screen {
	public void up() {}
	public void down() {}
}

class Lights {
	public void on() {}
	public void dim(int intensity) {}
}

class PopcornPopper {
	public void on() {}
	public void off() {}
	public void pop() {}
}

class Projector {
	public void on() {}
	public void off() {}
	public void wideScreenMode() {}
}