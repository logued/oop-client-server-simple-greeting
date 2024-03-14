package org.example;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.start(8888); // start client to connect to the port number that the server uses
    }

    public void start(int portNumber) {

        try (   // create socket in client to connect to the server
                Socket socket = new Socket("localhost", 8888);
                // get the socket's output stream
                OutputStream outputStream = socket.getOutputStream();
        ) {
            PrintWriter out = new PrintWriter(outputStream, true);
            System.out.println("The client is running and has connected to the server.");
            System.out.println("The client is sending teh message \"hello server\" to the server, and will await a response.");

            out.println("hello server");    // send greeting message to server via socket

            // Next, deal with the response from the server:
            // - get the input stream from the socket, and wrap in a reader
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = in.readLine();    // wait, and receive a return message from the server
            System.out.println("In client: The server response was : " + response);
            System.out.println("Finished! - client is exiting.");

        } catch (UnknownHostException e) {
            System.out.println(e);  // print out the exception
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}


