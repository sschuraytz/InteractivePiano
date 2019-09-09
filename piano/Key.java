package piano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.Timer;

public class Key
{
	private PianoLabel[] pianoLabels;
	private int position;
	private PianoGUI gui;

	public Key(PianoLabel[] pianoLabels, int position, PianoGUI gui)
	{
		this.pianoLabels = pianoLabels;
		this.position = position;
		this.gui = gui;
	}

	public void setColor(Color color)
	{
		for (PianoLabel pl : pianoLabels)
		{
			pl.setBackground(color);
			pl.repaint();
		}
	}

	public void play(Color color)
	{
		// change background for as as long as note plays
		//SoundThread s = new SoundThread(position + Notes.C, gui.getChannel()); // pass in pitch to play
		SoundThread s = new SoundThread(position + Notes.lowC, gui.getChannel()); // pass in pitch to play
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
		for (PianoLabel pl : pianoLabels)
		{
			pl.setBackground(pl.getPreferredColor());
			pl.repaint();
		}
	}

	public int getPosition()
	{
		return position;
	}

}
