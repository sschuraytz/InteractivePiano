package piano;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class PianoLabel extends JLabel
{
	private Key key;
	private Color preferredColor;// color of key when NOT clicked
	private Dimension dimension;

	public PianoLabel(Dimension dimension, Color preferredColor)
	{
		this.preferredColor = preferredColor;
		this.dimension = dimension;
		setPreferredSize(dimension);
		setOpaque(true);
		setBackground(preferredColor);
	}

	public PianoLabel(Color color) {
		setLayout(null);
		if (color == Color.WHITE || color == Color.BLACK) {
			createKey(color);
		}
	}

	private void createKey(Color color) {
		Dimension dimension;
		if (color == Color.WHITE) {
			dimension = new Dimension(KeyStats.WHITE_WIDTH, KeyStats.FRAME_HEIGHT);
		} else {
			dimension = new Dimension(KeyStats.BLACK_WIDTH, KeyStats.BLACK_HEIGHT);
		}
		this.preferredColor = color;
		this.dimension = dimension;
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

	public Dimension getDimension() {
		return dimension;
	}
}
