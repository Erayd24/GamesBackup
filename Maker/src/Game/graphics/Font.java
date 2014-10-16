package Game.graphics;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/customFont.png", 208, 112, 16);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String charIndex = "ABCDEFGHIJKLM" + "NOPQRSTUVWXYZ" + "abcdefghijklm" + "nopqrstuvwxyz" + "0123456789.,'" + "'\"\";:!@$%()-+" + "#?{}[]*/\\~_ "; //Dont forget the space in the last spot
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
		
		for(int i = 0; i < text.length(); i++) {
			xOffset += 16 + spacing;
			int yOffset = 0;
			char currentChar = text.charAt(i);
			if(i != 0) lastChar = text.charAt(i - 1);
			if(change == true && lastChar == 'i') spacing += 4; 
			if(change == true && lastChar == ' ') spacing += 8; 
			if(currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == ',' || currentChar == 'p' || currentChar == 'j') yOffset = 4; //Set offsets for certain chars to appear below the baseline
			if(currentChar == '.') yOffset = 2;
			if(currentChar == '\n') { //New line character
				xOffset = 0;
				line++;
			}
			if(currentChar == 'i') {
				spacing -= 2;
				change = true;
			}
			if(currentChar == ' ') {
				spacing -= 8;
				change = true;
			}
			int index = charIndex.indexOf(currentChar);
			if(index != -1) screen.renderTextCharacter(x + xOffset, y + line * 20 + yOffset, characters[index], color, false);
		}
	}
}
