package homework;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        GameProgress save1 = new GameProgress(100, 1, 3, 1.1);
        GameProgress save2 = new GameProgress(86, 5, 10, 10.3);
        GameProgress save3 = new GameProgress(99, 3, 5, 4.3);

        String saveFiles = "E:\\Games\\savegames\\";
        String[] save = {String.valueOf(save1), String.valueOf(save2), String.valueOf(save3)};
        String[] zip = {"zip1.zip", "zip2.zip", "zip3.zip"};
        String[] zipEntry = {"save1.dat", "save2.dat", "save3.dat"};

        saveGame(zipEntry, save, saveFiles);
        zipFiles(zip, zipEntry, saveFiles);
        delete(zipEntry, saveFiles);
    }

    private static void saveGame(String[] zipEntry, String[] save, String saveFiles) {
        for (int i = 0; i < zipEntry.length; i++) {
            try {
                FileOutputStream fos = new FileOutputStream(saveFiles + zipEntry[i]);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(save[i]);
                System.out.println("Save " + (i + 1));
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void zipFiles(String[] zip, String[] zipEntry, String saveFiles) throws IOException {
        for (int i = 0; i < zip.length; i++) {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(saveFiles + zip[i]));
                 FileInputStream fis = new FileInputStream(saveFiles + zipEntry[i])) {
                ZipEntry entry = new ZipEntry(zipEntry[i]);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        }
    }

    private static void delete(String[] zipEntry, String saveFiles) {
        for (String s : zipEntry) {
            if (true) {
                File del = new File(saveFiles + s);
                del.delete();
            }
        }
        System.out.println("Trash delete");
    }
}