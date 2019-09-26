package piano;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyListener implements MouseListener
{
	private PianoGUI gui;
    private final Recorder recorder;

	public KeyListener(PianoGUI pianoGUI, Recorder recorder) {
		this.gui = pianoGUI;
        this.recorder = recorder;
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
        pianoLabel.play();
        gui.setLevel(pianoLabel);

		if (recorder.getIsRecording())
		{
			recorder.append(pianoLabel, System.currentTimeMillis());
		}
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
