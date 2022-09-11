package com.qist13;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Cell extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final int SIZE = 30;
	
	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;
	
	private int row = -1;
	private int col = -1;
	
	private boolean[] wall = {true, true, true, true};
	private boolean[] path = {false, false, false, false};
	
	private boolean current = false;
	private boolean end = false;
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void paintComponent(Graphics g) {
		// draw the background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE, SIZE);
		g.setColor(Color.BLACK);
		
		if (isWall(TOP)) {
			g.drawLine(0, 0, SIZE, 0);
		}
		if (isWall(LEFT)) {
			g.drawLine(0, 0, 0, SIZE);
		}
		
		// draw the balls
		if (current) {
			g.setColor(Color.GREEN);
			g.fillOval(3, 3, SIZE - 6, SIZE - 6);
		} else if (end) {
			g.setColor(Color.RED);
			g.fillOval(3, 3, SIZE - 6, SIZE - 6);
		}
		
		// draw the path
		g.setColor(Color.GREEN);
		if (path[TOP]) {
			g.drawLine(SIZE/2, 0, SIZE/2, SIZE/2);
		}
		if (path[BOTTOM]) {
			g.drawLine(SIZE/2, SIZE/2, SIZE/2, SIZE);
		}
		if (path[LEFT]) {
			g.drawLine(0, SIZE/2, SIZE/2, SIZE/2);
		}
		if (path[RIGHT]) {
			g.drawLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
		}
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(SIZE, SIZE);
		return size;
	}
	
	public void addPath(int side) {
		path[side] = true;
		repaint();
	} 
	
	public void setEnd(boolean end) {
		this.end = end;
		repaint();
	}
	
	public void setCurrent(boolean current) {
		this.current = current;
		repaint();
	}
	
	public boolean hasAllWalls() {
		boolean allWalls = isWall(TOP) && isWall(LEFT) && isWall(BOTTOM) && isWall(RIGHT);
		return allWalls;
	}
	
	public void removeWall(int w) {
		wall[w] = false;
		repaint();
	}
	
	public void openTo(Cell neighbor) {
		if (row < neighbor.getRow()) {
			removeWall(BOTTOM);
			neighbor.removeWall(TOP);
		} else if (row > neighbor.getRow()) {
			removeWall(TOP);
			neighbor.removeWall(BOTTOM);
		} else if (col < neighbor.getCol()) {
			removeWall(RIGHT);
			neighbor.removeWall(LEFT);
		} else if (col > neighbor.getCol()) {
			removeWall(LEFT);
			neighbor.removeWall(RIGHT);
		}
	}
	
	public boolean isWall(int index) {
		return wall[index];
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
