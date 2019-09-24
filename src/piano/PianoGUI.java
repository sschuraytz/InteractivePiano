package piano;

import java.awt.Color;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;

public class PianoGUI extends JFrame
{
    private MidiChannel channel;
    private PianoLabel[] pianoLabels;
    private Colors colors;
    private JLayeredPane root;

    public PianoGUI() throws MidiUnavailableException
    {
        this.setTitle("MY PIANO");
        setSize(KeyStats.FRAME_WIDTH, KeyStats.FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pianoLabels = new PianoLabel[KeyStats.NUM_KEYS];
        colors = new Colors();
        root = new JLayeredPane();

        root.setBackground(Color.BLACK);
        root.setOpaque(true);
        // TODO if octaves == 7, set up full piano board with extra keys on both sides
        addWhitePianoLabels();
        addBlackPianoLabels();
        setContentPane(root);

		// setting up sound
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		channel = synth.getChannels()[SoundSettings.CHANNEL];
	}

    private void addWhitePianoLabels() {
        int placement = KeyStats.SPACE_BETWEEN_WHITE_KEYS;

        int index = 0;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int whiteKey = 0; whiteKey < KeyStats.NUM_WHITE_KEYS_IN_OCTAVE; whiteKey++) {
                PianoLabel pianoLabel = new PianoLabel(Color.WHITE, colors.getColor(index));
                pianoLabel.setLocation(placement, 0);
                setPianoLabelSizeAndListener(pianoLabel);
                pianoLabels[index] = pianoLabel;
                keyToLabel(pianoLabel, index);
                root.add(pianoLabel, 0);


                placement += KeyStats.WHITE_WIDTH + KeyStats.SPACE_BETWEEN_WHITE_KEYS;

                if (whiteKey == 2 || whiteKey == KeyStats.NUM_WHITE_KEYS_IN_OCTAVE - 1) {
                    index++;
                } else {
                    index += 2;
                }
            }
        }
    }

    private void addBlackPianoLabels() {
        int placement = KeyStats.FIRST_BLACK;
        int index = 0;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int blackKey = 0; blackKey < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackKey++) {
                PianoLabel pianoLabel = new PianoLabel(Color.BLACK, colors.getColor(index));
                pianoLabel.setLocation(placement, 0);
                setPianoLabelSizeAndListener(pianoLabel);
                pianoLabels[index] = pianoLabel;
                keyToLabel(pianoLabel, index);
                root.add(pianoLabel, 1);


                if (blackKey == 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
                    index +=3;
                } else if (blackKey != KeyStats.NUM_BLACK_KEYS_IN_OCTAVE - 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.SPACE_BETWEEN_BLACK_KEYS;
                    index += 2; // TODO check out if this else if is necessary, can we just make it one else and remove placement += below?
                }
            }
            placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
            index += 3;
        }
    }

    private void setPianoLabelSizeAndListener(PianoLabel pianoLabel) {
        pianoLabel.setSize(pianoLabel.getDimension());
        pianoLabel.addMouseListener(new KeyListener(this));
    }

    private void keyToLabel(PianoLabel pianoLabel, int index) {
        Key key = new Key(index, this);
        pianoLabel.setKey(key);
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

    public void setLevel(PianoLabel pianoLabel) {
        if (pianoLabel.getDefaultColor() == Color.BLACK) {
            root.moveToFront(pianoLabel);
        } else {
            root.moveToBack(pianoLabel);
        }
    }
}
