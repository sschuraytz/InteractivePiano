package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AvivaTestGUI extends JFrame implements MouseListener {
	private JPanel top;
	private JPanel bottom;
	JLabel white1;
	JLabel black1;
	private JLabel white2;
	private JLabel black2;
	private JLabel black3;
	private JLabel white3;
	private JLabel white4;
	private JLabel black4;
	private JLabel white5;
	private JLabel black5;
	private JLabel white6;
	private JLabel black6;

	public AvivaTestGUI() {
		this.setSize(460, 600);
		this.setTitle("Test");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		white1 = new JLabel();
		white1.setPreferredSize(new Dimension(80, getHeight() / 2));
		white1.addMouseListener(this);
		top.add(white1);

		black1 = new JLabel();
		black1.setBackground(Color.BLACK);
		black1.setPreferredSize(new Dimension(80, getHeight() / 2));
		black1.setBorder(new LineBorder(Color.BLACK));
		black1.setOpaque(true);
		black1.addMouseListener(this);
		top.add(black1);

		white2 = new JLabel();
		white2.setPreferredSize(new Dimension(80, getHeight() / 2));
		white2.addMouseListener(this);
		top.add(white2);

		black2 = new JLabel();
		black2.setPreferredSize(new Dimension(80, getHeight() / 2));
		black2.setBackground(Color.BLACK);
		black2.setOpaque(true);
		black2.addMouseListener(this);
		top.add(black2);

		white3 = new JLabel();
		white3.setPreferredSize(new Dimension(80, getHeight() / 2));
		white3.addMouseListener(this);
		top.add(white3);

		black3 = new JLabel();
		black3.setPreferredSize(new Dimension(5, getHeight() / 2));
		black3.setBackground(Color.BLACK);
		black3.setOpaque(true);
		black3.addMouseListener(this);
		top.add(black3);

		white4 = new JLabel();
		white4.setPreferredSize(new Dimension(97, getHeight() / 2));
		white4.addMouseListener(this);
		bottom.add(white4);

		black4 = new JLabel();
		black4.setPreferredSize(new Dimension(10, getHeight() / 2));
		black4.setBackground(Color.BLACK);
		black4.setOpaque(true);
		black4.addMouseListener(this);
		bottom.add(black4);

		white5 = new JLabel();
		white5.setPreferredSize(new Dimension(150, getHeight() / 2));
		white5.addMouseListener(this);
		bottom.add(white5);

		black5 = new JLabel();
		black5.setPreferredSize(new Dimension(10, getHeight() / 2));
		black5.setBackground(Color.BLACK);
		black5.setOpaque(true);
		black5.addMouseListener(this);
		bottom.add(black5);

		white6 = new JLabel();
		white6.setPreferredSize(new Dimension(132, getHeight() / 2));
		white6.addMouseListener(this);
		bottom.add(white6);

		black6 = new JLabel();
		black6.setPreferredSize(new Dimension(5, getHeight() / 2));
		black6.setBackground(Color.BLACK);
		black6.setOpaque(true);
		black6.addMouseListener(this);
		bottom.add(black6);

		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		AvivaTestGUI gui = new AvivaTestGUI();
		gui.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == white1)
			;
		white1.setText("Hi");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
