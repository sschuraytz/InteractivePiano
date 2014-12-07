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
			socket = new Socket("localhost", 3773);

			// once connection is made, send it back to the gui
			// gui.setSocket(socket);

			// can only have 1 instance of output stream, so sending it to gui cuz gui doesnt need the socket/in stream
			gui.setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // should be the only in stream
			// 1st thing sent is clientColor
			Color c = (Color) in.readObject();
			System.out.println("client color: " + c);
			gui.setClientColor(c);

			// now receives the PianoPackets
			PianoPacket packet;
			while (true)
			{
				// http://stackoverflow.com/questions/2393179/streamcorruptedexception-invalid-type-code-ac
				packet = (PianoPacket) in.readObject();
				// System.out.println("ClientReceiver: key pos = " + packet.getKeyPosition() + " color = " + packet.getClientColor());
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
