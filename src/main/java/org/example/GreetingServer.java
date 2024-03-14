package org.example;
/**
 * A basic Client - Server program that demonstrates how a Server can
 * create a ServerSocket that allows a client to connect.
 * Once connected, the client sends a greeting message to the server,
 * and the server responds by sending a reply to the client.
 * All messages travel via the socket.
 *
 * Note in particular the use of the .accept() and .readLine() methods which
 * are Blocking IO methods.  This means that they pause the execution of the
 * program at this point, and wait for something to happen before proceeding.
 * (e.g. wait for a client to connect, or wait for a message to arrive
 * (followed by a '\n' character.)
 *
 * Run the GreetingServer first, and then run the Client.
 * Study the two output panes (GreetingServer and Client) for results.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer {

    final int PORT_NUMBER = 8888;  // could be any port from 1024 to 49151 (that doesn't clash with other Apps)

    public static void main(String[] args) {
        GreetingServer server = new GreetingServer();
        server.start();
    }

    public void start() {

        // Create a ServerSocket that will allow a client to connect to this server.
        // The .accept() method is a blocking method that waits for a client to connect
        // to the server.  When a client connects, a new socket is created (clientSocket)
        // and this socket is used to communicate with that specific client.

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
             Socket clientSocket = serverSocket.accept();       // start server to listen on port number 8888
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("The GreetingServer server has started and is waiting for a client to connect.");

            String message = in.readLine(); // read the incoming message - terminated by a newline ("\n")
            // program stops and waits here until input (followed by '\n') arrives.

            System.out.println("The GreetingServer has received this message from a client: " + message);
            System.out.println("The server is replying to the client.");

            if ("hello server".equals(message)) {
                out.println("hello client");    // send response back to client
            } else {
                out.println("unrecognised greeting");
            }

            System.out.println("The GreetingServer is finished, and is exiting. Goodbye!");

        } catch (IOException exception) {
            System.out.println(exception);
        }
        // the try-with-resources style of exception handling used above is preferred
        // as it will automatically close all AutoClosable resources such as
        // the server socket and streams.
    }

    //TODO Exercise
    // Amend the code above so that the server will also deal with a greeting in another language
    // and will respond with a return greeting in that language.
    // In the server you need to add another branch to the if/else to deal with message "bonjour server"
    // and send the response "bonjour client".
    // You will have to modify the Client program to send the greeting "bonjour server" in order
    // to test your program.
    // Remember to run server before client, as client has to connect to server.
    // Your server will now be able to respond to two different greetings.

}