package com.lancewer.file;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 13-1-4
 * Time: 下午12:01
 * Description:
 */
public class FileOps {
    public static void main(String[] args) {
        try {
            //create an folder named ParentFolder which is not really exist
            File fp = new File("ParentFolder");
            //now created the folder
            if (fp.mkdir()) {

                //create an text file under ParentFolder which is not really exist also
                File fc = new File(fp, " ChildFile.txt");
                if (fc.createNewFile()) {

                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fc)));
                    for (int i = 0; i < 5; i++) {
                        out.printf("Line %d: This is created by FileOps.java\r\n", i);
                    }
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
