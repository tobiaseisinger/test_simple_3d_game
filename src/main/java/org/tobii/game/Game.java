package org.tobii.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Game extends JFrame implements Runnable {
    private static final Long serialVersionUID = 1L;
    public int mapWidth = 15;
    public int mapHeight = 15;
    public Camera camera;
    private Thread gameThread;
    private boolean gameRunning = true;
    private BufferedImage bufferedImage;
    public int[] pixel;
    public ArrayList<Texture> textures;
    public static int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };


    public Game() {
        gameThread = new Thread(this);
        bufferedImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        pixel = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        camera = new Camera(7, 7, 1, 0, 0, -.70);
        addKeyListener(camera);
        textures = new ArrayList<Texture>();
        textures.add(Texture.brick);
        textures.add(Texture.wood);
        setSize(640,480);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Test Raycaster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        start();
    }

    private synchronized void start() {
        gameRunning = true;
        gameThread.start();
    }

    public synchronized void stop() {
        gameRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(),null);
        bufferStrategy.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        requestFocus();
        while (gameRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {       // Game Logik
                camera.update(map);
                delta--;
            }
            render();
        }
    }
}
