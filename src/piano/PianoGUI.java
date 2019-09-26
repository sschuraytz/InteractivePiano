package piano;

import java.awt.Color;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {
    private Colors colors;
    private JLayeredPane root;
    private MidiChannel midiChannel;

    public PianoGUI(MidiChannel midiChannel) {
        this.midiChannel = midiChannel;
        this.setTitle("MY PIANO");
        setSize(KeyStats.FRAME_WIDTH, KeyStats.FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        colors = new Colors();
        root = new JLayeredPane();

        root.setBackground(Color.BLACK);
        root.setOpaque(true);

        // TODO if octaves == 7, set up full piano board with extra keys on both sides
        addWhitePianoLabels();
        addBlackPianoLabels();
        setContentPane(root);
	}

    private void addWhitePianoLabels() {
        int placement = KeyStats.SPACE_BETWEEN_WHITE_KEYS;

        int index = 0;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int whiteKey = 0; whiteKey < KeyStats.NUM_WHITE_KEYS_IN_OCTAVE; whiteKey++) {
                addPianoLabel(Color.WHITE, index, placement);

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
        int index = 1;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int blackKey = 0; blackKey < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackKey++) {
                addPianoLabel(Color.BLACK, index, placement);

                if (blackKey == 1 || blackKey == KeyStats.NUM_BLACK_KEYS_IN_OCTAVE - 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
                    index +=3;
                } else {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.SPACE_BETWEEN_BLACK_KEYS;
                    index += 2;
                }
            }
        }
    }

    private void addPianoLabel(Color color, int index, int placement) {
        PianoLabel pianoLabel = new PianoLabel(color, colors.getColor(index), new Key(index, midiChannel));
        pianoLabel.setLocation(placement, 0);
        pianoLabel.setSize(pianoLabel.getDimension());
        pianoLabel.addMouseListener(new KeyListener(this));

        int level = 0;
        if (color == Color.BLACK) {
            level = 1;
        }
        root.add(pianoLabel, level);
    }

    public void setLevel(PianoLabel pianoLabel) {
        if (pianoLabel.getDefaultColor() == Color.BLACK) {
            root.moveToFront(pianoLabel);
        } else {
            root.moveToBack(pianoLabel);
        }
    }
}
