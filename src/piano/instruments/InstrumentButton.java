package piano.instruments;

import javax.swing.*;

public class InstrumentButton extends JButton {

    private int instrument;

    public InstrumentButton(int instrument) {
        this.instrument = instrument;
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
    }

    int getInstrument() {
        return instrument;
    }
}
