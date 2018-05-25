package fr.sanchez.robot.ui.window;

import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.sanchez.robot.ui.components.Chart;
import fr.sanchez.robot.ui.components.CubePanel;
import fr.sanchez.robot.ui.components.Separator;
import fr.sanchez.robot.ui.listener.MoveFrame;
import fr.sanchez.robot.utils.ColorUtils;
import fr.sanchez.robot.utils.Config;

/**
 * Loading.java Parts of Robot
 * @author SANCHEZ Joris
 * @copyrights all rights reserved
 * @date 7 mai 2018
 * @version 1.0 preAlpha
 * 
 */
public class Representation extends JFrame
{
	public static final long serialVersionUID = 1;
	
	private MoveFrame drag;
	
	private JPanel menu;
	private JPanel info;
	
	private JLabel x;
	private JLabel y;
	private JLabel z;
	private JLabel serverState;
	private JLabel clientAmount;
	
	private Separator s1;
	
	private CubePanel cube;
	
	private Chart chart;
	
	public Representation()
	{
		this.setTitle(Config.TITLE+" v"+Config.VERSION);
		this.setSize(400, 500);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setBackground(ColorUtils.TRANSPARENT);
		drag = new MoveFrame(this);
		initComponents();
		this.setVisible(true);
		run();
	}

	private void initComponents()
	{	
		menu = new JPanel();
		menu.setSize(400, 70);
		menu.setLocation(0, 0);
		menu.setBackground(ColorUtils.DARKGRAY);
		menu.setLayout(null);
		menu.addMouseListener(drag);
		menu.addMouseMotionListener(drag);
		this.getContentPane().add(menu);
		
		info = new JPanel();
		info.setSize(400, 420);
		info.setLocation(0, 80);
		info.setBackground(ColorUtils.DARKGRAY);
		info.setLayout(null);
		this.getContentPane().add(info);
		initInfo();
	}
	
	public void initInfo()
	{
//		x = new JLabel("X: 0.0000");
//		x.setSize(103, 20);
//		x.setLocation(30, 10);
//		x.setVerticalAlignment(JLabel.CENTER);
//		x.setHorizontalAlignment(JLabel.CENTER);
//		x.setForeground(ColorUtils.WHITE);
//		x.setFont(new Font("Arial", Font.BOLD, 15));
//		info.add(x);
//		
//		y = new JLabel("Y: 0.0000");
//		y.setSize(103, 20);
//		y.setLocation(143, 10);
//		y.setVerticalAlignment(JLabel.CENTER);
//		y.setHorizontalAlignment(JLabel.CENTER);
//		y.setForeground(ColorUtils.WHITE);
//		y.setFont(new Font("Arial", Font.BOLD, 15));
//		info.add(y);
//		
//		z = new JLabel("Z: 0.0000");
//		z.setSize(103, 20);
//		z.setLocation(256, 10);
//		z.setVerticalAlignment(JLabel.CENTER);
//		z.setHorizontalAlignment(JLabel.CENTER);
//		z.setForeground(ColorUtils.WHITE);
//		z.setFont(new Font("Arial", Font.BOLD, 15));
//		info.add(z);
//		
//		s1 = new Separator(20, 40, 360, ColorUtils.LIGHTGRAY);
//		info.add(s1);
//		
//		serverState = new JLabel("Server: ON");
//		serverState.setSize(100, 30);
//		serverState.setLocation(30, 55);
//		serverState.setOpaque(true);
//		serverState.setBackground(ColorUtils.GREEN);
//		serverState.setVerticalAlignment(JLabel.CENTER);
//		serverState.setHorizontalAlignment(JLabel.CENTER);
//		serverState.setForeground(ColorUtils.WHITE);
//		serverState.setFont(new Font("Arial", Font.BOLD, 14));
//		info.add(serverState);
//		
//		clientAmount = new JLabel("Client(s): 0");
//		clientAmount.setSize(100, 30);
//		clientAmount.setLocation(270, 55);
//		clientAmount.setOpaque(true);
//		clientAmount.setBackground(ColorUtils.GREEN);
//		clientAmount.setVerticalAlignment(JLabel.CENTER);
//		clientAmount.setHorizontalAlignment(JLabel.CENTER);
//		clientAmount.setForeground(ColorUtils.WHITE);
//		clientAmount.setFont(new Font("Arial", Font.BOLD, 14));
//		info.add(clientAmount);
		
		chart = new Chart();
		chart.setSize(380, 200);
		chart.setLocation(10, 10);
		chart.setBackground(ColorUtils.GRAY);
		chart.setBackLineColor(ColorUtils.lLIGHTGRAY);
		chart.setPointColor(ColorUtils.GREEN);
		chart.setLineColor(ColorUtils.GREEN);
		chart.setBackFillColor(ColorUtils.TRANS_GREEN);
		chart.setLimit(true);
		chart.setMaxlimit(6);
		chart.addPoints(0, 2);
		chart.addPoints(0, 15);
		chart.addPoints(0, 30);
		chart.addPoints(0, 25);
		chart.addPoints(0, 6);
		chart.addPoints(0, 18);
		info.add(chart);
		
		/*cube = new CubePanel(ColorUtils.LIGHTGRAY);
		cube.setSize(300, 300);
		cube.setBackground(ColorUtils.DARKGRAY);
		cube.setLocation(50, 90);
		cube.cube.ball.setLocation(15, 2, 14);
		info.add(cube);*/
	}
	
	public void run()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while(true)
				{
					chart.setVisible(false);
					int y = new Random().nextInt(30);
					System.out.println(y);
					chart.addPoints(0, y);
					chart.setVisible(true);
					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
