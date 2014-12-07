package piano;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyListener implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e)
	{
		PianoLabel pl = (PianoLabel) e.getSource();
		pl.getKey().sendPacket();

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
