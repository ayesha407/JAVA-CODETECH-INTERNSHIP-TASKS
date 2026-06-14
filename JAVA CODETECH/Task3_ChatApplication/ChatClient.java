import java.io.*;
import java.net.*;
import java.util.Scanner;

// Main Client Class
public class ChatClient {
    public static void main(String[] args) {

        try {

            // Connect to server
            Socket socket = new Socket("localhost", 1234);

            System.out.println("Connected to Chat Server!");

            // Create input and output streams
            DataInputStream dis =
                    new DataInputStream(socket.getInputStream());

            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());

            // Scanner for user input
            Scanner sc = new Scanner(System.in);

            // =================================================
            // THREAD FOR SENDING MESSAGES
            // =================================================
            Thread sendMessage = new Thread(() -> {

                while (true) {

                    try {

                        // Read message from keyboard
                        String msg = sc.nextLine();

                        // Send message to server
                        dos.writeUTF(msg);

                        // Exit condition
                        if (msg.equalsIgnoreCase("exit")) {

                            socket.close();
                            break;
                        }

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            });

            // =================================================
            // THREAD FOR READING MESSAGES
            // =================================================
            Thread readMessage = new Thread(() -> {

                while (true) {

                    try {

                        // Read message from server
                        String msg = dis.readUTF();

                        // Display message
                        System.out.println(msg);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            });

            // Start both threads
            sendMessage.start();
            readMessage.start();

        } catch (Exception e) {

            System.out.println("Client Error!");
            e.printStackTrace();
        }
    }
}