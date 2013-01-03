package facebookcrawler.orthopermubot;

public class Deletion extends RandomPermutation {
    @Override
	protected String permute( String str, int index ) {
	System.out.println("Deletion typo");	
        return str.substring( 0, index ) + str.substring( index + 1 );
	}
}
