package com.rewbo.www;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


class Tankrame extends JFrame {

            Tank myTank = new Tank(200,500,Dir.UP,Group.GOOD,this);
            List<Tank> enemyTank = new ArrayList();
            List<Bullet> bullets = new ArrayList();
            List<Explode> explodes = new ArrayList<>();
            public   final int GAME_WIDTH=800,GAME_HIGH=600;
  public Tankrame (){
        this.setSize(GAME_WIDTH,GAME_HIGH);
        setTitle("Tank");
        setResizable(false);
        setVisible(true);
      this.addKeyListener(new JfListener());
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               System.exit(0);
           }
       });


    }



    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {

        if(offScreenImage ==  null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HIGH);
        }
        Graphics s = offScreenImage.getGraphics();
       Color color = s.getColor();
        s.setColor(Color.BLACK);
        s.fillRect(0,0,GAME_WIDTH,GAME_HIGH);
      //  s.setColor(color);
        paint(s);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color color = g.getColor();
        g.setColor(Color.BLACK);

        g.fillRect(0, 0, GAME_WIDTH, GAME_HIGH);
        g.setColor(color);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量"+bullets.size(),10,60);
        g.drawString("坦克数量"+enemyTank.size(),10,80);
        g.drawString("爆炸数量"+explodes.size(),10,100);


        g.setColor(c);



        myTank.paint(g);
        for (int i=0;i < enemyTank.size();i++) {

            if(!enemyTank.get(i).isLive()){
                enemyTank.remove(i);
            }else {
                enemyTank.get(i).paint(g);
            }

        }
       for(int i =0;i<bullets.size();i++){

             bullets.get(i).paint(g);

       }
       for (int i=0;i<bullets.size();i++){
           for (int j=0;j<enemyTank.size();j++){
               bullets.get(i).bulletDetection(enemyTank.get(j));
           }
       }
       for(int i =0; i<explodes.size();i++){
           explodes.get(i).paint(g);
       }

    }

    class JfListener extends KeyAdapter {
       boolean bl = false;
       boolean br = false;
       boolean bu = false;
       boolean bd = false;
       boolean b = true;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode){
                case  KeyEvent.VK_LEFT :
                    bl =  true;
                    break;
                case  KeyEvent.VK_RIGHT :
                    br = true;
                    break;
                case  KeyEvent.VK_UP :
                    bu = true;
                    break;
                case  KeyEvent.VK_DOWN :
                    bd = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:break;
            }

                setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode){
                case  KeyEvent.VK_LEFT :
                    bl =  false;
                    break;
                case  KeyEvent.VK_RIGHT :
                    br = false;
                    break;
                case  KeyEvent.VK_UP :
                    bu = false;
                    break;
                case  KeyEvent.VK_DOWN :
                    bd = false;
                    break;
                default:break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){

            if(!bl && !br && !bu && !bd) {
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bl) myTank.setDir(Dir.LEFT);
                if(br) myTank.setDir(Dir.RIGHT);
                if(bu) myTank.setDir(Dir.UP);
                if(bd) myTank.setDir(Dir.DOWN);
            }
         }

    }
}
