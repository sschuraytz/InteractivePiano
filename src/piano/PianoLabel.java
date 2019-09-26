package piano;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class PianoLabel extends JLabel
{
	private Key key;
	private Color defaultColor; // color of key when NOT clicked
	private Color colorOnClick; // color of key when clicked
	private Dimension dimension;


	public PianoLabel(Color defaultColor, Color colorOnClick, Key key) {
		if (defaultColor == Color.WHITE || defaultColor == Color.BLACK) {
			createPianoLabel(defaultColor, colorOnClick, key);
		}
	}

	private void createPianoLabel(Color defaultColor, Color onClickColor, Key key) {
		if (defaultColor == Color.WHITE) {
			dimension = new Dimension(KeyStats.WHITE_WIDTH, KeyStats.FRAME_HEIGHT);
		} else {
			dimension = new Dimension(KeyStats.BLACK_WIDTH, KeyStats.BLACK_HEIGHT);
		}
		this.defaultColor = defaultColor;
		this.colorOnClick = onClickColor;
		this.key = key;
		setPreferredSize(dimension);
		setOpaque(true);
		setBackground(defaultColor);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setColorToDefaultColor() {
		setBackground(defaultColor);
	}

	private void setColorToOnClickColor() {
		setBackground(colorOnClick);
	}

	public void play() {
		setColorToOnClickColor();
		key.play();
	}
}
