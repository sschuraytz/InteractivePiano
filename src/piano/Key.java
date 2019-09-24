package piano;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Key
{
	private int pitch;
	private PianoGUI gui;

	public Key(int position, PianoGUI gui)
	{
		pitch = position + Notes.startingC;
		this.gui = gui;
	}

	public void play()
	{
		// change background for as as long as note plays
		SoundThread s = new SoundThread(pitch, gui.getChannel()); // pass in pitch to play
		s.start();
		ActionListener action = e -> {};

		Timer t = new Timer(1000, action);
		t.start();
	}
}
