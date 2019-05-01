/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Hospital;

/**
 *
 * @author professor
 */
public class Utilitats {

    static JAXBContext jaxbContext;
    static Marshaller jaxbMarshaller;
    static Unmarshaller jaxbUnmarshaller;    

    static  {
        try {
            jaxbContext = JAXBContext.newInstance(Hospital.class);
            jaxbMarshaller = jaxbContext.createMarshaller();    
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
    }
  


    
    
/**
 * Obte la representacio XML d'un Hospital en forma d'String.
 * S'assumeix que la representacio es correcta.
 * @param hospital hospital a representar en XML
 * @return representacio XML del hospital indicat pel parametre
 * @throws gestors.GestorException si es produeix algun error en la conversio
 */
    
    public static String formaHospitalXML(Hospital hospital) throws GestorException {
        
        try {
            StringWriter sw = new StringWriter();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
           
            jaxbMarshaller.marshal(hospital, sw);
            
            return sw.toString();
            
        } catch (JAXBException ex) {
            throw new GestorException(ex.getMessage());
        }

    }
 /**
  * Obte un objecte Hospital a partir de la seva representacio en XML continguda en un String.
  * @param dades representacio XML d'un Hospital
  * @return objecte dades corresponent a la representacio XML continguda al parametre
  * @throws gestors.GestorException si es produeix algun error en la conversio
  */   
    public static Hospital obteHospital(String dades) throws GestorException {
        
        try {
            
            StringReader reader = new StringReader(dades);
            jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            return (Hospital) jaxbUnmarshaller.unmarshal(reader);
            
        } catch (JAXBException ex) {
            throw new GestorException(ex.getMessage());
        }
    
    }

}
