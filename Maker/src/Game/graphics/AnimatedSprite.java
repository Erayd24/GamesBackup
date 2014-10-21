package Game.graphics;

public class AnimatedSprite extends Sprite{
	
	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int length = -1; //how many sprites in the animation
	private int time = 0;
	
	//Constructor - Create an animated sprite sheet
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		sprite = sheet.getSprites()[0];
		this.length = length;
	}

	//Updates the frame and changes the sprite to visualize an animation
	public void update() {
		time++;
		if(time % rate == 0) {
			if(frame >= length - 1) frame = 0;
			else frame ++;
			sprite = sheet.getSprites()[frame];
		}
	}
	
	//Return a sprite to show to the screen
	public Sprite getSprite() {
		return sprite;
	}
	
	//Set a faster rate or slower rate for each new animation
	public void setFrameRate(int frames) {
		rate = frames;
	}
	
	//a Frame is a sprite in the animation, this sets the sprite as the current one
	public void setFrame (int index) {
		if(index > sheet.getSprites().length - 1) {
			System.err.println("Index Out of Bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}
}