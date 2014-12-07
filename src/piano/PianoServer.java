package piano;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PianoServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(3773);
		BlockingQueue<PianoPacket> pianoPackets = new LinkedBlockingQueue<PianoPacket>();
		List<ObjectOutputStream> outStreams = new ArrayList<ObjectOutputStream>();
		ServerBroadcast broadcaster = new ServerBroadcast(pianoPackets, outStreams);
		broadcaster.start();

		while (true)
		{
			Socket socket = serverSocket.accept();
			// send the client its color
			Random gen = new Random();
			Color color = new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
			// You must instantiate one ObjectInputStream and one ObjectOutputStream per Socket.
			// http://stackoverflow.com/questions/15986511/java-io-streamcorruptedexception-over-sockets
			ObjectOutputStream out;
			ObjectInputStream in;
			try
			{
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(color); // give the client its clientColor
				// System.out.println("server color: " + color);
				out.flush();
				in = new ObjectInputStream(socket.getInputStream());

				// add to list of outStreams
				outStreams.add(out);

				// create a receiver for the socket
				ServerReceiver reciever = new ServerReceiver(in, pianoPackets);
				reciever.start();

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}
}
