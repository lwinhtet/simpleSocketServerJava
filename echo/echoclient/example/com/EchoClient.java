package echoclient.example.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>"
            );
            System.exit(1);
        }

        String hostName = args[0];
        int port = Integer.parseInt(args[1]);

        try (
                /* Initializes multiple resources that implement the AutoCloseable interface,
                ensuring they will be automatically closed at the end of the try block.
                */
                /* Socket: Represents a client socket that connects to a server socket at the specified
                hostname and port.
                The Socket is initialized to establish a network connection to the server. */
                Socket echoSocket = new Socket(hostName, port);
                /* initializes a PrintWriter that sends its output to the OutputStream associated with echoSocket.
                echoSocket.getOutputStream() retrieves the output stream associated with the Socket object echoSocket.
                OutputStream is an abstract class in Java that serves as the superclass of all classes representing
                an output stream of bytes.
                Once out is initialized, you can use it to send data (text) to the server through the socket. */
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                /* BufferedReader: Used to read text from the server through the socket's input stream.
                Initialization: The BufferedReader is initialized with an InputStreamReader that wraps
                the socket's input stream. */
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                /* BufferedReader: Used to read text from the standard input (e.g., the console).
                Initialization: The BufferedReader is initialized with an InputStreamReader that
                wraps the standard input stream. */
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                ) {
            /* The purpose of the code is to read user input from the console, send it to the server,
            and then print the server's response back to the console. */
            /* userInput: This line declares a variable userInput of type String. It will hold the input read
            from the console. */
            String userInput;
            /* stdIn.readLine(): Reads a line of text from the standard input (the console).
            It blocks and waits for the user to type something and press Enter. The loop reads a line
            at a time from the standard input stream with the BufferedReader object stdIn. The loop
            then immediately sends the line to the server by writing it to the PrintWriter connected to the socket */
            /* != null: Checks if the line read from the console is not null. If stdIn.readLine()
            returns null, it means the end of the input stream has been reached (this usually happens
            if the input stream is closed). */
            while ((userInput = stdIn.readLine()) != null) {
                /* out.println(userInput): Sends the userInput string to the server. The println method
                adds a newline character to the end of the string and flushes the stream, ensuring that
                the data is sent immediately. */
                out.println(userInput);
                /* in.readLine(): reads a line of information from the BufferedReader connected to
                the socket. The readLine method waits until the server echoes the information back to
                EchoClient. When readline returns, EchoClient prints the information to the standard output. */
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            /* Terminate the Program: Use it when you need to stop all running threads and exit
            the program immediately.
            Exit with Status Code: Provide a meaningful exit status to indicate the reason for termination.
            For example, 0 for normal termination and non-zero values for errors or abnormal termination. */
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error in communication: " + e.getMessage());
            System.exit(1);
        }
    }
}

