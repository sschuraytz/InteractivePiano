package piano;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyListener implements MouseListener
{
	PianoGUI gui;

	public KeyListener(PianoGUI pianoGUI) {
		this.gui = pianoGUI;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		PianoLabel pianoLabel = (PianoLabel) arg0.getSource();
		pianoLabel.setColorToOnClickColor();
		pianoLabel.getKey().play();
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		PianoLabel pianoLabel = (PianoLabel) arg0.getSource();
		pianoLabel.setColorToDefaultColor();
		gui.setLevel(pianoLabel);
	}
}
