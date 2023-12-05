/**
 * 
 */
package icmu.nodes;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import icmu.util.Constants;
import icmu.util.NodeMessage;
import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;

/**
 * @author mille
 *
 */
public class GroundStationThread implements Runnable, NodeConnectionListener {
	private int id;
	private boolean mustFinish;
	private Vector<String> lastGraze;
	private MrUdpNodeConnection connection;
	private UUID uuidLocal;


	public GroundStationThread(int id, MrUdpNodeConnection connection, UUID uuidLocal) {
		super();
		this.id = id;
		this.connection = connection;
		this.uuidLocal = uuidLocal;

		this.mustFinish = false;

		/*
		 * Connect to ContextNet
		 */
		uuidLocal = UUID.randomUUID();
		InetSocketAddress address = new InetSocketAddress(Constants.GATEWAY_IP_ADDRESS,
				Constants.GATEWAY_PORT_NUMBER);
		try {
			connection = new MrUdpNodeConnection(uuidLocal);
			connection.connect(address);
			connection.addNodeConnectionListener(this);
		} catch (IOException e) {
			System.err.println(new Date());
			e.printStackTrace();
		}

		/*
		 * Send passenger position (latitude and longitude)
		 */
		ApplicationMessage message = new ApplicationMessage();
		NodeMessage msg = new NodeMessage();
		msg.setGroundStationID(this.uuidLocal.toString());
		msg.setGrazeInformation(lastGraze);
		message.setContentObject((Serializable) msg);
		try {
			connection.sendMessage(message);
		} catch (IOException e) {
			System.err.println(new Date());
			e.printStackTrace();
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isMustFinish() {
		return mustFinish;
	}

	public void setMustFinish(boolean mustFinish) {
		this.mustFinish = mustFinish;
	}

	public MrUdpNodeConnection getConnection() {
		return connection;
	}

	public void setConnection(MrUdpNodeConnection connection) {
		this.connection = connection;
	}

	public UUID getUuidLocal() {
		return uuidLocal;
	}

	public void setUuidLocal(UUID uuidLocal) {
		this.uuidLocal = uuidLocal;
	}

	@Override
	public void connected(NodeConnection arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnected(NodeConnection arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalException(NodeConnection arg0, Exception arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newMessageReceived(NodeConnection arg0, Message arg1) {
		System.out.println("Message received at " + (new Date()).getTime());
		System.out.println("Message received from: " + arg0.getUuid().toString());
		System.out.println("Message received: " + arg1.getContentObject());

		synchronized (arg1.getContentObject()) {
			NodeMessage msg = (NodeMessage) arg1.getContentObject();
			switch (msg.getType()) {
			// ACK, SET_FLIGHT_PLAN, RUN_GRAZE, GRAZE_RETURN
			case ACK: // ACK
				System.out.println("type: ACK");
				break;
			case SET_FLIGHT_PLAN: // SET_FLIGHT_PLAN
				System.out.println("type: Flight plan");
				this.configureDroneFlightPlan(msg.getWaypoints());
				break;
			case RUN_GRAZE: // RUN_GRAZE
				System.out.println("type: run graze");
				this.runGraze();
				break;
			case GRAZE_RETURN: // GRAZE_RETURN
				System.out.println("type: graze return");
				this.grazeReturn();
				break;
			default:
				break;
			}

		}
		this.mustFinish = true;
	}

	public void configureDroneFlightPlan(Vector<String> flight) {

	}

	public void runGraze() {

	}

	public void grazeReturn() {
	}

	@Override
	public void reconnected(NodeConnection arg0, SocketAddress arg1, boolean arg2, boolean arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsentMessages(NodeConnection arg0, List<Message> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (!this.mustFinish) {
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				System.err.println("\nDate = " + new Date());
				e.printStackTrace();
			}
		}
		System.out.println("Thread " + this.id + " finishing");

	}

	public Vector<String> getLastGraze() {
		return lastGraze;
	}

	public void setLastGraze(Vector<String> lastGraze) {
		this.lastGraze = lastGraze;
	}

}
