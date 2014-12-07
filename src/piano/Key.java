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
	private Sound sound;
	private int position;
	private PianoGUI gui;

	public Key(Sound sound, PianoLabel[] pianoLabels, int position, PianoGUI gui)
	{
		this.sound = sound;
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

	public void playSound()
	{

	}

	public void play(Color color)
	{
		// change background for as as long as note plays
		// sound.play();
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
			// System.out.println("Key: key pos = " + position + " color = " + gui.getClientColor());
			oos.writeObject(new PianoPacket(position, gui.getClientColor()));
			oos.flush();
			// oos.close();
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
