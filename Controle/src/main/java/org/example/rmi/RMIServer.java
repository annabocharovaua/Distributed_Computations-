package org.example.rmi;

import org.example.model.Abiturient;
import java.rmi.Remote;
import java.util.List;

interface RMIServer extends Remote {
    List<Abiturient> displayBadMarks();
    List<Abiturient> displayHighSumBiggerThanGiven(int a);
    List<Abiturient> displayNHighestSum(int count);
}