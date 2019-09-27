package piano;


import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JRootPane;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionListener;

public class PlaybackFunctionality {

    private MidiChannel channel;
    private PianoGUI gui = new PianoGUI();

    public PlaybackFunctionality(MidiChannel channel) throws MidiUnavailableException {
        this.channel = channel;
    }

    public void playSong(double[] song) {
        try {
            for (double note : song) {
          /*      Thread t = new Thread( NewThread );
                t.Start();
            }
*/
                gui.getNotesKeyMap().get((int) note).play();
                gui.getNotesLabelMap().get((int) note).setBackground(Color.RED);
                gui.repaint();
                //SoundThread s = new SoundThread((int) note, channel);
                //gui.setLevel(gui.getNotesLabelMap().get((int) note));
                //gui.getNotesKeyHashMap().get(35).setBackground(Color.RED);
                //s.start();
                Thread.sleep(500);
            }
        }
       catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void teachSong(double[] song) {
        for (double note : song) {
//            gui.getNotesKeyMap().get((int) note).setColor(Color.PINK);
//            gui.getNotesLabelMap().get((int) note).setBackground(Color.RED);
            ActionListener action = e -> gui.getNotesLabelMap().get((int) note).setBackground(Color.RED);
            Timer t = new Timer(1000, action);
            t.start();
            //System.out.println(gui.getNotesKeyMap().get((int) note).toString());
            //System.out.println(gui.getNotesLabelMap().get((int) note).toString());

            //gui.getNotesKeyHashMap().get(note).setBorder(BorderFactory.createLineBorder(Color.GREEN));
           // System.out.println(gui.getNotesKeyHashMap().get(67));
            //System.out.println(gui.getNotesKeyHashMap().entrySet());

            //1: outline first note in song
                // gui.getNotesKeyHashMap().get(note).setBorder(BorderFactory.createLineBorder(Color.GREEN));
            //2: when that note is clicked, play (& fill background)
                // if gui.getNotesKeyHashMap()
            //3: then outline next note in song
                // aka keep looping
        }
    }

    

}