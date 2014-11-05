package piano;

import javax.swing.JButton;

public class Piano {// this is the internal 2d array that keeps track of keys
					// and what sections of the array each belongs to
	private JButton[][] pianoButtons;
	private final int ROWS = 4;
	private final int COLUMNS = 4;

	public Piano() {
		pianoButtons = new JButton[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				pianoButtons[i][j] = new JButton();
			}
		}
	}

	public JButton[][] getPianoButtons() {
		return pianoButtons;
	}

	public void divideIntoKeys() {// loop through 2d array and assign sections
									// to keys(hashmap keyname->list of
									// jbuttons?), then add keys to PianoKeys
	}
}
