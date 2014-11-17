/* This is an Interface for client-side  networking stuff
 * 
 * If you need to send data to the server / or other robots? please use this Interface
 *   so you don't have to mess with sockets etc..
 * 
 * * * * * * * * Q U E S T I O N S
 * Do we want communication between robots directly or over the server?
 *   or do we need both?
 * What kind of type has the sensordata?
 * 
 **/

package Client;

import java.io.IOException;

public interface Client {
	void send(String message); // which type has the sensor data ???
	String receive();
	void Control();
	
	Map getMap();				// get current state of map
	boolean borderComplete();	// asks server if everything is done?
								// or just tells the server, yeah i'm done!
	Location getLoc(String robot);	// gives you the location of yourself or another robot
	
	/* unsure if we need those */
	//void send(String message);		// if something goes wrong, tell the server about it



}
