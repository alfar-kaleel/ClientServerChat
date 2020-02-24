package com.alfar.server;

import java.io.*;
import java.net.*;
import java.util.Scanner;
 
/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author www.codejava.net
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
 
    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
 
        //Console console = System.console();
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("\nEnter your name: ");
    	
        String userName = scanner.next();
        
        client.setUserName(userName);
        writer.println(userName);
 
        String text;
 
        do {
            //text = console.readLine("[" + userName + "]: ");
        	
        	System.out.println("[" + userName + "]: ");
        	
        	text = scanner.next();
            writer.println(text);
 
        } while (!text.equals("bye"));
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}