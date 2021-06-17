package paint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class DrawingArea extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Color backColor;
	private Color brushColor;
	private int brushX, brushY, brushR;
	private ArrayList<Brush> brushList;
	
	DrawingArea(){
		
		this.addMouseListener(new MouseEventHandler());
		this.addMouseMotionListener(new MouseMotionHandler());
		
		backColor = Color.WHITE;
		brushColor =  Color.BLACK;
		this.setBackground(backColor);
		
		brushList = new ArrayList<Brush>();
		brushR = 5;		
	}
	
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		
		for(Brush brush: brushList) {
			brush.drawbrush(graphic);
		}
	}
	
	public void setBackColor(Color value) {
		this.backColor = value;
	}
	
	public void setbrushColor(Color value) {
		this.brushColor = value;
	}
	
	public void setBrushRadius(int value) {
		this.brushR = value;
	}
	
	public void resetArea() {
		backColor = Color.WHITE;
		brushColor = Color.BLACK;
		brushList.clear();
		repaint();
	}
	
	private class MouseEventHandler implements MouseListener{
		
		public void mousePressed(MouseEvent e) {
			brushX = e.getX() - (brushR/2);
			brushY = e.getY() - (brushR/2);
			
			brushList.add(new Brush(brushX, brushY, brushR, brushColor));
			repaint();
		}
		
		
		public void mouseClicked(MouseEvent e) {;}
		
		public void mouseReleased(MouseEvent e) {;}
		
		public void mouseEntered(MouseEvent e) {;}
		
		public void mouseExited(MouseEvent e) {;}

	}
	
	private class MouseMotionHandler implements MouseMotionListener{
		
		public void mouseDragged(MouseEvent e) {
			brushX = e.getX() - (brushR/2);
			brushY = e.getY() - (brushR/2);
			
			brushList.add(new Brush(brushX, brushY, brushR, brushColor));
			repaint();
		}
		
		public void mouseMoved(MouseEvent e) {;}
	}
}
