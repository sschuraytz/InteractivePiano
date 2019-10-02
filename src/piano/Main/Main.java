package piano.Main;

import piano.Intro.Intro;
import piano.Keyboard.KeyboardAudio.Channel;
import piano.Recorder.Recorder;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            MidiChannel midiChannel = new Channel().getChannel();
            PianoGUI frame = new PianoGUI(new Channel().getChannel(), new Recorder());
            frame.setVisible(true);
            new Intro().playIntro(midiChannel);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
