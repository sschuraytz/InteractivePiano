package piano;

import java.util.ArrayList;
import java.util.List;

public class PianoKeys {// list of Keys that is created once Piano is divided up
	private List<Key> keys;

	public PianoKeys() {
		this.keys = new ArrayList<Key>();
	}

	public void addKey(Key key) {
		keys.add(key);
	}

	public List<Key> getPianoKeys() {
		return keys;
	}
}
