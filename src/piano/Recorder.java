package piano;

import java.util.ArrayList;

class Recorder
{
    private ArrayList<KeyPressedInfo> recordedKeysInfo = new ArrayList<>();
    private boolean isRecording;

    void append(PianoLabel labelPressed, long timePressed)
    {
        recordedKeysInfo.add(new KeyPressedInfo(labelPressed, timePressed));
    }

    void playBack()
    {
        long waitTime;
        for (int ix = 0; ix < recordedKeysInfo.size(); ix++)
        {
            PianoLabel labelPressed = recordedKeysInfo.get(ix).getLabelPressed();
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
            labelPressed.getKey().play();
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
