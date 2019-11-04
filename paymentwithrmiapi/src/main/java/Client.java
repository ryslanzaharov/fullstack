import model.Payment;
import service.PaymentService;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry();
        PaymentService stub = (PaymentService) registry.lookup("PaymentService");
        stub.create(new Payment(new BigDecimal(1241)));
        stub.findAll().forEach(e -> System.out.println(e.getValue()));
    }
}
