package com.rewbo.www;

import java.awt.*;
/*
子弹类
 */
public class Bullet {
    private static  final int STEP = 20;
    private  int x,y;
    private  Dir dir;
    private Group group = Group.DAD;
    private static  final int WIDTH = 20,HIGH=20;
    private Tankrame jf;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir,Group group, Tankrame jf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.jf = jf;
        this.group = group;
    }



    public void paint(Graphics g) {
        if(!live){
            jf.bullets.remove(this);
        }
        switch (dir){
            case LEFT: g.drawImage(Resource.missileL,x,y,null);
                break;
            case RIGHT: g.drawImage(Resource.missileR,x,y,null);
                break;
            case UP: g.drawImage(Resource.missileU,x,y,null);
                break;
            case DOWN: g.drawImage(Resource.missileD,x,y,null);
                break;
        }

        move();

    }
    private void move() {

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
        if(x<0 || y<0 || x>800 || y>600) live = false;
    }
    //爆炸检测
    public void bulletDetection(Tank tank) {
        if(this.group == tank.getGroup()) return;
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HIGH);
        Rectangle ta = new Rectangle(tank.getX(),tank.getY(),tank.getTankWidth(),tank.getTankHigh());
        if(rectangle.intersects(ta)){
            tank.die();
            this.die();

            jf.explodes.add(new Explode(tank.getX(),tank.getY(),jf));
        }


    }

    private void die() {
        live = false;
    }
}
