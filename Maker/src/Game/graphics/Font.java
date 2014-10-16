package Game.graphics;

public class Font {

	private boolean visible = true;
	
	private static SpriteSheet font = new SpriteSheet("/fonts/customFont.png", 208, 112, 16);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String charIndex = "ABCDEFGHIJKLM" + "NOPQRSTUVWXYZ" + "abcdefghijklm" + "nopqrstuvwxyz" + "0123456789.,'" + "'\"\";:!@$%()-+" + "#?{}[]*/\\~_ ";
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
				if(change == true && lastChar == 'I') spacing += 3;
				if(change == true && lastChar == 'i') spacing += 3; 
				if(change == true && lastChar == 't') spacing += 4;
				if(change == true && lastChar == ' ') spacing += 4;
				if(change == true && lastChar == 'u') spacing += 1;
				if(change == true && lastChar == 'q') spacing += 3;
				if(change == true && lastChar == 'g') spacing += 3;
				if(change == true && lastChar == 'c') spacing += 2;
				if(change == true && lastChar == 'v') spacing += 4;

				change = false;
				//Set offsets for special chars in the y-direction
				if(currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == ',' || currentChar == 'p' || currentChar == 'j') yOffset = 4; if(currentChar == '.') yOffset = 2;
				if(currentChar == '\n') { //New line character
					xOffset = 0;
					line++;
				}
				
				//Set all special indentations of characters here
				if(nextChar == 'i') { //Before the i
					spacing -= 2;
					change = true;
				}
				if(nextChar == 'v') { //Before the v
					spacing -= 2;
					change = true;
				}
				if(nextChar == 'c') { //Before the c
					spacing -= 2;
					change = true;
				}
				if(nextChar == 'g') { //Before the g
					spacing -= 3;
					change = true;
				}
				if(nextChar == 't') { //Before the t
					spacing -= 3;
					change = true;
				}
				if(currentChar == 'v') { //After the v
					spacing -= 2;
					change = true;
				}
				if(currentChar == 'i') { //After the i
					spacing -= 1;
					change = true;
				}
				if(currentChar == 't') { //After the t
					spacing -= 1;
					change = true;
				}
				if(currentChar == 'I') { //After the I
					spacing -= 3;
					change = true;
				}
				if(currentChar == 'u') { //after the u
					spacing -= 1;
					change = true;
				}
				if(currentChar == 'q') { //after the q
					spacing -= 3;
					change = true;
				}
				if(currentChar == ' ') { //after the space
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
