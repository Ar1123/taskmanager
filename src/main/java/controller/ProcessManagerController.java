package controller;

import java.util.ArrayList;
import model.ProcessModel;
import utils.EnumProcess;

public class ProcessManagerController {
    
    private final ArrayList<ProcessModel> processListp = new ArrayList<>();
    
    public ProcessManagerController() {
    }
//processList: la lista de procesos, type: el tipo de evento ejecutar

    public ArrayList<ProcessModel> filterProcess(ArrayList<ProcessModel> processList, EnumProcess type) {
        processListp.clear();
        processListp.addAll(processList);
        switch (type) {
            case MAYOR_CPU ->
                MayorCpu();
            case MENOR_CPU ->
                MenorCpu();
            case MAYOR_RAM ->
                MayorRam();
            case MENOR_RAM ->
                MenorRam();
            case USUARIO ->
                usuario();
            case SISTEMA ->
                sistema();
        }
        return processListp;
    }
    
    private void MayorRam() {
        
        processListp.sort((ProcessModel p1, ProcessModel p2)
                -> Integer.compare(Integer.parseInt(p2.getMemoryUse().replace("KB", "").replace(".", "").replace(",", "").trim()), Integer.parseInt(p1.getMemoryUse().replace("KB", "").replace(".", "").replace(",", "").trim()))
        );
        System.out.println("mayor Ram");
    }
    
    private void MenorRam() {
        processListp.forEach(e -> {
            System.out.println(e.getMemoryUse());
        });
        processListp.sort((ProcessModel p1, ProcessModel p2)
                -> Integer.compare(Integer.parseInt(p1.getMemoryUse().replace("KB", "").replace(".", "").replace(",", "").trim()), Integer.parseInt(p2.getMemoryUse().replace("KB", "").replace(".", "").replace(",", "").trim()))
        );
        System.out.println("menor ram");
    }
    
    private void MayorCpu() {
        processListp.sort((ProcessModel p1, ProcessModel p2)
                -> Long.compare(Integer.parseInt(p2.getCpuTime().replace(":", "").trim()), Integer.parseInt(p1.getCpuTime().replace(":", "").trim()))
        );
        
        System.out.println("mayor cpu");
    }
    
    private void MenorCpu() {
        processListp.sort((ProcessModel p1, ProcessModel p2)
                -> Integer.compare(Integer.parseInt(p1.getCpuTime().replace(":", "").trim()), Integer.parseInt(p2.getCpuTime().replace(":", "").trim()))
        );
        System.out.println("menor cpu");
//        System.out.println("_________________________________________________");

    }
    
    private void usuario() {
        ArrayList<ProcessModel> localprocessListp = new ArrayList<>();
        localprocessListp.addAll(processListp);
        processListp.clear();
        
        for (int i = 0; i < localprocessListp.size(); i++) {
            if (localprocessListp.get(i).getPriority().equalsIgnoreCase("0")) {
                processListp.add(localprocessListp.get(i));
            }
        }
        
    }
    
    private void sistema() {
    ArrayList<ProcessModel> localprocessListp = new ArrayList<>();
        localprocessListp.addAll(processListp);
        processListp.clear();
        
        for (int i = 0; i < localprocessListp.size(); i++) {
            if (localprocessListp.get(i).getPriority().equalsIgnoreCase("1")) {
                processListp.add(localprocessListp.get(i));
            }
        }
    }
}
