package com.lancewer.copyJPG;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 13-1-4
 * Time: 上午8:30
 * Description: This file shows how to copy a jpg file using java io, and compare to InputStream time consumed
 * Result:
 * 1. Without buffered stream: Total time: 4656;
 * 2. Only With bufferedInputStream: Total time: 1773;
 * 3. Use Both BufferedInputStream and BufferedOutputStream : Total time: 15 millisecond;
 * 4. User byte[] as a temp data: Total time: 3 millisecond;
 */
public class CopyJPG {
    static FileInputStream fin = null;
    static FileOutputStream fout = null;

    /**
     * Copy a jpg file only the normal io stream
     * @param sourceLoc
     */
    public static void withoutBuffered(String sourceLoc) {
        try {
            fin = new FileInputStream(sourceLoc);
            fout = new FileOutputStream("copied_without_buff.jpg");
            int read;
            //record start time
            long startTime = System.currentTimeMillis();
            while ((read = fin.read()) != -1) {
                fout.write(read);
            }
            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
            System.out.println("The picture successfully copied!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) fout.close();
                if (fin != null) fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Copy a jpg file with buffered io streams
     * @param sourceLoc
     */
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
//                bfout.flush(); //if flush here will greatly increase the process time!so, do NOT flush here!
            }
            //!important: if miss this line the data will lose a part!!!
            bfout.flush();
            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
            System.out.println("The picture successfully copied!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfin != null) bfin.close();
                if (bfout != null) bfin.close();
                if (fout != null) fout.close();
                if (fin != null) fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Copy a jpg file with buffered io streams, plus add a byte array as tmp data
     * @param sourceLoc
     */
    public static void withBuffered_v2(String sourceLoc) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(sourceLoc)));
            File oFiel = new File("copied_buffer_v2.jpg");
            oFiel.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(oFiel));
            //the code below equals 3 lines code above
//            out =   new BufferedOutputStream(new FileOutputStream("copied_buffer_v2.jpg"));
            byte[] buf;
            int readBytes;

            //record start time
            long startTime = System.currentTimeMillis();

            while ((readBytes = in.available()) > 0) {
                System.out.println(readBytes);
                buf = new byte[readBytes];
                in.read(buf);
                out.write(buf);
            }
            out.flush();

            System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
            System.out.println("The picture successfully copied!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String sourceLoc = "./JavaBasics/jdk1.6/JavaIO/resources/copyJPG/Hydrangeas.jpg";
        withoutBuffered(sourceLoc);
        withBufferd(sourceLoc);
        withBuffered_v2(sourceLoc);
    }
}
