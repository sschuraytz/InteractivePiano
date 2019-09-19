package piano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class Key
{
	private PianoLabel pianoLabel;
	private int position;
	private PianoGUI gui;
	private JLayeredPane layeredPane;

	public Key(PianoLabel pianoLabel, int position, PianoGUI gui, JLayeredPane layeredPane)
	{
		this.pianoLabel = pianoLabel;
		this.position = position;
		this.gui = gui;
		this.layeredPane = layeredPane;
	}

	public void setColor(Color color)
	{
		pianoLabel.setBackground(color);
		pianoLabel.repaint();
		if (pianoLabel.getPreferredColor() == Color.BLACK) {
			layeredPane.moveToFront(pianoLabel);
		} else {
			layeredPane.moveToBack(pianoLabel);
		}
	}

	public void play(Color color)
	{
		// change background for as as long as note plays
		SoundThread s = new SoundThread(position + Notes.C, gui.getChannel()); // pass in pitch to play
		s.start();
		setColor(color);
		ActionListener action = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				resetColor();
			}
		};

		Timer t = new Timer(1000, action);
		t.start();
	}

	public void sendPacket()
	{
		ObjectOutputStream oos;
		try
		{
			oos = gui.getObjectOutputStream();
			oos.writeObject(new PianoPacket(position, gui.getClientColor()));
			oos.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void resetColor()
	{
		pianoLabel.setBackground(pianoLabel.getPreferredColor());
		pianoLabel.repaint();
		if (pianoLabel.getPreferredColor() == Color.BLACK) {
			layeredPane.moveToFront(pianoLabel);
		} else {
			layeredPane.moveToBack(pianoLabel);
		}
	}
}
