package com.lancewer.copyJPG;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 13-1-4
 * Time: 上午8:30
 * Description: This file shows how to copy a jpg file using java io, and compare to InputStream time consumed
 * Result:
 *   1. Without buffered stream: Total time: 4656;
 *   2. Only With bufferedInputStream: Total time: 1773;
 *   3. Use Both BufferedInputStream and BufferedOutputStream : Total time: 15 millisecond;
 */
public class CopyJPG {
    static FileInputStream fin = null;
    static FileOutputStream fout = null;

    public static void withoutBuffered(String sourceLoc) {
        try {
            fin = new FileInputStream(sourceLoc);
            new BufferedInputStream(fin);
            fout = new FileOutputStream("copied_without_buff.jpg");
            int buf;
            //record start time
            long startTime = System.currentTimeMillis();
            while ((buf = fin.read()) != -1) {
                fout.write(buf);
            }
            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
            System.out.println("The picture successfully copied!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fout != null) fout.close ();
                if(fin != null) fin.close ();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void withBufferd(String sourceLoc) {
        BufferedInputStream bfin = null;
        BufferedOutputStream bfout = null;
        try {
            fin = new FileInputStream(sourceLoc);
            bfin = new BufferedInputStream(fin);
            fout = new FileOutputStream("copied_with_buff.jpg");
            bfout = new BufferedOutputStream(fout);
            int buf;
            //record start time
            long startTime = System.currentTimeMillis();

            while ((buf = bfin.read()) != -1) {
                bfout.write(buf);
            }
            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
            System.out.println("The picture successfully copied!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bfin != null) bfin.close ();
                if(bfout != null) bfin.close ();
                if(fout != null) fout.close ();
                if(fin != null) fin.close ();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String sourceLoc = "./JavaBasics/jdk1.6/JavaIO/resources/copyJPG/Hydrangeas.jpg";
        withoutBuffered(sourceLoc);
        withBufferd(sourceLoc);
    }
}
