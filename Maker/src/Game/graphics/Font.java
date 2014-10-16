package Game.graphics;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/customFont.png", 208, 115, 16);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String charIndex = "ABCDEFGHIJKLM" + "NOPQRSTUVWXYZ" + "abcdefghijklm" + "nopqrstuvwxyz" + "0123456789.,'" + "'\"\";:!@$%()-+" + "#?{}[]*/\\~ "; //Dont forget the space in the last spot
	public Font() {
		
	}
	
	//Default constructor, no color or spacing
	public void render(int x, int y, String text, Screen screen) {
		render(x, y, 0, 0, text, screen);
	}
	
	public void render(int x, int y, int spacing, int color, String text, Screen screen) {
		int xOffset = 0;
		int line = 0;
		for(int i = 0; i < text.length(); i++) {
			xOffset += 16 + spacing;
			int yOffset = 0;
			char currentChar = text.charAt(i);
			if(currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == ',' || currentChar == 'p' || currentChar == 'j') yOffset = 4; //Set offsets for certain chars to appear below the baseline
			if(currentChar == '.') yOffset = 2;
			if(currentChar == '\n') { //New line character
				xOffset = 0;
				line++;
			}
			int index = charIndex.indexOf(currentChar);
			screen.renderTextCharacter(x + xOffset, y + line * 20 + yOffset, characters[index], color, false);
		}
	}
}
