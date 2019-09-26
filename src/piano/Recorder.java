package piano;

import java.util.ArrayList;

public class Recorder
{
    private ArrayList<Key> recordedKeys = new ArrayList<>();
    private ArrayList<Long> timesPressed = new ArrayList<>();
    private boolean isRecording;

    public void append(Key keyPressed, long timePressed)
    {
        recordedKeys.add(keyPressed);
        timesPressed.add(timePressed);
    }

    public void playBack() throws InterruptedException
    {
        for (int ix = 0; ix < recordedKeys.size(); ix++)
        {
            if (ix > 0)
            {
                Thread.sleep((timesPressed.get(ix) - timesPressed.get(ix - 1))); //how many seconds
            }
            recordedKeys.get(ix).play();
        }
    }

    public void setIsRecording(boolean isRecording)
    {
        this.isRecording = isRecording;
    }

    public boolean getIsRecording()
    {
        return isRecording;
    }

    public void clearRecordedNotes()
    {
        recordedKeys.clear();
    }

    public ArrayList<Key> getRecordedKeys()
    {
        return recordedKeys;
    }
}
