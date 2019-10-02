package piano.Recorder;

import piano.Keyboard.KeyboardUI.PianoLabel;

class KeyPressedInfo
{
    private PianoLabel labelPressed;
    private long timeWhenPressed;

    KeyPressedInfo(PianoLabel labelPressed, long timeWhenPressed)
    {
        this.labelPressed = labelPressed;
        this.timeWhenPressed = timeWhenPressed;
    }

    PianoLabel getLabelPressed()
    {
        return labelPressed;
    }

    long getTime()
    {
        return timeWhenPressed;
    }
}
