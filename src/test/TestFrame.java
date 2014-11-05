package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestFrame extends JFrame
{
	private JLabel label;
	private JButton button;

	public TestFrame()
	{
		setSize(800, 600);
		setTitle("Test");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		label = new JLabel();
		label.addMouseListener(new MickeyMouse());

		button = new JButton();
		button.setBackground(Color.WHITE);
		button.addActionListener(new KeyListener(button, Color.WHITE));
		add(button);
	}

	public static void main(String[] args)
	{
		TestFrame test = new TestFrame();
		test.setVisible(true);
	}

	private class KeyListener implements ActionListener
	{
		private JButton me;
		private Color c;

		public KeyListener(JButton button, Color white)
		{
			me = button;
			c = white;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (c == Color.WHITE)
			{
				c = Color.blue;
				me.setBackground(c);

			}
			else
			{
				c = Color.WHITE;
				me.setBackground(c);
			}

		}
	}

	private class MickeyMouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			// use this one if it just checks if the label is clicked

		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}
	}
}
