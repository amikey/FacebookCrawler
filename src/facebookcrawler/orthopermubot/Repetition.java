package facebookcrawler.orthopermubot;

public class Repetition  extends RandomPermutation {

        public Repetition() {
            super();
        }
    
    @Override
        protected String permute( String str, int index ) {
            System.out.println("repetition typo");
            return str.substring( 0, index ) + str.charAt( index ) +
				str.substring( index );
	}
}
