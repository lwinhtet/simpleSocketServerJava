package echoserver.example.com;

import java.net.*;
import java.io.*;

/*
while you can use sockets directly for communication in applications like chat servers and
multiplayer games, in real-world scenarios, you often use higher-level frameworks and libraries
that abstract the complexities of socket management and provide additional features for scalability,
security, and ease of development.
*/

/* By default, the ServerSocket binds to the local machine's loopback address, which is
localhost (127.0.0.1). */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Server Running...");
        System.out.println("portNumber is "+ portNumber);
        try (
                /* This line creates a ServerSocket object that listens for incoming connections
                on the specified port number (provided as a command-line argument). The ServerSocket
                is responsible for waiting for client connection requests. */
                ServerSocket serverSocket =
                        new ServerSocket(Integer.parseInt(args[0]));
                /* This line waits (blocks) until a client connects to the server on the specified
                port. Once a connection is established, it returns a Socket object to communicate
                with the client. */
                Socket clientSocket = serverSocket.accept();
                /* These lines set up input and output streams to read from and write to the client.
                PrintWriter is used to send data to the client, and BufferedReader is used to read
                data from the client. */
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Processing Request..");
            String inputLine;

            /* This readLine() call will block if there is no data available to read, meaning it
            waits for the client to send data. */
            while ((inputLine = in.readLine()) != null) {
                /* This block of code reads data from the client line by line. For each line received,
                it sends the same line back to the client, effectively creating an echo server. */
                out.println(inputLine);
            }





        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                                       + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}