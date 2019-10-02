package piano.Instruments;

import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class InstrumentButton extends JButton {

    public InstrumentButton(int instrument, MidiChannel midiChannel) {
        String text;
        switch (instrument) {
            case InstrumentsInterface.PIANO:
                text = "Piano";
                break;
            case InstrumentsInterface.XYLOPHONE:
                text = "Xylophone";
                break;
            case InstrumentsInterface.GUITAR:
                text = "Guitar";
                break;
            case InstrumentsInterface.TRUMPET:
                text = "Trumpet";
                break;
            case InstrumentsInterface.FLUTE:
                text = "Flute";
                break;
            case InstrumentsInterface.DRUM:
                text = "Drum";
                break;
            default:
                text = "";
                break;
        }

        setText(text);
        addActionListener(e -> midiChannel.programChange(instrument));
    }
}
