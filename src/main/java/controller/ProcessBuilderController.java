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
    private ProcessModel processModel;

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

        ArrayList<String> localprocessLists = new ArrayList();

        String line;
        int counter = 0;
        String newLine = "";
        try {
            //Mientras existan lineas con datos
            while ((line = br.readLine()) != null) {
                //Verifica que no haya lineas vacias
                if (!line.isEmpty()) {
                    counter++;//Aumenta en uno cada iteración
                    if (counter <= 9) {
                        //Mientras sema menos a 9,  se construye la nueva linea
                        newLine += line + ",";
                    } else {
                        // se reinicia el contador y se limpia la linea, para continuar con el siguiente proceso
                        counter = 0;
                        newLine = "";
                    }
                    if (counter == 9) {
                        //cuando el contador, llega a 9,  se añade la linea al array
                        //el formato del array es  
                        //Nombre de imagen: ,
                        //PID:,
                        //Nombre de sesión:,
                        //Num. de sesió:,
                        //Uso de memoria:,
                        //Estado:,
                        //Nombre de usuario:,
                        //Tiempo CPU:,
                        //Titulo de ventana
                        localprocessLists.add(newLine.replaceAll("\\n", ""));
                    }
                } else {
                    // si hay linea vacia, se limpia la linea y se reinicia el contador
                    counter = 0;
                    newLine = "";
                }
            }

            //el formato del array queda de la siguiente forma 
            //[0] Nombre de imagen, [1],
            //[2]PID,[3],
            //[4]Nombre de sesión,[5],
            //[6]Num. de sesión,[7],
            //[8]Uso de memoria,[9],
            //[10]Estado,[11],
            //[12]Nombre de usuario,[13]
            //[14]Tiempo CPU,[15]
            //[16]Titulo de ventana,[17],
            for (int i = 0; i < localprocessLists.size(); i++) {

                String[] row = localprocessLists.get(i).replace(",", ":").split(":");

                processModel = new ProcessModel();
                processModel.setPid(Integer.parseInt(row[3].trim()));
                processModel.setName(row[1].trim());
                processModel.setUser(row[13].trim());
                processModel.setDescription(row[1].trim());
                processModel.setPriority((row[13].contains("SYSTEM")) ? '1' : '0');
                processModel.setMemory(row[9].trim());
                processModel.setCpu(row[15].trim() + ":" + row[16] + ":" + row[17]);
                processLists.add(processModel);
            }
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
