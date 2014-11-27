package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestFrame extends JFrame
{
	private JComponent label;
	private JButton button;
	private JPanel top;
	private JPanel bottom;

	public TestFrame()
	{
		setSize(1400, 600);
		setTitle("Test");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setLayout(new FlowLayout());
		// getContentPane().setBackground(Color.DARK_GRAY);

		top = new JPanel();
		top.setBackground(Color.DARK_GRAY);
		add(top);

		bottom = new JPanel();
		bottom.setBackground(Color.DARK_GRAY);
		add(bottom, BorderLayout.SOUTH);

		// add labels to top
		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(5, 300));
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		 top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		top.add(label);

		label = new JLabel();
		label.setPreferredSize(new Dimension(100, 300));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		top.add(label);

		// add labels to bottom
		for (int i = 0; i < 10; i++)
		{
			label = new JLabel();
			label.setPreferredSize(new Dimension(120, 250));
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
			bottom.add(label);
		}

		// pack so it all fits nice
		pack();

		//
		//
		//
		label.addMouseListener(new MickeyMouse());

		button = new JButton();
		button.setBackground(Color.WHITE);
		button.addActionListener(new KeyListener(button, Color.WHITE));
		// add(button);

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
