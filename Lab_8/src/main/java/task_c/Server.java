package task_c;

import task_b.Management;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public Server(){
        try {
            Management management = new Management();
            Registry registry = LocateRegistry.createRegistry(123);
            registry.rebind("Management", management);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
