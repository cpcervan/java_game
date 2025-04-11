package java_game;
import java.awt.Point;

public class Coin extends BoardObject{
    
    private int tickCount = 0;
    
    private static final int DISSAPPEAR_SECONDS = 4;
    
    private int value;

    public Coin(int x, int y) {
    	super();
        // load the assets
        loadImage("images/coin.png");

        // initialize the state
        pos = new Point(x, y);
        
        value = 100;
    }
    
    public void tick() {
    	tickCount++;
    }
    
    public boolean isGone() {
    	int neededTicks = (int)(1000*DISSAPPEAR_SECONDS/Board.getDelay() + 0.5);
    	return tickCount == neededTicks;
    }
    
    public int getValue() {
    	return value;
    }
}
