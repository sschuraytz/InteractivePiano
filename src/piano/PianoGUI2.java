package piano;

import javax.swing.*;
import java.awt.*;

public class PianoGUI2 extends JFrame {

    public PianoGUI2() {
        setTitle("MY PIANO");
        setSize(KeyStats.FRAME_WIDTH, KeyStats.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setBackground(Color.BLACK);
        addWhiteKeys(root);

        setContentPane(root);
    }

    private void addWhiteKeys(JPanel root) {
        for (int i = 0; i < KeyStats.NUM_WHITE_KEYS; i++) {
            PianoLabel key = new PianoLabel(Color.WHITE);
            key.addMouseListener(new KeyListener());
            root.add(key);
        }
    }
}
