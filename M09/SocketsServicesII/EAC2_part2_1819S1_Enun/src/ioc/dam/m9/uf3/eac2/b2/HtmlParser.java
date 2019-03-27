/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b2;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 *
 * @author Usuari
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 *
 * Curs 18/19
 */
public class HtmlParser {

    HTMLEditorKit.ParserCallback mHandler;
    ParserDelegator mParser;
    String mTraduccio;
    public HtmlParser() {
        this.mHandler = new HtmlPrintExternalPathsHandler();
        this.mParser = new ParserDelegator();
    }

    public String parse(InputStream in) throws IOException {
        mParser.parse(new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)),mHandler, true );
        return mTraduccio;
    }
    
    public class HtmlPrintExternalPathsHandler extends HTMLEditorKit.ParserCallback {

        boolean traduccioTrobada = false;
        
        @Override
        public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
            processTag(t, a);          
        }

        @Override
        public void handleSimpleTag(Tag t, MutableAttributeSet a, int pos) {
            processTag(t, a);
        }

        @Override
        public void handleText(char[] chars, int i) {
            // Només prenem el text quan abans hem trobat un tag <div class="trans" lang="ca">
            if(traduccioTrobada) {
               mTraduccio = new String(chars);                
            }
            traduccioTrobada = false;
        }
        
       
        public void processTag(Tag t, MutableAttributeSet a) {
            if (mTraduccio==null && t.equals(HTML.Tag.SPAN)) {
                String valueClass = (String) a.getAttribute(HTML.Attribute.CLASS);
                String valueLang = (String) a.getAttribute(HTML.Attribute.LANG);
                if ("trans".equals(valueClass)&&"ca".equals(valueLang)) {
                    traduccioTrobada = true;
                }
            } 
        }
    }       
}
 
 

