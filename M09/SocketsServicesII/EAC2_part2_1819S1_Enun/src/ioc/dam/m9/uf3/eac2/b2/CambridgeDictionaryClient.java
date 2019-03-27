/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b2;

/**
 *
 * @author Usuari
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;

/**
 *
 * 
 */

//Clase responsable de connectarse a la pagina web
public class CambridgeDictionaryClient {

    private URL url;
    private URLConnection web;
    private InputStream input;
    private String webname = "https://dictionary.cambridge.org/es/diccionario/ingles-catalan/";
    private HtmlParser parser;
    
    public CambridgeDictionaryClient(){};
    
    public String translate(String word) {

        //Implementar la traducció

          try {
              url = new URL("" + webname + "" + word);
              web = url.openConnection();
              input = web.getInputStream();
              parser = new HtmlParser();
              String traduccio= parser.parse(input);
              return traduccio;

          } catch (Exception ex) {
              Logger.getLogger(CambridgeDictionaryClient.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }

      }

}
