package piano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Key {
	private PianoLabel[] pianoLabels;
	private Sound sound;
	private Color clientColor;
	private int position;

	public Key(Sound sound, PianoLabel[] pianoLabels, Color clientColor, int position) {
		this.sound = sound;
		this.pianoLabels = pianoLabels;
		this.clientColor = clientColor;
		this.position = position;

	}

	public void setColor(Color color) {
		for (PianoLabel pl : pianoLabels) {
			pl.setBackground(color);
			pl.repaint();
		}
	}

	public void playSound() {
	}

	public void play() {
		//change background for as as long as note plays
		//sound.play();
		setColor(clientColor);
		//for (long i = 0; i < 9999999; i++) //simulate waiting for sound to finish
		//{
			//waste time
		//}
		
		ActionListener action = new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	resetColor();
		    }
		};

		Timer t = new Timer(1000, action);
		//t.setRepeats(false);
		t.start();
		
		//resetColor();
		
		
	}

	private void resetColor() {
		for (PianoLabel pl : pianoLabels) {
			pl.setBackground(pl.getPreferredColor());
			pl.repaint();
		}
		
	}

}
