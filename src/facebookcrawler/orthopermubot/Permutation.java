package facebookcrawler.orthopermubot;

/**
 * An individual type of permutation that can be applied to a string to generate
 * a new string.
 * 
 * @author Eric Galluzzo
 */
public abstract class Permutation {
	/**
	 * Permutes the given string.
	 * 
	 * @param str  The string to permute
	 * 
	 * @return  The permutation of the string
	 */
	abstract String permute( String str );
}
