package org.tobii.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {
    public double xPos, yPos, xDir, yDir, xPlane, yPlane;
    public boolean left, right, forward, backward;
    public final double MOVE_SPEED = .1;
    public final double ROTATE_SPEED = .1;

    public Camera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xDir = xDir;
        this.yDir = yDir;
        this.xPlane = xPlane;
        this.yPlane = yPlane;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) forward = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) backward = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) forward = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) backward = false;
    }

    public void update(int[][] map) {
        // Vorwärts
        if (forward) {
            if (map[(int)(xPos+xDir * MOVE_SPEED)][(int)yPos] == 0) {
                xPos += xDir * MOVE_SPEED;
            }
            if (map[(int)xPos][(int)(yPos+yDir * MOVE_SPEED)] == 0) {
                yPos += yDir * MOVE_SPEED;
            }
        }

        // Rückwärts
        if (backward) {
            if (map[(int)(xPos+xDir * MOVE_SPEED)][(int)yPos] == 0) {
                xPos += xDir * MOVE_SPEED;
            }
            if (map[(int)xPos][(int)(yPos+yDir * MOVE_SPEED)] == 0) {
                yPos += yDir * MOVE_SPEED;
            }
        }

        // Rotation Rechts
        if (right) {
            double oldxDir = xDir;
            xDir += xDir * Math.cos(-ROTATE_SPEED) - yDir * Math.sin(-ROTATE_SPEED);
            yDir += oldxDir * Math.sin(-ROTATE_SPEED) + yDir * Math.cos(-ROTATE_SPEED);
            double oldxPlane = xPlane;
            xPlane = xPlane * Math.cos(-ROTATE_SPEED) - yPlane * Math.sin(-ROTATE_SPEED);
            yPlane = oldxPlane * Math.sin(-ROTATE_SPEED) + yPlane * Math.cos(-ROTATE_SPEED);
        }

        // Rotation Links
        if (left) {
            double oldxDir = xDir;
            xDir += xDir * Math.cos(ROTATE_SPEED) - yDir * Math.sin(ROTATE_SPEED);
            yDir += oldxDir * Math.sin(ROTATE_SPEED) + yDir * Math.cos(ROTATE_SPEED);
            double oldxPlane = xPlane;
            xPlane = xPlane * Math.cos(ROTATE_SPEED) - yPlane * Math.sin(ROTATE_SPEED);
            yPlane = oldxPlane * Math.sin(ROTATE_SPEED) + yPlane * Math.cos(ROTATE_SPEED);
        }
    }
}
