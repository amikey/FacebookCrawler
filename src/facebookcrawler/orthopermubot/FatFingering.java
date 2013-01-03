package facebookcrawler.orthopermubot;

public class FatFingering extends RandomKeyboardPermutation {
	
	public FatFingering( Keyboard keyboard ) {
		super( keyboard );
	}
	
    @Override
	protected String permute( String str, int index ) {
	System.out.println("Fat Fingering typo");	
        return str.substring( 0, index ) +
				keyboard.characterNear( str.charAt( index ) ) + 
				str.substring( index + 1 );
	}
}
