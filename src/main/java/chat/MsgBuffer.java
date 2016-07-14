package chat;

import java.util.ArrayList;

import javafx.collections.ModifiableObservableListBase;

public class MsgBuffer extends ModifiableObservableListBase<String>{

	ArrayList<String> messages = new ArrayList<String>();
	
	@Override
	protected void doAdd(int arg0, String arg1) {
		messages.add(arg0,  arg1);
		
	}

	@Override
	protected String doRemove(int arg0) {
		return messages.remove(arg0);
	}

	@Override
	protected String doSet(int arg0, String arg1) {
		return messages.set(arg0,  arg1);
	}

	@Override
	public String get(int arg0) {
		return messages.get(arg0);
	}

	@Override
	public int size() {
		return messages.size();
	}

}
