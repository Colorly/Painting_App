package paint;

import java.awt.*;

public class Brush {
	private int brushX, brushY, brushR;
	private Color color;
		
	public Brush(int  brushX, int brushY, int brushR, Color color) {
		this.brushX = brushX;
		this.brushY = brushY;
		this.brushR = brushR;
		this.color = color;
	}
	
	public void drawbrush(Graphics graphic) {
		graphic.setColor(color);
		graphic.fillOval(brushX, brushY, brushR, brushR);
	}
}