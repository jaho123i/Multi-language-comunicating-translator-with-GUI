package zad1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DictionaryServer {
    private String languageCode;
    private Map<String, String> dictionary;

    public DictionaryServer(String languageCode) {
        this.languageCode = languageCode;
        this.dictionary = new HashMap<>();
        // Dodajmy przykładowe tłumaczenia dla demonstracji
        if (languageCode.equals("PL")) {
            dictionary.put("hello", "cześć");
            dictionary.put("cat", "kot");
            dictionary.put("dog", "pies");
        } else if (languageCode.equals("ES")) {
            dictionary.put("hello", "hola");
            dictionary.put("cat", "gato");
            dictionary.put("dog", "perro");
        }
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Dictionary server for language " + languageCode + " is running on port " + serverSocket.getLocalPort());
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    String wordToTranslate = (String) in.readObject();
                    String clientAddress = (String) in.readObject();
                    int clientPort = in.readInt();

                    String translation = dictionary.getOrDefault(wordToTranslate, "Translation not found");

                    System.out.println("Próba wysłania: "+translation+" do: "+clientAddress+" "+clientPort);

                    try (Socket translationSocket = new Socket(clientAddress, clientPort);
                         ObjectOutputStream translationOut = new ObjectOutputStream(translationSocket.getOutputStream())) {
                        translationOut.writeObject(translation);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Podaj argumenty");
            return;
        }

        String languageCode = args[0];
        int port = Integer.parseInt(args[1]);
        DictionaryServer server = new DictionaryServer(languageCode);
        server.start(port);
    }
}
