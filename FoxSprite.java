//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener; // why is NetBeans complaining
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import javax.swing.*;
//
//class FoxSprite {
//    
//    private BufferedImage fox1 = null;
//    private BufferedImage fox2 = null;
//    private BufferedImage fox3 = null;
//    
//    public FoxSprite() {
//    
//    fox1 = loadImage("fox1.png");
//    fox2 = loadImage("fox2.png");
//    fox3 = loadImage("fox3.png");
//    
//    }
//    
//    BufferedImage loadImage(String name) {
//        try {
//            return ImageIO.read(getClass().getClassLoader().getResource(name));
//        } catch (IOException e) {
//            System.out.println("An error happened: " + e.toString());
//            return null;
//        }
//    }
//     
//    public void paintComponent(Graphics g) {
//        
//    int xAxis = -300; // spawn off-screen
//    String drawSprite = "fox";
//    int delay = 125; // time in milliseconds
//    int reset = 1; // animation resets when 3 frames are drawn
//    Timer timer;
//    
//    ActionListener frameSwitch = new ActionListener() {
//        public void actionPerformed(ActionEvent evt)
//        {
//            for(reset = 1; reset < 4; reset = reset + 1) {
//                drawSprite = "fox" + reset;
//                g.drawImage(drawSprite, xAxis, 590, this);
//                xAxis = xAxis + 3;
//                if (reset == 3) {
//                    reset = 0;
//                }
//            }
//        }
//    };
//    new Timer(delay, frameSwitch).start();
//    
//    }
//}
