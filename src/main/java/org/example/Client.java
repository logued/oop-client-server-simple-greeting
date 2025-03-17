package org.example;        // March 2025
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    final static int PORT_NUMBER_ON_SERVER = 8888; // port number set in the server to listen for connection requests

    public static void main(String[] args) {
        Client client = new Client();
        client.start(); // start client
    }

    public void start() {

        try (   // attempt to connect to the server (running on localhost) on the specified port number
                // If the connection is successful, a new Socket will be created.
                Socket socket = new Socket("localhost", PORT_NUMBER_ON_SERVER);
        ) {
            // get the socket's output stream
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream, true);
            System.out.println("The client is running and has connected to the server.");
            System.out.println("The client is sending the message \"hello server\" to the server, and will await a response.");

            socketWriter.println("hello server");    // send greeting message to server via socket

            // Next, wait for response from the server, and when it arrives, deal with it.

            // - get the input stream from the socket, and wrap in a Reader
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = socketReader.readLine();    // wait, and receive a return message from the server
            // execution of this code stops at readline(), which waits for input from teh server.
            // The input stream of characters MUST be terminated by a newline ( \n )

            System.out.println("In client: The server response was : " + response);
            System.out.println("Finished! - client is exiting.");

        } catch (UnknownHostException e) {
            System.out.println(e.toString());  // print out the exception
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}


