package piano;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Key
{
	private PianoLabel pianoLabel;
	private static final Color[] colors =
			{new Color(204, 0, 0), new Color(255, 65, 0),new Color(255, 170, 0),new Color(255, 255, 0),
					new Color(102, 255, 51), new Color(0, 153, 51),new Color(0, 204, 153),new Color(0, 153, 204),
					new Color(0, 0, 255), new Color(153, 51, 255),new Color(153, 51, 153),new Color(204, 102, 153)};
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
		if (pianoLabel.getPreferredColor() == Color.BLACK) {
			layeredPane.moveToFront(pianoLabel);
		} else {
			layeredPane.moveToBack(pianoLabel);
		}
	}

	public void play()
	{
		// change background for as as long as note plays
		SoundThread s = new SoundThread(position + Notes.startingC, gui.getChannel()); // pass in pitch to play
		s.start();
		setColor(colors[position % colors.length]);
		ActionListener action = e -> resetColor();

		Timer t = new Timer(1000, action);
		t.start();
	}

	private void resetColor()
	{
		pianoLabel.setBackground(pianoLabel.getPreferredColor());
		if (pianoLabel.getPreferredColor() == Color.BLACK) {
			layeredPane.moveToFront(pianoLabel);
		} else {
			layeredPane.moveToBack(pianoLabel);
		}
	}
}
