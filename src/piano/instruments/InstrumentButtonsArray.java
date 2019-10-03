package piano.instruments;

import java.util.ArrayList;

public class InstrumentButtonsArray extends ArrayList<InstrumentButton> {

    public InstrumentButtonsArray() {
        add(new InstrumentButton(InstrumentsInterface.PIANO));
        add(new InstrumentButton(InstrumentsInterface.XYLOPHONE));
        add(new InstrumentButton(InstrumentsInterface.GUITAR));
        add(new InstrumentButton(InstrumentsInterface.TRUMPET));
        add(new InstrumentButton(InstrumentsInterface.FLUTE));
        add(new InstrumentButton(InstrumentsInterface.DRUM));
    }
}
