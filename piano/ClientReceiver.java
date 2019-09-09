package piano;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientReceiver extends Thread
{
	private Socket socket;
	private PianoGUI gui;
	private ArrayList<Key> keys;

	public ClientReceiver(PianoGUI gui, ArrayList<Key> keys)
	{
		this.gui = gui;
		this.keys = keys;
	}

	@Override
	public void run()
	{
		super.run();
		try
		{
			// establish the connection
			// socket = new Socket("192.168.117.46", 3773);
			socket = new Socket("localhost", 3773);

			// can only have 1 instance of output stream, so sending it to gui (gui doesnt need the socket/in stream)
			gui.setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // should be the only in stream
			// 1st thing sent is clientColor
			Color c = (Color) in.readObject();
			gui.setClientColor(c);

			// now receives the PianoPackets
			PianoPacket packet;
			while (true)
			{
				// http://stackoverflow.com/questions/2393179/streamcorruptedexception-invalid-type-code-ac
				packet = (PianoPacket) in.readObject();
				keys.get(packet.getKeyPosition()).play(packet.getClientColor());
			}
		}
		catch (SocketException e)
		{
			// "restart" thread
			new ClientReceiver(gui, keys).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
