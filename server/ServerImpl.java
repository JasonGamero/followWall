/* This class implements the Interface of network/Server.java
 * 
 **/

package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerImpl implements Server {
	private ServerSocket server = null;
	
	public ServerImpl(int port){
		try {
			System.out.println("Initializing");
			server = new ServerSocket(port);
			System.out.println("Done");
		} catch (IOException e) {
			System.err.println("Error by initializing server with port " + port);
			System.err.println("printing StrackTrace: ");
			e.printStackTrace();
			// maybe just taking port += 1
			// until a free port is found or sth
		}
	}
	
	public void run(){
		while(true){
			Socket client = null;
			
			try {
				client = server.accept();
				handleConnection( client );
			} catch ( IOException e ) {
				System.err.println( "Error in ServerImpl " + e.getMessage() );
				e.printStackTrace();
			} finally {
				if ( client != null )
					try {
						client.close(); 
					} catch ( IOException e ) { }
			}
		}
	}
	
	private static void handleConnection( Socket client ) throws IOException {
		System.out.println("new client " + client.toString()); //debug msg
	    Scanner in = new Scanner( client.getInputStream() );
	    PrintWriter out = new PrintWriter( client.getOutputStream(), true );

//	    String input = in.nextLine();
//	    
//	    // some output for testing
//	    System.out.println("Hello client" + client.getInetAddress());
//	    System.out.println("got " + input + "from you");
	    out.println("1");
	}

	@Override
	public void isObstacle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMap(Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendLoc(Location robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		
	}
}
