package facebookcrawler.orthopermubot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QwertyKeyboard implements Keyboard {
	protected Random random = new Random();
	
	protected static class KeyboardPosition {
		public int x;
		public int y;
		public boolean shift;
		
		public KeyboardPosition( int x, int y, boolean shift ) {
			this.x = x;
			this.y = y;
			this.shift = shift;
		}
	}
	
	protected String[] keyboard = {
		"1234567890-=",
		"qwertyuiop[]\\",
		"asdfghjkl;'",
		"zxcvbnm,./"
	};
	
	protected String[] shiftKeyboard = {
		"!@#$%^&*()_+",
		"QWERTYUIOP{}|",
		"ASDFGHJKL:\"",
		"ZXCVBNM<>?"
	};
	
	protected Map<Character, KeyboardPosition> charToPositionMap =
		new HashMap<Character, KeyboardPosition>();
	
	public QwertyKeyboard() {
		computeKeyboardPositions( keyboard, false );
		computeKeyboardPositions( shiftKeyboard, true );
	}
	
	protected void computeKeyboardPositions( String[] keyboard, boolean shift ) {
		for ( int y = 0; y < keyboard.length; y++ ) {
			for ( int x = 0; x < keyboard[y].length(); x++ ) {
				charToPositionMap.put( keyboard[y].charAt( x ),
						new KeyboardPosition( x, y, shift ) );
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see net.galluzzo.wave.orthopermubot.permutation.Keyboard#characterNear(char)
	 */
	public char characterNear( char c ) {
		KeyboardPosition position = positionOf( c );
		if ( position == null ) {
			// Can't find the character on the keyboard.
			return c;
		}
		
		Character newChar = null;
		while ( newChar == null ) {
			KeyboardPosition newPosition = positionNear( position );
			newChar = characterAt( newPosition );
		}
		
		return newChar.charValue();
	}
	
	protected KeyboardPosition positionNear( KeyboardPosition pos ) {
		switch ( random.nextInt( 4 ) ) {
			case 0:
				return new KeyboardPosition( pos.x - 1, pos.y, pos.shift );
			case 1:
				return new KeyboardPosition( pos.x + 1, pos.y, pos.shift );
			case 2:
				return new KeyboardPosition( pos.x, pos.y - 1, pos.shift );
			case 3:
				return new KeyboardPosition( pos.x, pos.y + 1, pos.shift );
			default:
				throw new IllegalStateException(
						"Random position change int should be between 0 and 3, inclusive" );
		}
	}
	
	protected KeyboardPosition positionOf( char c ) {
		return charToPositionMap.get( c );
	}
	
	protected Character characterAt( KeyboardPosition pos ) {
		String[] keyboardToUse = pos.shift ? shiftKeyboard : keyboard;
		if ( pos.y < 0 || pos.y >= keyboardToUse.length ) {
			return null;
		}
		
		String keyboardRow = keyboardToUse[pos.y];
		if ( pos.x < 0 || pos.x >= keyboardRow.length() ) {
			return null;
		}
		
		return keyboardRow.charAt( pos.x );
	}
}
