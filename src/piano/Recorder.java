package piano;

import java.util.ArrayList;

class Recorder
{
    private ArrayList<KeyPressedInfo> recordedKeysInfo = new ArrayList<>();
    private boolean isRecording;

    void append(Key keyPressed, long timePressed)
    {
        recordedKeysInfo.add(new KeyPressedInfo(keyPressed, timePressed));
    }

    void playBack()
    {
        long waitTime;
        for (int ix = 0; ix < recordedKeysInfo.size(); ix++)
        {
            if (ix > 0)
            {
                waitTime = recordedKeysInfo.get(ix).getTime() - recordedKeysInfo.get(ix - 1).getTime();
                try
                {
                    Thread.sleep(waitTime);
                }
                catch (Exception exc)
                {
                    System.out.println(exc.getMessage());
                }
            }
            recordedKeysInfo.get(ix).getKey().play();
        }
    }

    void setIsRecording(boolean isRecording)
    {
        this.isRecording = isRecording;
    }

    boolean getIsRecording()
    {
        return isRecording;
    }

    void clearRecordedNotes()
    {
        recordedKeysInfo.clear();
    }

    ArrayList<KeyPressedInfo> getRecordedKeysInfo()
    {
        return recordedKeysInfo;
    }
}
