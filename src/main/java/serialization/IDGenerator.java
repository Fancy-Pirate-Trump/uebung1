package serialization;

public class IDGenerator {
	private int lastID;
	private int maxID;

	public IDGenerator(int firstID, int maxID){
		lastID = firstID;
		this.maxID = maxID;
	}

	public int nextID() throws IDOverflowException{
		if (lastID+1 > maxID)
			throw(new IDOverflowException());

		return lastID++;
	}
}


