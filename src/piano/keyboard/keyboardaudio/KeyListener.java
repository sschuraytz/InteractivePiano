package piano.keyboard.keyboardaudio;

import piano.keyboard.keyboardui.PianoLabel;
import piano.recorder.Recorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyListener implements MouseListener
{
    private final Recorder recorder;

	public KeyListener(Recorder recorder) {
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
	}
}
