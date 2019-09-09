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
	private Color clientColor; // sent by server - randomly generated int
	private ObjectOutputStream out;
	private MidiChannel channel;

	public PianoGUI() throws MidiUnavailableException
	{
		this.setTitle("MY PIANO");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(top, BorderLayout.NORTH);
		PianoLabel[] topRowLabels = new PianoLabel[KeyStats.NUM_KEYS];
		initializeTopLabels(top, topRowLabels);

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(bottom, BorderLayout.CENTER);
		PianoLabel[] bottomRowLabels = new PianoLabel[KeyStats.NUM_KEYS];	//this was hard-coded; changed it.
		initializeBottomLabels(bottom, bottomRowLabels);

		ArrayList<Key> keys = new ArrayList<>();
		linkLabelsToKeys(topRowLabels, bottomRowLabels, keys);

		setResizable(false);
		pack();
		this.setLocationRelativeTo(null);

		// setting up sound
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		channel = synth.getChannels()[SoundSettings.CHANNEL];

		// outStream remains null until a connection is made
		ClientReceiver conn = new ClientReceiver(this, keys);
		conn.start();
	}

	private void linkLabelsToKeys(PianoLabel[] topRowLabels, PianoLabel[] bottomRowLabels, ArrayList<Key> keys)
	{
		Key k;
		for (int i = 0; i < KeyStats.NUM_KEYS; i++)
		{
			if ((i & 1) == 0) // white key
			{
				k = new Key(new PianoLabel[] { topRowLabels[i], bottomRowLabels[i] }, keys.size(), this);
				topRowLabels[i].setKey(k);
				bottomRowLabels[i].setKey(k);
				keys.add(k);
			}
			else
			// black key
			{
				//alternating btwn +8 & +6 - is there a way to automate the calculation of these numbers?
				if (i != 5 && i != 13 && i !=19 && i != 27 && i != 33 && i != 41) // not skinny dude
				{
					k = new Key(new PianoLabel[] { topRowLabels[i] }, keys.size(), this);
					topRowLabels[i].setKey(k);
					keys.add(k);
				}
			}
		}
	}

	private void initializeBottomLabels(JPanel bottom, PianoLabel[] bottomRowLabels)
	{
		for (int i = 0; i < KeyStats.NUM_KEYS; i++)
		{
			if ((i & 1) == 0) // whiteKey
			{
				//+14 - is there a way to automate this?
				if (i == 4 || i == 18 || i == 32) {
					bottomRowLabels[i] = new PianoLabel(new Dimension(KeyStats.BOTTOM_FAT_WIDTH_2, KeyStats.BOTTOM_HEIGHT), Color.WHITE);
				}
				else {
					bottomRowLabels[i] = new PianoLabel(new Dimension(
							i == 8 || i == 10 || i == 22 || i == 24 || i == 36 || i == 38 ? KeyStats.BOTTOM_FAT_WIDTH : KeyStats.BOTTOM_WHITE_WIDTH,
							KeyStats.BOTTOM_HEIGHT),
							Color.WHITE);
				}
				bottomRowLabels[i].addMouseListener(new KeyListener());
			}
			else
			{
				bottomRowLabels[i] = new PianoLabel(new Dimension(KeyStats.BOTTOM_SKINNY_WIDTH, KeyStats.BOTTOM_HEIGHT), Color.BLACK);
			}
			bottom.add(bottomRowLabels[i]);
		}
	}

	private void initializeTopLabels(JPanel top, PianoLabel[] topRowLabels)
	{
		for (int i = 0; i < KeyStats.NUM_KEYS; i++)
		{
			if ((i & 1) == 0) // white
			{
				topRowLabels[i] = new PianoLabel(new Dimension(KeyStats.TOP_WHITE_WIDTH, KeyStats.TOP_HEIGHT), Color.WHITE);
			}
			else if (i == 5 || i == 13 || i == 19 || i == 27 || i == 33 || i == 41) // skinny dude
			{
				topRowLabels[i] = new PianoLabel(new Dimension(KeyStats.TOP_SKINNY_WIDTH, KeyStats.TOP_HEIGHT), Color.BLACK);
			}
			else
			// black
			{
				topRowLabels[i] = new PianoLabel(new Dimension(KeyStats.TOP_BLACK_WIDTH, KeyStats.TOP_HEIGHT), Color.BLACK);
			}

			topRowLabels[i].addMouseListener(new KeyListener());
			top.add(topRowLabels[i]);
		}
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
		try
		{
			int[] notes = { Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.A, Notes.B };
			for (int i = 0; i < notes.length; ++i)
			{
				channel.noteOn(notes[i], SoundSettings.VOLUME);
				Thread.sleep(100);
				channel.noteOff(notes[i]);
			}
			// Play a C major chord.
			channel.noteOn(Notes.C, SoundSettings.VOLUME);
			channel.noteOn(Notes.E, SoundSettings.VOLUME);
			channel.noteOn(Notes.G, SoundSettings.VOLUME);
			Thread.sleep(3000);
			channel.allNotesOff();
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

	public MidiChannel getChannel()
	{
		return channel;
	}
}
