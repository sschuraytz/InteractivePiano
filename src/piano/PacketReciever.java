package piano;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class PacketReciever extends Thread
{
	private Socket socket;
	private BlockingQueue<PianoPacket> packets;

	public PacketReciever(Socket s, BlockingQueue<PianoPacket> pianoPackets)
	{
		socket = s;
		this.packets = pianoPackets;
	}

	public void run()
	{
		try
		{
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line; // need to change so will read in objects

			while ((line = reader.readLine()) != null)
			{
				// write to all clients
				packets.add(line);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
