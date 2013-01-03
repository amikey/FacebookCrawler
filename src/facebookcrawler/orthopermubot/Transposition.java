package facebookcrawler.orthopermubot;

public class Transposition extends RandomPermutation {
    @Override
	protected String permute( String str, int index ) {
		System.out.println("Transposition typo");
                if ( index == 0 ) {
			index++;
		}
		return str.substring( 0, index - 1 ) + str.charAt( index ) +
			str.charAt( index - 1 ) + str.substring( index + 1 );
	}
}
