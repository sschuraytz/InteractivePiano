package piano;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientReceiver extends Thread {

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
			gui.setSocket(socket);

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			//1st thing sent is clientColor
			gui.setClientColor((Color)in.readObject());
			
			//now receives the PianoPackets
			PianoPacket packet;
			//potential solution to EOFException
			//1.Before sending the file, send its length, via writeLong().
			//2. At the receiver, when receiving the file, first get its length, via readLong(), then read exactly that many bytes from the ObjectInputStream and copy them to the file.
			//look into protocol buffers
			while(true)
			{
				//http://stackoverflow.com/questions/2393179/streamcorruptedexception-invalid-type-code-ac
				packet = (PianoPacket)in.readObject();
				keys.get(packet.getKeyPosition()).play(packet.getClientColor());
			}
		}
		catch ( SocketException e ) 
		{
			//"restart" thread
			new ClientReceiver(gui, keys).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

