package com.rewbo.www;

import java.awt.*;
import java.util.Random;
/*
坦克类
 */
public class Tank {
    private int x, y;
    static final   int STEP = 10; //坦克移动速度
    private Dir dir;                //坦克方向
    private Tankrame jf;
    private boolean live =true;
    boolean tankDetection = false;
    private  boolean moving = true;
    private Group group = Group.DAD;
    private Random random = new Random();
    private int bulletWidth=Resource.missileD.getWidth();
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tankrame getJf() {
        return jf;
    }

    public void setJf(Tankrame jf) {
        this.jf = jf;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public static int getSTEP() {
        return STEP;
    }

    public int getBulletWidth() {
        return bulletWidth;
    }

    public void setBulletWidth(int bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    public int getTankWidth() {
        return tankWidth;
    }

    public void setTankWidth(int tankWidth) {
        this.tankWidth = tankWidth;
    }

    public int getBulletHigh() {
        return bulletHigh;
    }

    public void setBulletHigh(int bulletHigh) {
        this.bulletHigh = bulletHigh;
    }

    public int getTankHigh() {
        return tankHigh;
    }

    public void setTankHigh(int tankHigh) {
        this.tankHigh = tankHigh;
    }




    public Tank(int x, int y, Dir dir,Group group, Tankrame jf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.jf = jf;
        this.group = group;

    }

    public void paint(Graphics g) {
        if(live) {
            switch (dir) {
                case LEFT:
                    g.drawImage(Resource.tankL, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(Resource.tankR, x, y, null);
                    break;
                case UP:
                    g.drawImage(Resource.tankU, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(Resource.tankD, x, y, null);
                    break;
            }
        }else {
            return;
        }
        if (this.group == Group.DAD &&  random.nextInt(100) >95) dir = Dir.values()[random.nextInt(4)] ;
         move();
        if(group== Group.DAD && random.nextInt(100)>95){
           this.fire();
        }
        wallDetection();
        tankDetection();
    }

    private void move() {

        if (!moving) {
            return;
        } else {
            switch (dir) {
                case LEFT:
                    x -= STEP;
                    break;
                case RIGHT:
                    x += STEP;
                    break;
                case UP:
                    y -= STEP;
                    break;
                case DOWN:
                    y += STEP;
                    break;
            }
    }
    }



    public void fire() {
        int bX = (x+tankWidth/2-bulletWidth/2)+2;
        int bY= (y+tankHigh/2-bulletHigh/2)+2;
       jf.bullets.add(new Bullet(bX,bY,this.dir,group,jf));
    }

    public void die() {
       live = false;
    }
    //坦克出界检测
    public void wallDetection() {
        if (x< 5) x=5;
        if(y<25) y = 25;
        if(x>jf.getWidth()- this.tankWidth) x = jf.getWidth()- this.tankWidth;
        if(y>jf.getHeight()- this.tankHigh) y = jf.getHeight()- this.tankHigh ;

    }

    public void tankDetection() {
        for (int i =0; i<jf.enemyTank.size(); i++){
            Tank  tank = jf.enemyTank.get(i);
        Rectangle rectangle = new Rectangle(jf.myTank.getX(), jf.myTank.getY(), this.getTankWidth(), this.getTankHigh());
        Rectangle ta = new Rectangle(tank.getX(),tank.getY(),tank.getTankWidth(),tank.getTankHigh());
        if(rectangle.intersects(ta)) {
           // System.out.println("c");
           if(dir == Dir.UP) y += STEP;
           if(dir == Dir.DOWN) y-= STEP;
           if(dir == Dir.LEFT) x += STEP;
           if (dir == Dir.RIGHT) x -= STEP;
        }
        }
    }
}