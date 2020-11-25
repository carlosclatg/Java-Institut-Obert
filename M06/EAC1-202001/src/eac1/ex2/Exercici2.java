package eac1.ex2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Exercici2 {
    private static Contacte contacte;
    private static Brot brot;
    private static JAXBContext jaxbContext;
    private static File fileOutput;
    /**
     * Nom / Telefon / domicili / poblacio / fitxrOrigen / fitxerDesti
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(args);
        try{
            if(args!= null && args.length == 6){

                initXMLInput(args);
                writeXMLOutput(args[5]);

            } else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Error Code 2");
            e.printStackTrace();
        }
    }


    private static void initXMLInput(String[] args) throws Exception {
        //Check file exists
        File file = new File(args[4]);
        if (!file.exists()) throw new Exception("The input file does not exist");

        //Creation of brot
        jaxbContext = JAXBContext.newInstance(Brot.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        brot = (Brot) jaxbUnmarshaller.unmarshal(file);
        //System.out.println(brot.toString());
        //brot.getCadena().stream().forEach(x -> System.out.println(x.toString()));

        //Check non existing phone
        checkNotExistingPhone(brot, args[1]);
        initContact(args);

        //Add new contacte to brot
        brot.addNewContacte(contacte);
    }

    /**
     * Write in an XML in the route pathToFile, if not exists, it is created.
     * @param pathToFile
     * @throws Exception
     */
    private static void writeXMLOutput(String pathToFile) throws Exception{
        jaxbContext = JAXBContext.newInstance(Brot.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        fileOutput = new File(pathToFile);
        marshaller.marshal(brot, fileOutput);
        showBrot(marshaller);
    }

    /**
     * Method responsible for printing out brot.
     * @param marshaller
     * @throws JAXBException
     */
    private static void showBrot(Marshaller marshaller) throws JAXBException {
        marshaller.marshal(brot, System.out);
    }

    /**
     * Check existing phone in the current brot. In case it is found an exception is thrown.
     * @param brot
     * @param phone
     * @throws Exception
     */
    private static void checkNotExistingPhone(Brot brot, String phone) throws Exception {
         if (brot.getCadena().stream().anyMatch(contacte -> contacte.getTelefon().equals(phone))) throw new Exception("Error existing phone");
    }

    private static void initContact(String[] args) {
        contacte = new Contacte();
        contacte.setNom(args[0]);
        contacte.setTelefon(args[1]);
        contacte.setDomicili(args[2]);
        contacte.setPoblacio(args[3]);
        System.out.println(contacte);
    }
}
