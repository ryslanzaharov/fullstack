package server;

import java.rmi.Naming;

public class ChatServerDriver {

    public static void main(String[] args) throws Exception {
        Naming.rebind("RMIChatServer", new ChatServer());
    }
}
