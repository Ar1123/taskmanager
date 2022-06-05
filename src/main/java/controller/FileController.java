package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FileController {

    private final String urlBase = "C://Users//Laptop Asus M415DA//Desktop//Universidad//semestre 8//S.O//taller procesos//files";
    private String urlFinal;

    public FileController() {
    }

    public void createFolder(String nameFolder) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        File directory = new File(urlBase + "//" + nameFolder + "//" + dtf.format(LocalDateTime.now()).replace(":", "").replace("/", ""));
        urlFinal = urlBase + "//" + nameFolder + "//" + dtf.format(LocalDateTime.now()).replace(":", "").replace("/", "");
        System.out.println("url" + urlFinal);
        System.out.println("path" + directory.getAbsolutePath());
        directory.mkdirs();
    }

    public void createFile(String nameFile, String body) {
        System.out.println("-->"+ urlFinal);

        try {
            FileWriter file = new FileWriter(urlFinal + "//" + nameFile + ".json");
            file.write(body);
            file.flush();
            file.close();
        } catch (Exception e) {

            System.out.println(e);
        } finally {
        }
    }

}
