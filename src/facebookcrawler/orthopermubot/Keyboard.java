package facebookcrawler.orthopermubot;

/**
 * A class representing a keyboard.
 * 
 * @author Eric Galluzzo
 */
public interface Keyboard {

	/**
	 * Finds a character near the given character on the keyboard.  If the
	 * character cannot be found on the keyboard, returns the character itself.
	 * 
	 * @param c  The character
	 * 
	 * @return  The character on the keyboard
	 */
	char characterNear( char c );
}