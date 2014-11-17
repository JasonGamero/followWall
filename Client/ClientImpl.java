/* This class implements the Interface of network/Client.java
 * 
 **/

package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

import com.jcraft.jsch.Buffer;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;


public class ClientImpl implements Client {
	private EV3 ev3 = (EV3) BrickFinder.getLocal();
	private Keys keys = ev3.getKeys();
	
	private String hostportfile = "./host.txt";
	private String host   = "141.82.48.217";//default
	private int port      = 3143;			//default
	private Socket server = null;
	private Scanner in    = null;
	private PrintWriter out = null;
	private DataInputStream FlowIn;
	private DataOutputStream FlowOut;
	
	public ClientImpl() throws UnknownHostException, IOException {
		initServer();
		Socket SocketClient = new Socket(host, 8000);
		 OutputStream FlowOutgoing = SocketClient.getOutputStream();
		 InputStream FlowIncomming = SocketClient.getInputStream();
	     
	     FlowIn = new DataInputStream(FlowIncomming);
	     FlowOut = new DataOutputStream(FlowOutgoing);
		// using default settings
		// l8r better be read from a file or sth
	}
	
	public ClientImpl(String host, int port) {
		FileReader fr = null;
		try {
			fr = new FileReader(hostportfile);
		} catch (FileNotFoundException e) {
			this.host = host;
			this.port = port;
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			this.host = br.readLine();
			this.port = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String receive(){
		
		byte [] in= new byte [800000000];
		try {
			int numbytes = FlowIn.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String m = new String(in); 
		return m;
		
	}
	public void send(String message){
	
		 try {
			FlowOut.writeBytes(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initScanner() throws IOException{
		this.in = new Scanner( server.getInputStream() );
	}
		
	private void initPrintWriter() throws IOException{
		this.out = new PrintWriter( server.getOutputStream(), true );
	}
	
	private void initServer() throws UnknownHostException, IOException{
		this.server = new Socket(host, port);
	}
	

	/*public void send(String sensordata) {
		// TODO Auto-generated method stub
		try {
//			Socket server = new Socket(host, port);
//			Scanner in = new Scanner( server.getInputStream() );
//			PrintWriter out = new PrintWriter ( server.getOutputStream(), true);
			//initServer();
			initScanner();
			initPrintWriter();
			out.println(sensordata);
			
			//some output for testing
			LCD.drawString(in.nextLine(), 2, 2);
			keys.waitForAnyPress();
		} catch (UnknownHostException e) {
			LCD.drawString("unknown host", 2, 2);
			keys.waitForAnyPress();
		} catch (IOException e) {
			LCD.drawString(e.getMessage(), 1, 1);
			keys.waitForAnyPress();
		}
	}*/


//	public Map getMap() {
		// TODO Auto-generated method stub
		//return null;
	//}

//	@Override
	//public Location getLoc(String robot) {
		// TODO Auto-generated method stub
		//return null;
	//}

	@Override
	public boolean borderComplete() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Map getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLoc(String robot) {
		// TODO Auto-generated method stub
		return null;
	}
	public void Control () {
	
		String mess;
		 do {
			 mess= receive();
			 
		        if (mess.equals("1")){
		        	LCD.drawString("it works!", 2, 2);
		        	//Location();
		        	//set the return values
		        	//FlowOut.writeBytes("Message")
		        }else if (in.equals("2")){
		        	//getMap();
		        	//set the return values
		        	//FlowOut.writeBytes("Message"))
		        }else if (in.equals("3")){
		        	//RutineThree();
		        	//set the return values
		        	//FlowOut.writeBytes("Message")
		        }else if (in.equals("4")){
		        	RutineFour();
		        	//set the return values
		        	//FlowOut.writeBytes("Message")
		        }else {LCD.drawString("no option available", 2, 2);}
		        
		      
		 
	
		 } while (mess.equals("0"));

	}

	private void RutineFour() {
		// TODO Auto-generated method stub
		
	}

	private void RutineThree() {
		// TODO Auto-generated method stub
		
	}

	private void Location() {
		// TODO Auto-generated method stub
		
	}
}
