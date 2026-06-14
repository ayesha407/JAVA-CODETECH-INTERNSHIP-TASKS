import java.io.*;
import java.net.*;
import java.util.*;

// Main Server Class
public class ChatServer {

    // List to store connected clients
    static Vector<ClientHandler> clients = new Vector<>();

    // Counter for client IDs
    static int clientNumber = 0;

    public static void main(String[] args) {

        try {

            // Create server socket on port 1234
            ServerSocket serverSocket = new ServerSocket(1234);

            System.out.println("=================================");
            System.out.println("Chat Server Started...");
            System.out.println("Waiting for clients...");
            System.out.println("=================================");

            // Run server continuously
            while (true) {

                // Accept client connection
                Socket socket = serverSocket.accept();

                System.out.println("New client connected!");

                // Create input and output streams
                DataInputStream dis =
                        new DataInputStream(socket.getInputStream());

                DataOutputStream dos =
                        new DataOutputStream(socket.getOutputStream());

                // Assign client name
                String clientName = "Client" + clientNumber;

                // Create client handler object
                ClientHandler clientHandler =
                        new ClientHandler(socket,
                                clientName,
                                dis,
                                dos);

                // Create thread for client
                Thread thread = new Thread(clientHandler);

                // Add client to list
                clients.add(clientHandler);

                // Start thread
                thread.start();

                // Increase client count
                clientNumber++;
            }

        } catch (Exception e) {

            System.out.println("Server Error!");
            e.printStackTrace();
        }
    }
}

// =====================================================
// CLIENT HANDLER CLASS
// Each client runs in separate thread
// =====================================================
class ClientHandler implements Runnable {

    Scanner sc = new Scanner(System.in);

    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket socket;
    boolean isLoggedIn;

    // Constructor
    public ClientHandler(Socket socket,
                         String name,
                         DataInputStream dis,
                         DataOutputStream dos) {

        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.socket = socket;
        this.isLoggedIn = true;
    }

    @Override
    public void run() {

        String received;

        while (true) {

            try {

                // Read message from client
                received = dis.readUTF();

                // Exit condition
                if (received.equalsIgnoreCase("exit")) {

                    this.isLoggedIn = false;
                    this.socket.close();

                    System.out.println(this.name + " disconnected.");

                    break;
                }

                // Broadcast message to all clients
                for (ClientHandler mc : ChatServer.clients) {

                    if (mc.isLoggedIn) {

                        mc.dos.writeUTF(this.name + " : " + received);
                    }
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        try {

            // Close streams
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}