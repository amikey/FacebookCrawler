/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facebookcrawler.orthopermubot;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Manu
 */
public class TypoCreator {
    static private Keyboard keyboard = new QwertyKeyboard();
    //private ArrayList<RandomPermutation> typoTypeArray;
    public static String realisticTypos(String realString) {
        
        String copyOfRealString = realString;
        
        RandomPermutation[] typoTypeArray = {
            ( new Repetition() ), 
            ( new Insertion( keyboard ) ), 
            ( new Deletion() ),
            ( new Transposition() ),
            ( new FatFingering( keyboard ) )
        };
        
        //String sdf = typoTypeArray[0].permute("fdsfsadfa", 0);
        
        
        //if ((typoTypeArray == null)) {
//            typoTypeArray.add( new Repetition() );
//            typoTypeArray.add( new Insertion( keyboard ) );
//            typoTypeArray.add( new Deletion() );
//            typoTypeArray.add( new Transposition() );
//            typoTypeArray.add( new FatFingering( keyboard ) );
        //}
        
        int randIntNumErrorFreq = (new Random()).nextInt(9);
        int numOfErrors = 0;
        ArrayList<RandomPermutation> selectedTypoTypeArrayList = new ArrayList();
        
        if (randIntNumErrorFreq < 2) {
            numOfErrors = 0;
        }
        else if (randIntNumErrorFreq < 8) {
            System.out.println("1 typo added");
            numOfErrors = 1;
        }
        else if (randIntNumErrorFreq < 10) {
            System.out.println("2 typos added");
            numOfErrors = 2;
        }
        
        if (numOfErrors == 0) {
            System.out.println("no typos added");
            return realString;
        }
        
        else {
            for (int i = 0; i < numOfErrors; i++) {
                selectedTypoTypeArrayList.add(typoTypeArray[((new Random()).nextInt(typoTypeArray.length))]);
            }
            
            if (selectedTypoTypeArrayList != null) {
                for (int i = 0; i < selectedTypoTypeArrayList.size(); i ++) {
                    
                    
                    copyOfRealString = ((RandomPermutation)selectedTypoTypeArrayList.get(i)).permute(copyOfRealString);
                }
            }
            
        }
        
        return copyOfRealString;
    }
}
