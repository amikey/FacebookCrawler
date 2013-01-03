package facebookcrawler.orthopermubot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MultiplePermutation extends Permutation {
	protected Random random = new Random();
	protected Keyboard keyboard = null;
	protected List<Permutation> permutations;
	
	public MultiplePermutation( Keyboard keyboard ) {
		if ( keyboard == null ) {
			throw new IllegalArgumentException( "keyboard cannot be null" );
		}
		this.keyboard = keyboard;
		
		registerDefaultPermutations();
	}
	
    @Override
	public String permute( String word ) {
		String newWord = word;
		if ( word.length() >= 3 ) {
			int numPermutations = random.nextInt( word.length() / 2 );
			for ( int i = 0; i < numPermutations; i++ ) {
				Permutation permutation =
						permutations.get( random.nextInt( permutations.size() ) );
				newWord = permutation.permute( newWord );
			}
		}
		return newWord;
	}
	
	protected void registerDefaultPermutations() {
		/*permutations = new ArrayList<Permutation>();
		permutations.add( new Repetition() );
		permutations.add( new Insertion( keyboard ) );
		permutations.add( new Deletion() );
		permutations.add( new Transposition() );
		permutations.add( new FatFingering( keyboard ) );
                * 
                */
	}
}
