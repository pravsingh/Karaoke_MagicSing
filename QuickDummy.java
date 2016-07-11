package com.hashfold.gre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        File file1 = new File("README.md");

        // if file doesnt exists, then create it
        if (!file1.exists()) {
            file1.createNewFile();
        }

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        bw = new BufferedWriter(fw);

        write("| SEQ | Title, Movie, Singers|");
        write("|---|---|");

        ClassLoader classLoader = App.class.getClassLoader();
        File file = new File(classLoader.getResource("karaoke_magic_sing1.txt").getFile());

        FileInputStream fis = new FileInputStream(file);

        // Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String prevLine = "";

        String line = null;
        while ((line = br.readLine()) != null) {

            char c = line.charAt(0);
            if ((c >= '0') && (c <= '9')) {
                dump(prevLine);
                prevLine = line;
            } else {
                prevLine += " " + line;
            }
        }

        dump(prevLine);

        br.close();

        bw.flush();
        bw.close();
    }

    public static void dump(String line) throws IOException {
        if (line == null || line.length() == 0) {
            return;
        }

        if (line.indexOf("0629") != -1) {
            int i = 0;
            i++;
        }

        // System.out.println(line);
        int i = line.indexOf(' ');
        String youtube = "https://www.youtube.com/results?search_query=" + line.substring(i + 1);
        write("|" + line.substring(0, i) + " | [" + line.substring(i + 1) + "](" + youtube + ")|");
    }

    public static void write(String text) throws IOException {
        bw.write(text + "\n");
    }
}
