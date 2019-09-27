package piano;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;

public class PianoGUI extends JFrame
{
    private MidiChannel channel;


    private ArrayList<Key> keys;
    static final int INSTRUCTION_PANEL_HEIGHT = 100;
    private int startingNote = Notes.C;
    private int startingNote2 = Notes.C;
    private JLayeredPane root;
    private HashMap<Integer, PianoLabel> notesLabelMap = new HashMap<>();

    private HashMap<Integer, Key> notesKeyMap = new HashMap<>();

    public PianoGUI() throws MidiUnavailableException
    {
        this.setTitle("MY PIANO");
        setSize(KeyStats.FRAME_WIDTH, KeyStats.FRAME_HEIGHT + INSTRUCTION_PANEL_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        keys = new ArrayList<>();

        root = new JLayeredPane();
        root.setBackground(Color.BLACK);
        root.setOpaque(true);
        // TODO if octaves == 7, set up full piano board with extra keys on both sides
        PianoLabel[] whiteLabels = addWhitePianoLabels(root);
        PianoLabel[][] blackLabels = addBlackPianoLabels(root);
        linkKeysToLabel(whiteLabels, blackLabels, root);
        ControlPanel controlPanel = new ControlPanel();
        root.add(controlPanel, 2);
        setContentPane(root);

		// setting up sound
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		channel = synth.getChannels()[SoundSettings.CHANNEL];
	}

    private PianoLabel[] addWhitePianoLabels(JLayeredPane root) {
        PianoLabel[] whiteLabels = new PianoLabel[KeyStats.NUM_WHITE_KEYS];
        int placement = KeyStats.SPACE_BETWEEN_WHITE_KEYS;
        for (int i = 0; i < KeyStats.NUM_WHITE_KEYS; i++) {
            PianoLabel pianoLabel = new PianoLabel(Color.WHITE);
            pianoLabel.setLocation(placement, INSTRUCTION_PANEL_HEIGHT);

            linkLabelToNote(startingNote, pianoLabel);
            setPianoLabelSizeAndListener(pianoLabel);

            root.add(pianoLabel, 0);
            whiteLabels[i] = pianoLabel;
            placement += KeyStats.WHITE_WIDTH + KeyStats.SPACE_BETWEEN_WHITE_KEYS;
        }
        return whiteLabels;
    }

    private PianoLabel[][] addBlackPianoLabels(JLayeredPane root) {
        PianoLabel[][] blackLabels = new PianoLabel[KeyStats.OCTAVES][KeyStats.NUM_BLACK_KEYS_IN_OCTAVE];
        int placement = KeyStats.FIRST_BLACK;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int blackKey = 0; blackKey < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackKey++) {
                PianoLabel pianoLabel = new PianoLabel(Color.BLACK);
                pianoLabel.setLocation(placement, INSTRUCTION_PANEL_HEIGHT);
                setPianoLabelSizeAndListener(pianoLabel);
                root.add(pianoLabel, 1);
                blackLabels[octave][blackKey] = pianoLabel;

                if (blackKey == 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
                } else if (blackKey != KeyStats.NUM_BLACK_KEYS_IN_OCTAVE - 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.SPACE_BETWEEN_BLACK_KEYS;
                }
            }
            placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
        }
        return blackLabels;
    }

    private void setPianoLabelSizeAndListener(PianoLabel pianoLabel) {
        pianoLabel.setSize(pianoLabel.getDimension());
        pianoLabel.addMouseListener(new KeyListener());
    }

    private void linkKeysToLabel(PianoLabel[] whiteLabels, PianoLabel[][] blackLabels, JLayeredPane layeredPane) {
        int whiteIndex = 0;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int blackIndex = 0; blackIndex < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackIndex++) {
                keyToLabel(whiteLabels[whiteIndex++], layeredPane);
                if (blackIndex == 2) {
                    keyToLabel(whiteLabels[whiteIndex++], layeredPane);

                }
                keyToLabel(blackLabels[octave][blackIndex], layeredPane);
            }
            keyToLabel(whiteLabels[whiteIndex++], layeredPane);
        }
    }

    private void keyToLabel(PianoLabel pianoLabel, JLayeredPane layeredPane) {
        Key key = new Key(pianoLabel, keys.size(), this, layeredPane);
        pianoLabel.setKey(key);
        keys.add(key);
        notesKeyMap.put(startingNote2, key);
        startingNote2++;
    }

	public void playIntro()
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


	public MidiChannel getChannel()
	{
		return channel;
	}


    public HashMap<Integer, PianoLabel> getNotesLabelMap() {
        return notesLabelMap;
    }

    public HashMap<Integer, Key> getNotesKeyMap() {
        return notesKeyMap;
    }

    public void linkLabelToNote(int note, PianoLabel pianoLabel) {
        notesLabelMap.put(note, pianoLabel);
        startingNote++;
    }

    public void setLevel(PianoLabel pianoLabel) {
        root.moveToFront(pianoLabel);
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

}
