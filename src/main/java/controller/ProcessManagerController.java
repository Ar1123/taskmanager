package controller;

import java.util.ArrayList;
import model.ProcessModel;
import utils.EnumProcess;

public class ProcessManagerController {

    private final ArrayList<ProcessModel> processListp = new ArrayList<>();
    private ProcessModel[] vecpr;

    public ProcessManagerController() {
    }
//processList: la lista de procesos, type: el tipo de evento ejecutar

    public ArrayList<ProcessModel> filterProcess(ArrayList<ProcessModel> processList, EnumProcess type) {
        vecpr = new ProcessModel[processList.size()];
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
            case SYSTEMA ->
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
        processListp.sort((ProcessModel p1, ProcessModel p2)
                -> Integer.compare(Integer.parseInt(p1.getMemoryUse().replace("KB", "").replace(".", "").replace(",", "").trim()), Integer.parseInt(p2.getMemoryUse().replace("KB", "").replace(".", "").replace(",", "").trim()))
        );
        System.out.println("menor ram");
    }

    private void MayorCpu() {
//        for (int i = 1; i < vecpr.length; i++) {
//            for (int j = 0; j < (vecpr.length - i); j++) {
//                Time eval1 = Time.valueOf(vecpr[j].getCpu());
//                Time eval2 = Time.valueOf(vecpr[j + 1].getCpu());
//                changePosition(j, j + 1, (eval1.compareTo(eval2) < 0));
//            }
//        }
//
//        processListp2.addAll(Arrays.asList(vecpr));
//        System.out.println("mayor cpu");
//        System.out.println("_________________________________________________");

        System.out.println("mayor cpu");
    }

    private void MenorCpu() {
//        for (int i = 1; i < vecpr.length; i++) {
//            for (int j = 0; j < (vecpr.length - i); j++) {
//                Time eval1 = Time.valueOf(vecpr[j].getCpu());
//                Time eval2 = Time.valueOf(vecpr[j + 1].getCpu());
//                changePosition(j, j + 1, (eval1.compareTo(eval2) > 0));
//            }
//        }
//        processListp2.addAll(Arrays.asList(vecpr));
        System.out.println("menor cpu");
//        System.out.println("_________________________________________________");

    }

    private void usuario() {

//        for (ProcessModel vecpr1 : vecpr) {
//            if (!vecpr1.getUser().contains("SYSTEM")) {
//                System.out.println(vecpr1.getUser());
//                processListp2.add(vecpr1);
//            }
//        }
    }

    private void sistema() {

//        for (ProcessModel vecpr1 : vecpr) {
//            if (vecpr1.getUser().contains("SYSTEM")) {
//                processListp2.add(vecpr1);
//            }
//        }
    }

    private void changePosition(int p1, int p2, boolean condtition) {

        if (condtition) {
            ProcessModel pm;
            pm = vecpr[p1];
            vecpr[p1] = vecpr[p2];
            vecpr[p2] = pm;
        }

    }
}
