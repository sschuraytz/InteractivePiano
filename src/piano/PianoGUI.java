package piano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PianoGUI extends JFrame
{
	private Color clientColor = Color.BLUE; // sent by server - randomly
											// generate int converted to color
	private ObjectOutputStream out;
	private SoundSettings soundSettings;

	public PianoGUI() throws MidiUnavailableException
	{
		this.setTitle("MY PIANO");
		this.setSize(837, 751); // final size will be determined by pack()
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// instantiate top row pianoLabels
		JPanel top = new JPanel();
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(top, BorderLayout.NORTH);

		PianoLabel[] topRowLabels = new PianoLabel[13];
		for (int i = 0; i < 13; i++)
		{
			if (i == 5)
			{
				topRowLabels[i] = new PianoLabel(new Dimension(10, 448), Color.BLACK); // skinny
																						// dude
			}
			else
			{
				if (i % 2 == 0) // white
				{
					topRowLabels[i] = new PianoLabel(new Dimension(72, 448), Color.WHITE);
				}
				else
				{
					topRowLabels[i] = new PianoLabel(new Dimension(61, 448), Color.BLACK);
				}

				topRowLabels[i].addMouseListener(new KeyListener());
			}
			top.add(topRowLabels[i]);
		}

		// instantiate bottom row labels
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(bottom, BorderLayout.CENTER);

		PianoLabel[] bottomRowLabels = new PianoLabel[13];
		for (int i = 0; i < 13; i++)
		{
			if (i % 2 == 0) // whiteKey
			{
				bottomRowLabels[i] = new PianoLabel(new Dimension(i == 8 || i == 10 ? 114 : 105, 256), Color.WHITE);
				bottomRowLabels[i].addMouseListener(new KeyListener());
			}
			else
			{
				bottomRowLabels[i] = new PianoLabel(new Dimension(11, 256), Color.BLACK);
			}
			bottom.add(bottomRowLabels[i]);
		}

		// link labels to keys
		ArrayList<Key> keys = new ArrayList<>();
		Key k;
		for (int i = 0; i < 13; i++)
		{
			if (i % 2 == 0) // white key
			{
				k = new Key(new PianoLabel[] { topRowLabels[i], bottomRowLabels[i] }, keys.size(), this);
				topRowLabels[i].setKey(k);
				bottomRowLabels[i].setKey(k);
				keys.add(k);
			}
			else
			// black key
			{
				if (i != 5) // not skinny dude
				{
					k = new Key(new PianoLabel[] { topRowLabels[i] }, keys.size(), this);
					topRowLabels[i].setKey(k);
					keys.add(k);
				}
			}
		}

		setResizable(false);
		pack();

		// setting up sound
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		soundSettings = new SoundSettings(synth.getChannels());

		// outStream remains null until a connection is made
		ClientReceiver conn = new ClientReceiver(this, keys);
		conn.start();
	}

	public Color getClientColor()
	{
		return clientColor;
	}

	public void setClientColor(Color clientColor)
	{
		this.clientColor = clientColor;
	}

	public static void main(String[] args)
	{
		PianoGUI gui;
		try
		{
			gui = new PianoGUI();
			gui.setVisible(true);
			gui.playIntro();
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
		}
	}

	private void playIntro()
	{
		// play an intro
		try
		{
			MidiChannel[] channels = soundSettings.getChannels();
			int channel = soundSettings.getCHANNEL();
			int volume = soundSettings.getVOLUME();

			int[] notes = { 60, 62, 64, 65, 67, 69, 71, 72 }; // C, D, E, F, G, A, B, C
			for (int i = 0; i <= 7; ++i)
			{
				channels[channel].noteOn(notes[i], volume);
				Thread.sleep(100);
				channels[channel].noteOff(notes[i]);
			}
			// Play a C major chord.
			channels[channel].noteOn(60, volume); // C
			channels[channel].noteOn(64, volume); // E
			channels[channel].noteOn(67, volume); // G
			Thread.sleep(3000);
			channels[channel].allNotesOff();
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream)
	{
		out = objectOutputStream;
	}

	public ObjectOutputStream getObjectOutputStream()
	{
		return out;
	}

	public SoundSettings getSoundSettings()
	{
		return soundSettings;
	}
}
