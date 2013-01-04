package com.lancewer.readFile;

import java.io.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 13-1-4
 * Time: 上午9:54
 * Description:
 */
public class ReadFile {
    public String getPath() {
        URL url = this.getClass().getResource("/readFile/sourceFile.txt");
        System.out.println(url.getPath());
        return url.getPath();
    }

    public static void main(String[] args) {
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new ReadFile().getPath()));
            bw = new BufferedWriter(new FileWriter("out.txt"));
            PrintWriter out = new PrintWriter(bw);
            String line;
            while ((line = br.readLine()) != null) {
                out.println(line);
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
