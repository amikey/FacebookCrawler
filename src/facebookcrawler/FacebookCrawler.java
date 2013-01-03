/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facebookcrawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import facebookcrawler.orthopermubot.TypoCreator;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manu
 */
public class FacebookCrawler {

    /**
     * @param args the command line arguments
     */
    public static void startTimedMessages() throws IOException {
        // TODO code application logic here


        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
            new Runnable(){
                @Override
                public void run()
                {
                    try {
                        FacebookCrawler.postToGroup("183107588431067", FacebookCrawler.getRandomMessage());
                    } catch (IOException ex) {
                        System.out.println("ERROR");
                    }
                }
            },
            0,  //Delay
            57,  //Interval
            TimeUnit.MINUTES
        );
        
    }
    
    public static void postToGroup(String groupID, String message) throws IOException {
        BrowserVersion useragent = new BrowserVersion(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), (new Random()).nextInt(10));
        
        final WebClient webClient = new WebClient(useragent);
        webClient.setJavaScriptEnabled(false);
        webClient.setCssEnabled(false);
        JavaScriptEngine engine = new JavaScriptEngine(webClient);
        webClient.setJavaScriptEngine(engine);
        
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");
        final HtmlPage loginPage = webClient.getPage("http://m.facebook.com/");
        final HtmlForm form = (HtmlForm) loginPage.getElementsByTagName("form").get(0);
                
        final HtmlSubmitInput button = form.getInputByName("login");
        
        System.out.println(button.asXml());
        
        final HtmlTextInput textField = form.getInputByName("email");
        textField.setValueAttribute("viveks3th@gmail.com");
        final HtmlPasswordInput textField2 = form.getInputByName("pass");
        textField2.setValueAttribute("PASS HERE");
        final HtmlPage loginResponsePage = button.click();

        final HtmlPage rutgersGroupPage = webClient.getPage("http://m.facebook.com/groups/"+groupID);
        
        final HtmlForm postForm = rutgersGroupPage.getFirstByXPath("//form[@id='composer_form']");
                       
        System.out.println(postForm.getElementById("composerInput").asXml());
        
        HtmlTextArea textArea = postForm.getElementById("composerInput");
        
        textArea.setText(message);
        System.out.println(textArea.getText());
        
        HtmlSubmitInput postButton = postForm.getInputByName("update");
        System.out.println(postButton.asXml());
        
        postButton.click();
    }
    
    public static void postToRUGroup(String message) throws IOException {
        FacebookCrawler.postToGroup("183107588431067", message);
    }
    
    public static String getRandomMessage() {
        String[] messagesArray = {
            "I have Douglass, Lippincott and i'm looking to trade\n"
                + "\n"
                + "Located right next to bus stop, almost all SEBS classes, dining hall, has a beautiful view, and a lot of hot girls\n"
                + "\n"
                + "Looking to trade for any other campus MESSAGE me if interested",
            "I'm in Douglass campus, Lippincott Hall.\n"
                + "\n"
                + "Want to trade?\n"
                + "\n"
                + "It's a beautiful campus, close to the bus stop and dining halls. not to mention all the girls :P. Please MESSAGE me if intersted.",
            "Hey, I'm looking to trade my Douglass, Lippincott.\n"
                + "\n"
                + "SEBS students, Douglass - Lippincott is conveniently located right near all the SEBS classes so if you're a SEBS student please trade!\n"
                + "\n"
                + "It's also next to the dining hall, bus stop, and passion puddle, not to forget all the ladies here. Please MESSAGE me if you're interested in trading"
        };
        
        int index = (new Random()).nextInt(messagesArray.length);
        String selectedMessage = messagesArray[index];
        
        String selectedMessageWithTypos = TypoCreator.realisticTypos(selectedMessage);
        
        //System.out.println(selectedMessageWithTypos);
        
        return selectedMessageWithTypos;
        
    }
    
    
}
