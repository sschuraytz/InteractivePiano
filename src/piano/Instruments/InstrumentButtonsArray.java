package piano.Instruments;

import javax.sound.midi.MidiChannel;
import java.util.ArrayList;

public class InstrumentButtonsArray extends ArrayList<InstrumentButton> {

    public InstrumentButtonsArray(MidiChannel midiChannel) {
        add(new InstrumentButton(InstrumentsInterface.PIANO, midiChannel));
        add(new InstrumentButton(InstrumentsInterface.XYLOPHONE, midiChannel));
        add(new InstrumentButton(InstrumentsInterface.GUITAR, midiChannel));
        add(new InstrumentButton(InstrumentsInterface.TRUMPET, midiChannel));
        add(new InstrumentButton(InstrumentsInterface.FLUTE, midiChannel));
        add(new InstrumentButton(InstrumentsInterface.DRUM, midiChannel));
    }
}
