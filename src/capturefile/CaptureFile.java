/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturefile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author quang
 */
public class CaptureFile {
    private static String dirForScan = "F:\\Games\\Banished";//Nhập đường dẫn
    private static String paths_file = "all_paths.txt";
    ArrayList<String> list = new ArrayList<>();
    WriteFile writeFile = new WriteFile();
    /**
     * @param args the command line arguments
     */
    
    public void list_all_paths(String dirPath) {

        File dir = new File(dirPath);
        File[] first = dir.listFiles();

        if (first != null && first.length > 0) {
            for (File Ffile : first) {
                if (Ffile.isDirectory()) {
                    list_all_paths(Ffile.getAbsolutePath());
                } else {
                    list.add(Ffile.getAbsolutePath());
                }
            }
        }
    }
    
     public void scan() throws IOException {
        writeFile.create_file(paths_file);
        list_all_paths(dirForScan);
        ArrayList<String> full_paths = list;
        writeFile.write_paths_to_file(full_paths, paths_file);
        writeFile.copyCat(paths_file);
    }
     
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        CaptureFile main = new CaptureFile();
        main.scan();
    }
    
}
