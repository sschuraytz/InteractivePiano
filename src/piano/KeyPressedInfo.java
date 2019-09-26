package piano;

class KeyPressedInfo
{
    private Key keyPressed;
    private long timeWhenPressed;

    KeyPressedInfo(Key keyPressed, long timeWhenPressed)
    {
        this.keyPressed = keyPressed;
        this.timeWhenPressed = timeWhenPressed;
    }

    Key getKey()
    {
        return keyPressed;
    }

    long getTime()
    {
        return timeWhenPressed;
    }
}
