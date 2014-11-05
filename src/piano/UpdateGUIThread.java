package piano;

public class UpdateGUIThread extends Thread {

	private PianoGUI gui;

	public UpdateGUIThread(PianoGUI gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		while (true) {// get the Key and Color being sent from the server and
						// call ClickAndUpdateKey

		}
	}

}
