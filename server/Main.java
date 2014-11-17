package server;

import server.*;

public class Main {
	public static void main(String[] args){
		Server server = new ServerImpl(3143);
		server.run();
		
		server.close();
	}
}