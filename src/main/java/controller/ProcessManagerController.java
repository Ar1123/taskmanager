package controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import model.ProcessModel;

public class ProcessManagerController {

    private final ArrayList<ProcessModel> processListp2 = new ArrayList<>();
    private ProcessModel[] vecpr;

    public ProcessManagerController() {
    }
//processList: la lista de procesos, type: el tipo de evento ejecutar

    public ArrayList<ProcessModel> filterProcess(ArrayList<ProcessModel> processList, int type) {
        vecpr = new ProcessModel[processList.size()];
        processListp2.clear();
        for (int i = 0; i < processList.size(); i++) {
            vecpr[i] = processList.get(i);
        }
        switch (type) {
            case 1 -> MayorCpu();
            case 2 -> MenorCpu();
            case 3 -> MayorRam();
            case 4 -> MenorRam();
            case 5 -> sistema();
            case 6 -> usuario();

        }
   

        return processListp2;
    }

    private void MayorRam() {
        for (int i = 1; i < vecpr.length; i++) {
            for (int j = 0; j < (vecpr.length - i); j++) {
                float eval1 = Float.parseFloat(vecpr[j].getMemory().replace("KB", "").trim().replace(".", ""));
                float eval2 = Float.parseFloat(vecpr[j + 1].getMemory().replace("KB", "").trim().replace(".", ""));

                changePosition(j, j + 1, (eval1 > eval2));
            }
        }
        processListp2.addAll(Arrays.asList(vecpr));
    }

    private void MenorRam() {

        for (int i = 1; i < vecpr.length; i++) {
            for (int j = 0; j < (vecpr.length - i); j++) {
                float eval1 = Float.parseFloat(vecpr[j].getMemory().replace("KB", "").trim().replace(".", ""));
                float eval2 = Float.parseFloat(vecpr[j + 1].getMemory().replace("KB", "").trim().replace(".", ""));

                changePosition(j, j + 1, (eval1 < eval2));
            }
        }
        processListp2.addAll(Arrays.asList(vecpr));
    }

    private void MayorCpu() {
        for (int i = 1; i < vecpr.length; i++) {
            for (int j = 0; j < (vecpr.length - i); j++) {
                Time eval1 = Time.valueOf(vecpr[j].getCpu());
                Time eval2 = Time.valueOf(vecpr[j + 1].getCpu());
                changePosition(j, j + 1, (eval1.compareTo(eval2) < 0));
            }
        }

        processListp2.addAll(Arrays.asList(vecpr));
        System.out.println("mayor cpu");
        System.out.println("_________________________________________________");

    }

    private void MenorCpu() {
        for (int i = 1; i < vecpr.length; i++) {
            for (int j = 0; j < (vecpr.length - i); j++) {
                Time eval1 = Time.valueOf(vecpr[j].getCpu());
                Time eval2 = Time.valueOf(vecpr[j + 1].getCpu());
                changePosition(j, j + 1, (eval1.compareTo(eval2) > 0));
            }
        }
        processListp2.addAll(Arrays.asList(vecpr));
        System.out.println("menor cpu");
        System.out.println("_________________________________________________");

    }

    private void usuario() {

        for (ProcessModel vecpr1 : vecpr) {
            if (!vecpr1.getUser().contains("SYSTEM")) {
                System.out.println(vecpr1.getUser());
                processListp2.add(vecpr1);
            }
        }
    }

    private void sistema() {

        for (ProcessModel vecpr1 : vecpr) {
            if (vecpr1.getUser().contains("SYSTEM")) {
                processListp2.add(vecpr1);
            }
        }
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
