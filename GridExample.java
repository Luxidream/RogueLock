import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;

@SuppressWarnings("serial")
public class GridExample extends JPanel {
   public int[][] MAP = {
      {1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
      {1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2},
      {1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2},
      {1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2},
      {1, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2},
      {1, 1, 1, 0, 0, 0, 2, 2, 2, 2, 2},
      {1, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2},
      {1, 1, 1, 0, 0, 0, 2, 2, 2, 2, 2},
      {1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2},
      {1, 1, 1, 1, 1, 0, 0, 0, 2, 2, 2},
      {1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 2}
   };
   


   public static final Color[] COLORS = {};
   private JLabel[][] labelGrid = new JLabel[5][5];
   
   public void setMap (int[][] map) {
	   this.MAP = map;
   }

   public GridExample() {
      setLayout(new GridLayout(MAP.length, MAP[0].length));
      for (int r = 0; r < labelGrid.length; r++) {
         for (int c = 0; c < labelGrid[r].length; c++) {
            labelGrid[c][r] = new JLabel();
            labelGrid[c][r].setIcon(Ground.getGround(MAP[c][r]).getIcon());
            add(labelGrid[c][r]);            
         }
      }
   }
   
   public GridExample(int[][]map) {
	   	  this.MAP = map;
	      setLayout(new GridLayout(MAP.length, MAP[0].length));
	      for (int r = 0; r < labelGrid.length; r++) {
	         for (int c = 0; c < labelGrid[r].length; c++) {
	            labelGrid[c][r] = new JLabel();
	            labelGrid[c][r].setIcon(Ground.getGround(MAP[c][r]).getIcon());
	            add(labelGrid[c][r]);            
	         }
	      }
	   }
   
   

   public static void createAndShowGui() {
      GridExample mainPanel = new GridExample();

      JFrame frame = new JFrame("GridExample");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
   
   public static void createAndShowGui(int[][] map) {
	      GridExample mainPanel = new GridExample();

	      JFrame frame = new JFrame("GridExample");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add(mainPanel);
	      frame.pack();
	      frame.setLocationByPlatform(true);
	      frame.setVisible(true);
	   }
   


   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}

enum Ground {
   DIRT(0, new Color(205,133, 63)), GRASS(1, new Color(0, 107, 60)), 
   WATER(2, new Color(29, 172, 214)), WATER2(7, new Color(29, 172, 214)), 
   WATER3(3, new Color(29, 172, 214)), WATER4(4, new Color(29, 172, 214)),
   WATER5(5, new Color(29, 172, 214)), WATER6(6, new Color(29, 172, 214));
   private int value;
   private Color color;
   private Icon icon;

   private Ground(int value, Color color) {
      this.value = value;
      this.color = color;

      icon = createIcon(value);
   }

   private Icon createIcon(Color color) {
      int width = 24; // how to use const in enum? 
      BufferedImage img = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
      Graphics g = img.getGraphics();
      g.setColor(color);
      g.fillRect(0, 0, width, width);
      g.dispose();
      return new ImageIcon(img);
   }
   
   private Icon createIcon(int value) {
	   BufferedImage img1 = null;
	   BufferedImage img2 = null;
	   BufferedImage img3 = null;
	   BufferedImage img4 = null;
	   BufferedImage img5 = null;
	   BufferedImage img6 = null;
	   BufferedImage img7 = null;
	   try {
	       img1 = ImageIO.read(new File("C:/Icons/player.png"));
	       img2 = ImageIO.read(new File("C:/Icons/buckler.png"));
	       img3 = ImageIO.read(new File("C:/Icons/chest.png"));
	       img4 = ImageIO.read(new File("C:/Icons/flag.png"));
	       img5 = ImageIO.read(new File("C:/Icons/pennant.png"));
	       img6 = ImageIO.read(new File("C:/Icons/star.png"));
	       img7 = ImageIO.read(new File("C:/Icons/sword.png"));
	   } catch (IOException e) {
	   }
	   switch(value) {
	   case 1:
		   return new ImageIcon(img1);
	   case 2:
		   return new ImageIcon(img2);
	   case 3:
		   return new ImageIcon(img3);
	   case 4:
		   return new ImageIcon(img4);
	   case 5:
		   return new ImageIcon(img5);
	   case 6:
		   return new ImageIcon(img6);
	   case 7:
		   return new ImageIcon(img7);
		   
		 
	
	   }
	  BufferedImage img8 = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
	  Graphics g = img8.getGraphics();
	  g.setColor(new Color(200,255,255));
	  g.fillRect(0, 0, 24, 24);
	  g.dispose();
	  return new ImageIcon(img8);
   }

   public int getValue() {
      return value;
   }

   public Color getColor() {
      return color;
   }

   public Icon getIcon() {
      return icon;
   }

   public static Ground getGround(int value) {
      for (Ground ground : Ground.values()) {
         if (ground.getValue() == value) {
            return ground;
         }
      }
      return null;
   }

}