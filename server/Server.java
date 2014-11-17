/* This is an Interface for server-side  networking stuff
 * 
 **/

package server;



public interface Server {
	public void isObstacle();			// sends robot, if found object is obstacle or not
	public void run();					// starts the server
	public void close();				// terminates the server
	
	abstract Map getMap();				// gets Map from mapper
	abstract void sendMap(Map map);		// sends Map if client asks for it
	abstract void sendLoc(Location robot);		// sends Location of robot
}
