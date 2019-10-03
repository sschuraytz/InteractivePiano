package piano.recorder;

import piano.keyboard.keyboardui.PianoLabel;

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
