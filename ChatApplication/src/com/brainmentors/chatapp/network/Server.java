package com.brainmentors.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.brainmentors.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers=new ArrayList<>();  //Contains all the socket
	public Server() throws IOException {
		int PORT=Integer.parseInt( ConfigReader.getValue("PORTNO")) ;
		serverSocket =new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client to join...");
		handleClientRequest();
	}
	//Multiple Client HandShaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket= serverSocket.accept();   //HandShaking
			//Per Client Per Thread
			ServerWorker serverWorker=new ServerWorker(clientSocket, this);  //Creating a New Worker/Thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	 /*Single Client*/
	/*
	public Server() throws IOException {
		int PORT=Integer.parseInt( ConfigReader.getValue("PORTNO")) ;
		serverSocket =new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection...");
		Socket socket= serverSocket.accept();  //Hand Shaking
		System.out.println("Client Joins the Server");
	    InputStream in=socket.getInputStream();  //Read bytes from the network
	    byte arr[]= in.readAllBytes();
	    String str=new String(arr);  //Convert into String
	    System.out.println("Message Receiver from the Client: "+str);
	    in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server=new Server();
	}

}
