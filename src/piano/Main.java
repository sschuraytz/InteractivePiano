package piano;

import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            PianoGUI frame = new PianoGUI();
            frame.setVisible(true);
            frame.playIntro();
        } catch (MidiUnavailableException exc) {
            exc.printStackTrace();
        }

    }
}
