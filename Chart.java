package fr.sanchez.robot.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import fr.sanchez.robot.utils.Coord;

/**
 * Chart.java Parts of Robot
 * @author SANCHEZ Joris
 * @copyrights all rights reserved
 * @date 24 mai 2018
 * @version 1.0 preAlpha
 * 
 */
public class Chart extends JComponent
{
	private static final long serialVersionUID = 1L;

	private ArrayList<Coord> points;
	private boolean linked = true;
	private Color lineColor;
	private Color pointColor;
	private Color backLineColor;
	private Color backFillColor;
	
	private boolean limit = false;
	private int maxlimit = 0;
	
	public Chart()
	{
		points = new ArrayList<>();
	}
	
	public void setLimit(boolean limit)
	{
		this.limit = limit;
	}

	public void setMaxlimit(int maxlimit)
	{
		this.maxlimit = maxlimit;
	}

	public void isLinked(boolean linked)
	{
		this.linked = linked;
	}
	
	public void addPoints(int x, int y)
	{
		points.add(new Coord(x, y));
		if(limit)
		{
			if(points.size() > maxlimit)
				points.remove(0);
		}
	}
	
	public void setLineColor(Color lineColor)
	{
		this.lineColor = lineColor;
	}

	public void setPointColor(Color pointColor)
	{
		this.pointColor = pointColor;
	}

	public void setBackLineColor(Color backLineColor)
	{
		this.backLineColor = backLineColor;
	}

	public void setBackFillColor(Color backFillColor)
	{
		this.backFillColor = backFillColor;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int height = this.getHeight()-20;
		int diffHeight = height/6;
		
		System.out.println(diffHeight);
		
		int min = 0;
		int max = 0;
		int echelle = 0;
		boolean isFirst = true;;
		if(!points.isEmpty())
		{
			for(Coord c : points)
			{
				if(isFirst)
				{
					min = c.getY();
					max = c.getY();
					isFirst = false;
				}
				else
				{
					if(c.getY() > max)
						max = c.getY();
					if(c.getY() < min)
						min = c.getY();
				}
			}
			System.out.println(max + " "+ min + " = "+(max-min));
			echelle = (max - min)/2;
			echelle = (echelle == 0)? 1 : echelle;
			System.out.println(echelle);
		}
		
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(backLineColor);
		g.drawLine(10, this.getHeight() - (10+diffHeight*5), this.getWidth() - 10, this.getHeight() - (10+diffHeight*5));
		g.drawString(""+echelle * 5, 10, this.getHeight() - (10+diffHeight*5 + 5));
		g.drawLine(10, this.getHeight() - (10+diffHeight*4), this.getWidth() - 10, this.getHeight() - (10+diffHeight*4));
		g.drawString(""+echelle * 4, 10, this.getHeight() - (10+diffHeight*4 + 5));
		g.drawLine(10, this.getHeight() - (10+diffHeight*3), this.getWidth() - 10, this.getHeight() - (10+diffHeight*3));
		g.drawString(""+echelle * 3, 10, this.getHeight() - (10+diffHeight*3 + 5));
		g.drawLine(10, this.getHeight() - (10+diffHeight*2), this.getWidth() - 10, this.getHeight() - (10+diffHeight*2));
		g.drawString(""+echelle * 2, 10, this.getHeight() - (10+diffHeight*2 + 5));
		g.drawLine(10, this.getHeight() - (10+diffHeight*1), this.getWidth() - 10, this.getHeight() - (10+diffHeight*1));
		g.drawString(""+echelle, 10, this.getHeight() - (10+diffHeight*1 + 5));
		g.drawLine(10, this.getHeight() - (10), this.getWidth() - 10, this.getHeight() - (10));
		g.drawString(""+0, 10, this.getHeight() - (10 + 5));
		
		int diffWidth = (this.getWidth()-26) / ((!points.isEmpty())?points.size():1);
		int i = 0;
		
		int[] xs = new int[points.size() + 2];
		int[] ys = new int[points.size() + 2];
		
		for(Coord c : points)
		{
			g.setColor(pointColor);
			int y = this.getHeight() - (10 + (((int)(c.getY()/echelle))* diffHeight) + ((diffHeight / echelle) * (c.getY()%echelle)));
			System.out.println("x:"+i*diffWidth+" y:"+y);
			g.fillOval(30 + i * diffWidth -4, y -4, 8, 8);
			c.setX1(30 + i * diffWidth);
			c.setY1(y);
			g.setColor(lineColor);
			xs[i + 1] = 30 + i * diffWidth;
			ys[i + 1] = y;
			if(linked && i != 0)
			{
				Coord last = points.get(i - 1);
				g.drawLine(last.getX1(), last.getY1(), c.getX1(), c.getY1());
			}
			i++;
		}
		
		xs[0] = points.get(0).getX1();
		ys[0] = this.getHeight() - (10);
		
		xs[points.size()+1] = points.get(i - 1).getX1();
		ys[points.size()+1] = this.getHeight() - (10);
		
		g.setColor(backFillColor);
		g.fillPolygon(xs, ys, points.size() + 2);
	}
}
