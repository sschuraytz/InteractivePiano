package piano;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PianoGUI extends JFrame implements ActionListener {
	private PianoClient client;
	private JPanel pianoPanel;

	public PianoGUI(PianoClient client) {
		this.client = client;
		this.pianoPanel = new JPanel();
		this.pianoPanel.setLayout(new GridLayout(4, 4));
		addKeysToPanel(client.getColor());
		this.add(pianoPanel);

		this.setTitle("MY PIANO");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		UpdateGUIThread thread = new UpdateGUIThread(this);
		thread.start();

	}

	public void addKeysToPanel(Color color) {// loop through PianoKeys, loop
												// through each Key and set each
												// JButton's background to the
												// color and add the JButton to
												// the panel
	}

	public void ClickAndUpdateKey(Key key, Color color) {// click on Key, set
															// it's background
															// to approprite
															// color

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {// when each JButton is
													// clicked, send it's
													// corresponding Key to the
													// sever and the clients
													// Color

	}
}
