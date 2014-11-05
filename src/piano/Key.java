package piano;

import java.util.List;

import javax.swing.JButton;

public class Key {// a section in Piano 2d array
	private List<JButton> buttons;

	public Key(List<JButton> buttons) {
		this.buttons = buttons;
	}

	public List<JButton> getButtonsList() {
		return buttons;
	}
}
