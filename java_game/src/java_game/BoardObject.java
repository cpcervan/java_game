package java_game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class BoardObject {

	private BufferedImage image;
	protected Point pos;
	

	public BoardObject() {
		super();
	}

	protected void loadImage(String path) {
	    try {
	        // you can use just the filename if the image file is in your
	        // project folder, otherwise you need to provide the file path.
	        image = ImageIO.read(new File(path));
	        int newWidth = Board.TILE_SIZE;
            int newHeight = Board.TILE_SIZE;

            // Scale the image
            image = scaleImage(image, newWidth, newHeight);
	    } catch (IOException exc) {
	        System.out.println("Error opening image file: " + exc.getMessage());
	    }
	}
	
	private static BufferedImage scaleImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = scaledImage.createGraphics();

        // Improve scaling quality
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return scaledImage;
    }

	public void draw(Graphics g, ImageObserver observer) {
	    // with the Point class, note that pos.getX() returns a double, but 
	    // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
	    // this is also where we translate board grid position into a canvas pixel
	    // position by multiplying by the tile size.
	    g.drawImage(
	        image, 
	        pos.x * Board.TILE_SIZE, 
	        pos.y * Board.TILE_SIZE, 
	        observer
	    );
	}

	public Point getPos() {
	    return pos;
	}

}