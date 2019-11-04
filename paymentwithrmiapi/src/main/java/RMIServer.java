import repository.PaymentServiceImpl;
import service.PaymentService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {

    public static void main(String args[]) throws Exception {
        PaymentServiceImpl obj = new PaymentServiceImpl();
        PaymentService stub = (PaymentService) UnicastRemoteObject.exportObject(obj, 5000);
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("PaymentService", stub);
    }

}
