package piano;

import javax.swing.*;
import java.awt.*;

public class PianoGUI2 extends JFrame {

    public PianoGUI2() {
        setTitle("MY PIANO");
        setSize(KeyStats.FRAME_WIDTH, KeyStats.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLayeredPane root = new JLayeredPane();
        root.setBackground(Color.BLACK); // TODO find out why this isn't working
        // TODO if octaves == 7, set up full piano board with extra keys on both sides
        addWhiteKeys(root);
        addBlackKeys(root);

        setContentPane(root);
    }

    private void addWhiteKeys(JLayeredPane root) {
        int placement = KeyStats.SPACE_BETWEEN_WHITE_KEYS;
        for (int i = 0; i < KeyStats.NUM_WHITE_KEYS; i++) {
            PianoLabel key = new PianoLabel(Color.WHITE);
            key.addMouseListener(new KeyListener());
            key.setLocation(placement, 0);
            key.setSize(key.getDimension());
            root.add(key, 0);
            placement += KeyStats.WHITE_WIDTH + KeyStats.SPACE_BETWEEN_WHITE_KEYS;
        }
    }

    private void addBlackKeys(JLayeredPane root) {
        int placement = KeyStats.FIRST_BLACK;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int blackKey = 0; blackKey < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackKey++) {
                PianoLabel key = new PianoLabel(Color.BLACK);
                key.addMouseListener(new KeyListener());
                key.setLocation(placement, 0);
                key.setSize(key.getDimension());
                root.add(key, 1);

                if (blackKey == 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
                } else if (blackKey != KeyStats.NUM_BLACK_KEYS_IN_OCTAVE - 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.SPACE_BETWEEN_BLACK_KEYS;
                }
            }
            placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
        }
    }
}
