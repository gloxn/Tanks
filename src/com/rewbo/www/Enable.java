package com.rewbo.www;

public class Enable {
    public static void main(String[] args) throws InterruptedException {
        Tankrame jf = new Tankrame ();
        for (int i=0;i<5;i++){
            jf.enemyTank.add(new Tank(50+i*50,200,Dir.DOWN,Group.DAD,jf));

        }
        while(true) {
            Thread.sleep(50);
            jf.repaint();

        }
    }
}
