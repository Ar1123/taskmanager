package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import model.ProcessModel;

public class ProcessBuilderController {

    private ProcessBuilder pb;
    private Process p;
    private BufferedReader br;
    private final ArrayList<ProcessModel> processLists = new ArrayList();
    ProcessModel processModel;

    public ProcessBuilderController() {
    }

    //Se listan los procesos
    private void executeCommand() {
        // Se define el comando a ejecutar
        pb = new ProcessBuilder("tasklist", "/v", "/fo", "list");
        try {
            // se inicia el proceso
            p = pb.start();

        } catch (IOException e) {
            System.out.println("F x tí :( Error ejecutando comando-->" + e);
        }
    }

    private void resultReading() {
        //se obtiene el flujo de stream que retorna el proceso
        InputStream in = p.getInputStream();
        //se instancia el flujo de stream para su lectura
        InputStreamReader isr = new InputStreamReader(in);
        // variable para leer el buffer de datos
        br = new BufferedReader(isr);
    }

    private void orderProcess() {
        String line;
        String newline = "";
        int counter = 0;
        long startTime = System.nanoTime();
        int d = 0;
        try {
            //Mientras existan lineas con datos
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    counter++;
                    d++;
                    newline += line.split(":\\s")[1].trim().replace(",", ".") + ",";
                    System.out.println("<-->");
                    if (counter == 9) {

                        String[] l = newline.split(",");
                        processModel = new ProcessModel();
                        processModel.setProcessName(l[0]);
                        processModel.setPid(l[1]);
                        processModel.setSessionName(l[2]);
                        processModel.setSessionNumber(l[3]);
                        processModel.setMemoryUse(l[4].replace("N/D", "0 KB"));
                        processModel.setState(l[5]);
                        processModel.setUserName(l[6]);
                        processModel.setCpuTime(l[7]);
                        processModel.setWindowTitle(l[8]);
                        processModel.setArrivalTime((d == 9) ? "0" : (System.nanoTime() - startTime) / 1e6+"");
                        processModel.setPriority((l[6].contains("N/D") || l[6].contains("SYSTEM"))?"1":"0");
                        processLists.add(processModel);

                        counter = 0;
                        newline = "";
                    }
                }
            }
            System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        } catch (IOException ex) {
            System.out.println("F x tí :( Error al formar array: " + ex);
        }
    }

    //Se destruye el proceso
    private void destroyProcess() {
        p.destroy();
    }

    public ArrayList<ProcessModel> getProcess() {
        executeCommand();//Ejecutar comando
        resultReading();//Obtener reesultado
        orderProcess();// se crea el vector con el resultado obtenido
        destroyProcess();// se mata el proceso ejecutado
        return processLists;
    }

}
