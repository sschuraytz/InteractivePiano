package piano;

import javax.swing.JLabel;

public class PianoKey extends JLabel
{
	private String note; // send to server so you know which key to color
	//private Sound tone; // if all notes of same type sound the same, dont need to send. if each piano has its own pitch, must send
}
