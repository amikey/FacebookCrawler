package facebookcrawler.orthopermubot;

public abstract class RandomKeyboardPermutation extends RandomPermutation {
	protected Keyboard keyboard = null;
	
	public RandomKeyboardPermutation( Keyboard keyboard ) {
		if ( keyboard == null ) {
			throw new IllegalArgumentException( "keyboard cannot be null" );
		}
		this.keyboard = keyboard;
	}
}
