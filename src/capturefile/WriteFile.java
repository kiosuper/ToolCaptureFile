/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturefile;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author quang
 */
public class WriteFile {

    public void write_paths_to_file(ArrayList<String> paths, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        PrintWriter pw = new PrintWriter(writer);
        for (String str : paths) {
            pw.println(str);
        }
        writer.close();
    }

    public void create_file(String fileName) throws IOException {
        File f;
        f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
    }
    
    public void copyFile(File source, File dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
    
    public ArrayList read_paths_from_file(String fileName) throws FileNotFoundException, IOException{
        ArrayList<String> paths = new ArrayList();
        File file = new File(fileName); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String st; 
        while ((st = br.readLine()) != null){
            paths.add(st);
        } 
        return paths;
    }
    
    public void copyCat(String fileName) throws IOException{
        ArrayList<String> paths = new ArrayList();
        String sub_path = "copy_File\\";
        new File(sub_path).mkdir();
        String[] nameOfFile;
        paths = read_paths_from_file(fileName);
        String regex = "\\\\";
        for(int i=0; i< paths.size(); i++){
            nameOfFile = paths.get(i).split(regex);
            File fileSource = new File(paths.get(i));
            File fileDest = new File(sub_path + "copy_" + nameOfFile[nameOfFile.length-1]);
            copyFile(fileSource, fileDest);
        }
    }
}