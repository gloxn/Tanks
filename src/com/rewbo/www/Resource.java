package com.rewbo.www;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
/*
加载资源
 */
public class Resource {
    static BufferedImage tankL;
    static BufferedImage tankR;
    static BufferedImage tankU;
    static BufferedImage tankD;
    static BufferedImage missileL,missileR,missileU,missileD;
    static  BufferedImage explode[] = new BufferedImage[10];
   static {
       try {
           tankL = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
           tankR = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
           tankU = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
           tankD = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
           missileL = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/missileL.gif"));
           missileR = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/missileR.gif"));
           missileU = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/missileD.gif"));
           missileD = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/missileU.gif"));
          for (int i =0;i<explode.length;i++) {

              explode[i] = ImageIO.read(Resource.class.getClassLoader().getResourceAsStream("images/"+i+".gif"));
          }
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

}
