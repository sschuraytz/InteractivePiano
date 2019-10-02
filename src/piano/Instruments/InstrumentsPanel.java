package piano.Instruments;

import piano.Main.MainFrameInterface;
import javax.sound.midi.MidiChannel;
import javax.swing.*;
import java.awt.*;

public class InstrumentsPanel extends JPanel {

    //instrument program numbers found at https://www.midi.org/specifications-old/item/gm-level-1-sound-set
    private final int PIANO_PROGRAM  = 0;
    private final int  XYLOPHONE_PROGRAM = 14;
    private final int GUITAR_PROGRAM = 25;
    private final int TRUMPET_PROGRAM = 57;
    private final int FLUTE_PROGRAM = 74;
    private final int DRUM_PROGRAM = 115;
    private final MidiChannel midiChannel;

    public InstrumentsPanel(MidiChannel midiChannel) {
        this.midiChannel = midiChannel;
        setLayout(new FlowLayout());
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.INSTRUMENTS_PANEL_HEIGHT);
        setBackground(Color.BLACK);
        JButton piano = new JButton("Piano");
        add(piano);
        JButton xylophone = new JButton("Xylophone");
        add(xylophone);
        JButton guitar = new JButton("Guitar");
        add(guitar);
        JButton trumpet = new JButton("Trumpet");
        add(trumpet);
        JButton flute = new JButton("Flute");
        add(flute);
        JButton drum = new JButton("Drum");
        add(drum);
        piano.addActionListener(e->{
            setInstrument(PIANO_PROGRAM);
        });
        xylophone.addActionListener(e->{
            setInstrument(XYLOPHONE_PROGRAM);
        });
        guitar.addActionListener(e->{
            setInstrument(GUITAR_PROGRAM);
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
    }

    public void setInstrument(int instrument){
        midiChannel.programChange(instrument);
    }
}
