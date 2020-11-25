package eac1.ex1;

import java.io.File;
import java.io.FileFilter;

public class Exercici1 {

    private static File file;
    private static FileFilter fileFilter;
    private static Integer depthNumber;

    public static void main(String[] args) {

        try{
            if(args != null && args.length == 2){
                checkAndInitPath(args[0]);
                checkAndInitDepth(args[1]);
                showAndFilterFiles();
            } else {
                throw new Exception("Error code 2: Not correct parameters");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    private static void showAndFilterFiles() {
        fileFilter = (file) -> {
            if(file.canRead() && !file.isHidden()){
                return true;
            }
            return false;
        };
        recursivePrint(file.listFiles(fileFilter),0,0);

    }

    /**
     * Recursive function for printing file tree
     * @param fileArray Array of File to print.
     * @param currentIndex current index of iteration in fileArray. Initially 0.
     * @param currentDepth current depth of the next element to show.
     */
    private static void recursivePrint(File[] fileArray, int currentIndex, int currentDepth) {
        //Prevent IOB Exception
        if(currentIndex == fileArray.length)
            return;

        //Left line of the tree
        System.out.print("|");

        //Tabs
        for (int i = 0; i < currentDepth; i++)
            System.out.print("\t");

        //case File
        if(fileArray[currentIndex].isFile())
            if(currentDepth != 0) System.out.println("|-- " + fileArray[currentIndex].getName());
            else System.out.println("-- " + fileArray[currentIndex].getName());

        //case Directory
        else if(fileArray[currentIndex].isDirectory()) {
            if(currentDepth==0)System.out.println("--[" + fileArray[currentIndex].getName() + "]");
            else System.out.println("|--[" + fileArray[currentIndex].getName() + "]");
            if(depthNumber == -1 || currentDepth < depthNumber) {
                recursivePrint(fileArray[currentIndex].listFiles(), 0, currentDepth + 1);
            }
        }

        recursivePrint(fileArray,++currentIndex, currentDepth);
    }


    private static void checkAndInitDepth(String depth) throws Exception {

        if(depth.equals("T") || depth.equals("t")) {
            depthNumber = -1;
            return;
        }
        try{
            depthNumber = Integer.parseInt(depth);
            if(depthNumber <= 0) throw new NumberFormatException();
        } catch (NumberFormatException n){ //in case of Converting String to Integer Exception
            throw new Exception("Error Code 2: not a valid number, it must be greater than 0 or either T");
        }

    }

    private static void checkAndInitPath(String path) throws Exception {
        System.out.println("ROOT FOLDER: " + path);
        file = new File(path); //init File.
        if (!file.exists() || !file.isDirectory()) throw new Exception("Error code 2: Not a folder");
    }
}
