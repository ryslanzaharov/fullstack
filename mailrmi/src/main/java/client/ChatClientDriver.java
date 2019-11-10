package client;

import server.ChatServerIF;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClientDriver {

    public static void main(String[] args) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        ChatServerIF stub = (ChatServerIF) registry.lookup("RMIChatServer");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        new Thread(new ChatClient(name, stub)).start();
    }
}
