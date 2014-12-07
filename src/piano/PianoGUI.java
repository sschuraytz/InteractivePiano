package piano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PianoGUI extends JFrame
{
	private Color clientColor = Color.BLUE; // sent by server - randomly generate int converted to color
	private ObjectOutputStream out;

	public PianoGUI()
	{
		this.setTitle("MY PIANO");
		this.setSize(500, 300); // final size will be determined by pack()
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// instantiate top row pianoLabels
		JPanel top = new JPanel();
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(top, BorderLayout.NORTH);

		PianoLabel[] topRowLabels = new PianoLabel[13];
		for (int i = 0; i < 13; i++)
		{
			if (i == 5)
			{
				topRowLabels[i] = new PianoLabel(new Dimension(8, 200), Color.BLACK); // skinny dude
			}
			else
			{
				topRowLabels[i] = new PianoLabel(new Dimension(70, 200), i % 2 == 0 ? Color.WHITE : Color.BLACK);
				topRowLabels[i].addMouseListener(new KeyListener());
			}
			top.add(topRowLabels[i]);
		}

		// instantiate bottom row labels
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(bottom, BorderLayout.CENTER);

		PianoLabel[] bottomRowLabels = new PianoLabel[13];
		for (int i = 0; i < 13; i++)
		{
			if (i % 2 == 0) // whiteKey
			{
				bottomRowLabels[i] = new PianoLabel(new Dimension(130, 150), Color.WHITE);
				bottomRowLabels[i].addMouseListener(new KeyListener());
			}
			else
			{
				bottomRowLabels[i] = new PianoLabel(new Dimension(8, 150), Color.BLACK);
			}
			bottom.add(bottomRowLabels[i]);
		}

		// link labels to keys
		ArrayList<Key> keys = new ArrayList<>();
		Key k;
		for (int i = 0; i < 13; i++)
		{
			if (i % 2 == 0) // white key
			{
				k = new Key(null, new PianoLabel[] { topRowLabels[i], bottomRowLabels[i] }, keys.size(), this);
				topRowLabels[i].setKey(k);
				bottomRowLabels[i].setKey(k);
				keys.add(k);
			}
			else
			// black key
			{
				if (i != 5) // not skinny dude
				{
					k = new Key(null, new PianoLabel[] { topRowLabels[i] }, keys.size(), this);
					topRowLabels[i].setKey(k);
					keys.add(k);
				}
			}
		}

		pack();

		// outStream remains null until a connection is made
		ClientReceiver conn = new ClientReceiver(this, keys);
		conn.start();

	}

	public Color getClientColor()
	{
		return clientColor;
	}

	public void setClientColor(Color clientColor)
	{
		this.clientColor = clientColor;
	}

	public static void main(String[] args)
	{
		PianoGUI gui = new PianoGUI();
		gui.setVisible(true);
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream)
	{
		out = objectOutputStream;
	}

	public ObjectOutputStream getObjectOutputStream()
	{
		return out;
	}
}
