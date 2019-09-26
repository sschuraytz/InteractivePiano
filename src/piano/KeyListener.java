package piano;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;

public class KeyListener implements MouseListener
{
	private final Recorder recorder;

	public KeyListener(Recorder recorder)
	{
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
		PianoLabel pl = (PianoLabel) arg0.getSource();

		Key currentKey = pl.getKey();
		currentKey.play();

		if (recorder.getIsRecording())
		{
			recorder.append(currentKey, System.currentTimeMillis());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{

	}
}
