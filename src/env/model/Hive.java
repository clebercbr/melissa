package model;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import model.enumeration.HoneySupply;
import model.exception.InsufficientHoneyException;
import model.exception.InsufficientPolenException;

public class Hive {
	private static Hive instance;
	private static int maxHoney = 1000;
	private static int maxPolen = 1000;

	private int temperature;

	private List<Bee> queens = new ArrayList<>();
	private List<Bee> feeders = new ArrayList<>();
	private List<Bee> sentinels = new ArrayList<>();
	private List<Bee> workers = new ArrayList<>();
	private List<Larva> larvas = new ArrayList<>();

	private int honey;
	private int polen;
	
	private Rectangle hive;

	private Hive() {
	}
	
	public HoneySupply getStatus() {
		if (honey < maxHoney * 0.05)
			return HoneySupply.EMPTY;
		else if (honey < maxHoney * 0.2)
			return HoneySupply.LOW;
		else if (honey < maxHoney * 0.6)
			return HoneySupply.MEDIUM;
		else if (honey < maxHoney * 0.9)
			return HoneySupply.HIGH;
		else
			return HoneySupply.FULL;
	}
	
	public static Hive getInstance() {
		if (instance == null) 
			instance = new Hive();
		
		return instance;
	}
	
	public Rectangle getHiveRect() {
		return hive;
	}
	
	public void setHiveRect(Rectangle rect) {
		hive = rect;
	}
	
	public void setHiveRect(int x, int y, int width, int heigth) {
		hive = new Rectangle(x, y, width, heigth);
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public List<Bee> getFeeders() {
		return feeders;
	}
	
	public List<Larva> getLarvas() {
		return larvas;
	}
	
	public int getHoney() {
		return honey;
	}
	
	public void setHoney(int honey) {
		this.honey = honey;
	}	
	
	public int getPolen() {
		return polen;
	}
	
	public void setPolen(int polen) {
		this.polen = polen;
	}
	
	public List<Bee> getQueens() {
		return queens;
	}
	public List<Bee> getSentinels() {
		return sentinels;
	}
	
	public List<Bee> getWorkers() {
		return workers;
	}

	public void createLarva() {
		larvas.add(new Larva(0));
	}

	public Bee createFeeder(String id) {
		Bee bee = new Bee(id);
		feeders.add(bee);
		return bee;
	}

	public Bee createSentinel(String id) {
		Bee bee = new Bee(id);
		sentinels.add(bee);
		return bee;
	}

	public Bee createWorker(String id) {
		Bee bee = new Bee(id);
		workers.add(bee);
		return bee;
	}

	public Bee createQueen(String id) {
		Bee bee = new Bee(id);
		queens.add(bee);
		return bee;
	}

	synchronized public void addHoney(int ammount) {
		honey = honey + ammount;
	}
	
	synchronized public void subHoney(int ammount) throws InsufficientHoneyException {
		if (honey >= ammount) {
			honey = honey - ammount;
		} else
			throw new InsufficientHoneyException("The honey is gone.");
	}	

	synchronized public void addPolen(int ammount) {
		polen = polen + ammount;
	}
	
	synchronized public void subPolen(int ammount) throws InsufficientPolenException {
		if (polen >= ammount) {
			polen = polen - ammount;
		} else
			throw new InsufficientPolenException("The polen is gone.");
	}	
}
