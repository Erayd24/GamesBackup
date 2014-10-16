package Game.graphics;

public class Font {

	private boolean visible = true;
	
	private static SpriteSheet font = new SpriteSheet("/fonts/customFont.png", 208, 112, 16);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String charIndex = "ABCDEFGHIJKLM" + "NOPQRSTUVWXYZ" + "abcdefghijklm" + "nopqrstuvwxyz" + "0123456789.,'" + "'\"\";:!@$%()-+" + "#?{}[]*/\\~_ "; //Dont forget the space in the last spot
	//Eras Demi TC
	
	public Font() {
		
	}
	
	//Default constructor, no color or spacing
	public void render(int x, int y, String text, Screen screen) {
		render(x, y, 0, 0, text, screen);
	}
	
	public void render(int x, int y, int spacing, int color, String text, Screen screen) {
		int xOffset = 0;
		int line = 0;
		boolean change = false;
		char lastChar = 'q';
		char nextChar = 'q';
		
		if(visible) {
			for(int i = 0; i < text.length(); i++) {
				xOffset += 16 + spacing;
				int yOffset = 0;
				char currentChar = text.charAt(i);
				if(i != 0) lastChar = text.charAt(i - 1);
				if(i < text.length() - 1) nextChar = text.charAt(i + 1);
				
				//Resetting correct spacing
				if(change == true && lastChar == 'I') spacing += 4;
				if(change == true && lastChar == 'i' || lastChar == 't') spacing += 5; 
				if(change == true && lastChar == ' ') spacing += 4;
				
				//Set offsets for special chars in the y-direction
				if(currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == ',' || currentChar == 'p' || currentChar == 'j') yOffset = 4; if(currentChar == '.') yOffset = 2;
				if(currentChar == '\n') { //New line character
					xOffset = 0;
					line++;
				}
				//Set all special indentations of characters here
				if(nextChar == 'i' || nextChar == 't') {
					spacing -= 3;
					change = true;
				}
				if(currentChar == 'i') {
					spacing -= 2;
					change = true;
				}
				if(currentChar == 'I') {
					spacing -= 4;
					change = true;
				}
				if(currentChar == ' ') {
					spacing -= 4;
					change = true;
				}
				int index = charIndex.indexOf(currentChar);
				if(index != -1) screen.renderTextCharacter(x + xOffset, y + line * 20 + yOffset, characters[index], color, false);
			}
		}
	}
	
	public boolean show() {
		visible = true;
		return visible;
	}
	
	public boolean hide() {
		visible = false;
		return visible;
	}
}
