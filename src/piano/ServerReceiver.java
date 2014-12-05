package piano;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerReceiver extends Thread
{
	private Socket socket;
	private BlockingQueue<PianoPacket> packets;

	public ServerReceiver(Socket s, BlockingQueue<PianoPacket> pianoPackets)
	{
		socket = s;
		this.packets = pianoPackets;
	}

	public void run()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			while (true)
			{
				packets.add((PianoPacket)in.readObject());
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
