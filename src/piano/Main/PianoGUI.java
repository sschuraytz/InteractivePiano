package piano.Main;

import piano.Instruments.InstrumentsPanel;
import piano.Keyboard.KeyboardUI.Keyboard;
import piano.Recorder.Recorder;
import piano.Recorder.RecorderPanel;
import java.awt.*;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {

    public PianoGUI(MidiChannel midiChannel, Recorder recorder) {
        setTitle("MY PIANO");
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.BLACK);
        root.setOpaque(true);

        root.add(new RecorderPanel(recorder), BorderLayout.NORTH);
        root.add(new Keyboard(midiChannel, recorder), BorderLayout.CENTER);
        root.add(new InstrumentsPanel(midiChannel), BorderLayout.SOUTH);
        setContentPane(root);

	}
}
