package piano;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class PianoLabel extends JLabel
{
	private Key key;
	private Color preferredColor;// color of key when NOT clicked

	public PianoLabel(Dimension dimension, Color preferredColor)
	{
		this.preferredColor = preferredColor;
		setPreferredSize(dimension);
		setOpaque(true);
		setBackground(preferredColor);
	}

	public Key getKey()
	{
		return key;
	}

	public void setKey(Key k)
	{
		this.key = k;
	}

	public Color getPreferredColor()
	{
		return preferredColor;
	}

}
