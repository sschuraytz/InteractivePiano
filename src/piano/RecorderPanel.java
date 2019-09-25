package piano;

import javax.swing.*;
import java.awt.*;

public class RecorderPanel extends JPanel
{
    JButton BtnStartRecording;
    JButton BtnStopRecording;
    JButton BtnPlayBack;
    Recorder recorder;

    public RecorderPanel(Recorder recorder)
    {
        this.recorder = recorder;
        BtnStartRecording = createStartRecordingBtn();
        BtnStopRecording = createStopRecordingBtn();
        BtnPlayBack = createPlaybackBtn();
        add(BtnStartRecording);
        add(BtnStopRecording);
        add(BtnPlayBack);
    }


    private JButton createStartRecordingBtn()
    {
        JButton start = new JButton("Record");
        start.setBackground(new Color(46, 177, 8));
        start.addActionListener(e -> {
            if (!recorder.getIsRecording())
            {
                recorder.setIsRecording(true);
                recorder.clearRecordedNotes();
            }
        });
        return start;
    }

    private JButton createStopRecordingBtn()
    {
        JButton stop = new JButton("Stop");
        stop.setBackground(new Color(196, 15, 25));
        stop.addActionListener(e -> {
            if (recorder.getIsRecording())
            {
                recorder.setIsRecording(false);
            }
        });
        return stop;
    }

    private JButton createPlaybackBtn()
    {
        JButton playBack = new JButton("Play Back");
        playBack.setBackground(new Color(108, 63, 177));
        playBack.addActionListener(e -> {
            if (!recorder.getRecordedKeys().isEmpty())
            {
                try
                {
                    recorder.playBack();
                }
                catch (InterruptedException exc)
                {
                    exc.printStackTrace();
                }
            }
        });
        return playBack;
    }


}
