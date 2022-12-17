package org.example.rmi;

import org.example.model.Abiturient;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerRmiTask {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(123);
        RMIServer service = new Service();
        registry.rebind("exam", service);
        System.out.println("Server started");
    }

    static class Service extends UnicastRemoteObject implements RMIServer {
        List<Abiturient> abiturients;
        Service() throws RemoteException {
            super();
            abiturients = new ArrayList<Abiturient>() {
                {
                    add(new Abiturient("1", "Anna", "Bocharova",
                            "Shostka", "0998334585", new int[]{10, 10}));
                    add(new Abiturient("2", "Ivan", "Bega",
                            "Kyiv", "0504235886", new int[]{9, 10}));
                    add(new Abiturient("3", "Katya", "Moloday",
                            "Kharkiv", "0665478954", new int[]{10, 10}));
                }
            };
        }

        @Override
        public List<Abiturient> displayBadMarks() {
            List<Abiturient> results = new ArrayList<>();
            for(Abiturient abiturient: abiturients) {
                for (int mark : abiturient.getMarks())
                    if (mark < 10){
                        results.add(abiturient);
                        break;
                    }
            }
            return results;
        }

        @Override
        public List<Abiturient> displayHighSumBiggerThanGiven(int a) {
            List<Abiturient> results = new ArrayList<>();
            for(Abiturient abiturient: abiturients) {
                int sum = 0;
                for (int mark : abiturient.getMarks())
                    sum += mark;
                if(sum > a) {
                    results.add(abiturient);
                }
            }
            return results;
        }

        @Override
        public List<Abiturient> displayNHighestSum(int count) {
            List<Abiturient> temp = abiturients;
            List<Abiturient> results = new ArrayList<>();
            Collections.sort(temp);
            for (int i = 0; i < count; i++)
                results.add(temp.get(temp.size() - 1 - i));
            return results;
        }
    }
}
