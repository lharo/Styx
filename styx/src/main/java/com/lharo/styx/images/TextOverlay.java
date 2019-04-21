package com.lharo.styx.images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @see https://stackoverflow.com/questions/2658663
 */
public class TextOverlay extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private String imagePath;
	
    public TextOverlay(String msg) throws IOException {
        try {
            image = ImageIO.read(new URL(
                "https://media.istockphoto.com/photos/travel-planning-concept-on-map-picture-id891573112?k=6&m=891573112&s=612x612&w=0&h=4FMEW6gJadYGC5yMBeolS4rc7EzZa9_2DU1Y3-Ma7a4="));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = process(image, msg);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
    
    void drawStrings(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
    public static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        if(m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for(int i = 1; i < words.length; i++) {
                if(m.stringWidth(currentLine+words[i]) < lineWidth) {
                    currentLine += " "+words[i];
                } else {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if(currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }
    }

    private BufferedImage process(BufferedImage old, String msg) throws IOException {
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(
            w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, this);
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        //System.out.println("s " + msg);
        FontMetrics fm = g2d.getFontMetrics();
        int y = fm.getHeight();
        for (String line : msg.split("\n")) {
            int x = img.getWidth() - fm.stringWidth(line) - 5;
        	System.out.println("Line " + line);
        	g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
        }
        //g2d.drawString(msg, x, y);
        g2d.dispose();

        String fileExtension = ".jpg";
        String filename = UUID.randomUUID().toString();
        String filePath = "img/";
        filePath = filePath + filename + fileExtension;
        File f = new File(filePath);
        setImagePath(filePath);
        ImageIO.write(img, "jpg", f);
        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    private static void create(String msg) throws IOException {
    	JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new TextOverlay(msg));
        f.pack();
        f.setVisible(true);
    }

    public void showImage(String msg) {
        //TextOverlay.msg = msg;
    	EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                	create(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}