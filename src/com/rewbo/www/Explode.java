package com.rewbo.www;

import java.awt.*;
import java.util.Random;
/*
爆炸类

* */
public class Explode {
    private  int step = 0;
    private  boolean moving = true;
    private Tankrame jf;
    private int bulletWidth=Resource.missileD.getWidth();
    private int x, y;
    private int tankWidth=Resource.tankD.getWidth();
    private int bulletHigh=Resource.missileD.getHeight();
    private int tankHigh=Resource.tankD.getHeight();



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTankWidth() {
        return tankWidth;
    }

    public void setTankWidth(int tankWidth) {
        this.tankWidth = tankWidth;
    }

    public int getTankHigh() {
        return tankHigh;
    }

    public void setTankHigh(int tankHigh) {
        this.tankHigh = tankHigh;
    }

    public Explode(int x, int y,  Tankrame jf) {
        this.x = x;
        this.y = y;
        this.jf = jf;

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }







    public void paint(Graphics g) {
            //画出爆炸 并删除
                g.drawImage(Resource.explode[step++],x,y,null);


                if (step >=Resource.explode.length){
                    jf.explodes.remove(this);

                }



        

    }



}