package com.main.Graphics;

import com.main.Main;
import com.main.Utils.Keybindings;
import com.main.Sprites.Hero;

import com.main.worldgen.World;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable{
    public static final int WIDTH = Main.dimension.width;
    public static final int HEIGHT = Main.dimension.width;
    public static final int textureTileSize = 32;
    Hero hero;
    Thread thread;
    Keybindings keybindings;
    World world;
    int x;
    int y;
    int speed;
    boolean walking;
    public Game(){
        world = new World(0, 0,56, 100);
        keybindings = new Keybindings();
        this.setPreferredSize(Main.dimension);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setDoubleBuffered(true);
        hero = new Hero();
        this.addKeyListener(keybindings);
        this.setFocusable(true);
    }

    public void start(){
        if (thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }
    public void stop(){
        if (thread != null){
            thread = null;
        }
    }

    public void update(){


        if (keybindings.up){
            world.moveWorld(0,-2,2);
        }
        if (keybindings.down){

            world.moveWorld(0,2,2);
        }

        if (keybindings.right){
            world.moveWorld(2,0,2);
        }

        if (keybindings.left) {
            world.moveWorld(-2,0,2);
        }
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        world.renderWorld(g2d);
        world.renderTrees(g2d);
        hero.draw(g2d);
        g2d.dispose();
    }

    @Override
    public void run() {
        while (thread!=null){
            update();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
