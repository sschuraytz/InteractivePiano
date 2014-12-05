package piano;

import java.awt.Color;
import java.io.Serializable;

public class PianoPacket implements Serializable
{
	// all fields that must be sent to server will be bundled into a PianoPacket to group all related info about 1 key being pressed
	private int keyPosition;
	private Color clientColor;
	//sound when implemented
	
	public PianoPacket(int keyPosition, Color clientColor){
		keyPosition=keyPosition;
		clientColor=clientColor;
	}

	public int getKeyPosition() {
		return keyPosition;
	}

	public Color getClientColor() {
		return clientColor;
	}
	
	
}
