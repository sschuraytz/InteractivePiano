package piano.instruments;

import piano.main.MainFrameInterface;
import javax.sound.midi.MidiChannel;
import javax.swing.*;
import java.awt.*;

public class InstrumentsPanel extends JPanel {

    public InstrumentsPanel(MidiChannel midiChannel) {
        setLayout(new FlowLayout());
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.INSTRUMENTS_PANEL_HEIGHT);
        setBackground(Color.BLACK);
        for (InstrumentButton instrumentButton: new InstrumentButtonsArray()) {
            instrumentButton.addActionListener(e -> midiChannel.programChange(instrumentButton.getInstrument()));
            add(instrumentButton);
        }
    }
}
