package piano;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PianoGUI extends JFrame {

    private ArrayList<Key> keys;
    private Color clientColor; // sent by server - randomly generated int
    private ObjectOutputStream out;
    private MidiChannel channel;

    public PianoGUI() throws MidiUnavailableException {
        setTitle("MY PIANO");
        setSize(KeyStats.FRAME_WIDTH, KeyStats.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        keys = new ArrayList<>();

        JLayeredPane root = new JLayeredPane();
        root.setBackground(Color.BLACK);
        root.setOpaque(true);
        // TODO if octaves == 7, set up full piano board with extra keys on both sides
        addWhitePianoLabels(root);
        addBlackPianoLabels(root);

        setContentPane(root);

        // setting up sound
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        channel = synth.getChannels()[SoundSettings.CHANNEL];

        // outStream remains null until a connection is made
        ClientReceiver conn = new ClientReceiver(this, keys);
        conn.start();
    }

    private void addWhitePianoLabels(JLayeredPane root) {
        int placement = KeyStats.SPACE_BETWEEN_WHITE_KEYS;
        for (int i = 0; i < KeyStats.NUM_WHITE_KEYS; i++) {
            PianoLabel pianoLabel = new PianoLabel(Color.WHITE);
            pianoLabel.setLocation(placement, 0);
            setPianoLabelSizeandListener(pianoLabel);

            root.add(pianoLabel, 0);
            addKeyToList(pianoLabel, root);

            placement += KeyStats.WHITE_WIDTH + KeyStats.SPACE_BETWEEN_WHITE_KEYS;
        }
    }

    private void addBlackPianoLabels(JLayeredPane root) {
        int placement = KeyStats.FIRST_BLACK;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++) {
            for (int blackKey = 0; blackKey < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackKey++) {
                PianoLabel pianoLabel = new PianoLabel(Color.BLACK);
                pianoLabel.setLocation(placement, 0);
                setPianoLabelSizeandListener(pianoLabel);

                root.add(pianoLabel, 1);
                addKeyToList(pianoLabel, root);

                if (blackKey == 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
                } else if (blackKey != KeyStats.NUM_BLACK_KEYS_IN_OCTAVE - 1) {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.SPACE_BETWEEN_BLACK_KEYS;
                }
            }
            placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
        }
    }

    private void setPianoLabelSizeandListener(PianoLabel pianoLabel) {
        pianoLabel.setSize(pianoLabel.getDimension());
        pianoLabel.addMouseListener(new KeyListener());
    }

    private void addKeyToList(PianoLabel pianoLabel, JLayeredPane layeredPane) {
        Key key = new Key(pianoLabel, keys.size(), this, layeredPane);
        pianoLabel.setKey(key);
        keys.add(key);
    }

    public Color getClientColor()
    {
        return clientColor;
    }

    public void setClientColor(Color clientColor)
    {
        this.clientColor = clientColor;
    }

    public void playIntro()
    {
        try
        {
            int[] notes = { Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.A, Notes.B };
            for (int i = 0; i < notes.length; ++i)
            {
                channel.noteOn(notes[i], SoundSettings.VOLUME);
                Thread.sleep(100);
                channel.noteOff(notes[i]);
            }
            // Play a C major chord.
            channel.noteOn(Notes.C, SoundSettings.VOLUME);
            channel.noteOn(Notes.E, SoundSettings.VOLUME);
            channel.noteOn(Notes.G, SoundSettings.VOLUME);
            Thread.sleep(3000);
            channel.allNotesOff();
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream)
    {
        out = objectOutputStream;
    }

    public ObjectOutputStream getObjectOutputStream()
    {
        return out;
    }

    public MidiChannel getChannel()
    {
        return channel;
    }
}
