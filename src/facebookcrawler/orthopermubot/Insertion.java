package facebookcrawler.orthopermubot;

public class Insertion extends RandomKeyboardPermutation {
	public Insertion( Keyboard keyboard ) {
		super( keyboard );
	}
	
    @Override
	protected String permute( String str, int index ) {
                System.out.println("Insertion typo");
        // We'll never insert a character at the beginning of the word.
		return str.substring( 0, index + 1 ) +
				keyboard.characterNear( str.charAt( index ) ) +
				str.substring( index + 1 );
	}
}
