package application;

public class IDGenerator {
	private int lastID = 0;
	private int maxID = 9999;

	public IDGenerator(int firstID, int maxID){
		lastID = firstID;
		this.maxID = maxID;
	}
	public IDGenerator(int maxID){
		this(0, maxID);
	}
	public IDGenerator(){

	}

	public int nextID() throws IDOverflowException{
		if (lastID+1 > maxID)
			throw(new IDOverflowException());

		return lastID++;
	}
}


