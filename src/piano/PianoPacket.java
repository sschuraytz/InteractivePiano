package piano;

import java.awt.Color;

public class PianoPacket
{
	// all fields that must be sent to server will be bundled into a PianoPacket to group all related info about 1 key being pressed
	private String note;
	//private Sound tone;
	private Color color;
}
