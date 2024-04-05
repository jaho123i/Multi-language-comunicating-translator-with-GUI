package zad1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MainServer {
    private static Map<String, String> languageServers = new HashMap<>();

    public static void main(String[] args) {
        languageServers.put("ES","192.168.0.157");

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Main server is running on port " + serverSocket.getLocalPort());

            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    String wordToTranslate = (String) in.readObject();
                    String targetLanguageCode = (String) in.readObject();

                    if (!languageServers.containsKey(targetLanguageCode)) {
                        out.writeObject("Unsupported language code: " + targetLanguageCode);
                        continue;
                    }

                    String serverAddress = languageServers.get(targetLanguageCode);
                    try (Socket serverSocketToDictionary = new Socket(/*serverAddress*/InetAddress.getLocalHost(), 8887);
                         ObjectOutputStream serverOut = new ObjectOutputStream(serverSocketToDictionary.getOutputStream())) {
                        serverOut.writeObject(wordToTranslate);
                        serverOut.writeObject(targetLanguageCode);
                        serverOut.writeObject(clientSocket.getInetAddress().getHostAddress());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
