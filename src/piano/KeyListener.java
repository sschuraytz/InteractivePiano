package piano;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyListener implements MouseListener
{
	private PianoGUI gui;

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
		 // TODO stop level from changing on color change
		pianoLabel.play();
		gui.setLevel(pianoLabel);
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		PianoLabel pianoLabel = (PianoLabel) arg0.getSource();
		// TODO make color stay on clicked color as long as key is still playing, even if key was already released
		pianoLabel.setColorToDefaultColor();
		gui.setLevel(pianoLabel);
	}
}
