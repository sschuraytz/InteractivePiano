package piano;

import java.awt.*;
import java.util.ArrayList;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;

public class PianoGUI extends JFrame {
    //instrument program numbers found at https://www.midi.org/specifications-old/item/gm-level-1-sound-set
    private static final int PIANO_PROGRAM  = 0;
    private static final int  XYLOPHONE_PROGRAM = 14;
    private static final int GUITAIR_PROGRAM = 25;
    private static final int TRUMPET_PROGRAM = 57;
    private static final int FLUTE_PROGRAM = 74;
    private static final int DRUM_PROGRAM = 115;

    public PianoGUI(MidiChannel midiChannel, Recorder recorder) {
        setTitle("MY PIANO");
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.BLACK);
        root.setOpaque(true);

        root.add(new RecorderPanel(recorder), BorderLayout.NORTH);
        root.add(new Keyboard(midiChannel, recorder), BorderLayout.CENTER);
        root.add(instrumentOptions(), BorderLayout.SOUTH);
        setContentPane(root);

	}

    private JPanel instrumentOptions ()
    {
        JPanel instrumentsButtons = new JPanel();
        instrumentsButtons.setLayout(new FlowLayout());
        JButton piano = new JButton("Piano");
        instrumentsButtons.add(piano);
        JButton xylophone = new JButton("Xylophone");
        instrumentsButtons.add(xylophone);
        JButton guitar = new JButton("Guitar");
        instrumentsButtons.add(guitar);
        JButton trumpet = new JButton("Trumpet");
        instrumentsButtons.add(trumpet);
        JButton flute = new JButton("Flute");
        instrumentsButtons.add(flute);
        JButton drum = new JButton("Drum");
        instrumentsButtons.add(drum);
        piano.addActionListener(e->{
            setInstrument(PIANO_PROGRAM);
        });
        xylophone.addActionListener(e->{
            setInstrument(XYLOPHONE_PROGRAM);
        });
        guitar.addActionListener(e->{
            setInstrument(GUITAIR_PROGRAM);
        });
        trumpet.addActionListener(e->{
            setInstrument(TRUMPET_PROGRAM);
        });
        flute.addActionListener(e->{
            setInstrument(FLUTE_PROGRAM);
        });
        drum.addActionListener(e->{
            setInstrument(DRUM_PROGRAM);
        });
        return instrumentsButtons;
    }

    public void setInstrument(int instrument){
        channel.programChange(instrument);
    }
}
