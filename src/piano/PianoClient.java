package piano;

import java.awt.Color;

public class PianoClient {
	private String name;
	private Color color;
	private PianoKeys pianoKeys;

	public PianoClient(String name, Color color) {
		this.name = name;
		this.color = color;
		pianoKeys = new PianoKeys();

	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public PianoKeys getPianoKeys() {
		return pianoKeys;
	}

}
