package icmu.util;

import java.util.UUID;
import java.util.Vector;


public class NodeMessage {
//	static enum MessageType {
//		ACK, SET_FLIGHT_PLAN, RUN_GRAZE, GRAZE_RETURN 
//	};
	MessageType type;
	Vector<String> waypoints;
	Vector<String> grazeInformation;
	String groundStationID;

	public NodeMessage() {
		this.type = MessageType.ACK;
		this.setGroundStationID(UUID.randomUUID().toString());
		this.setWaypoints(null);
		this.grazeInformation = new Vector<String>();
	}

	public NodeMessage(Vector<String> waypoints, Vector<String> grazeInformation, String groundStationID) {
		super();
		this.type = MessageType.ACK;
		this.waypoints = waypoints;
		this.grazeInformation = grazeInformation;
		this.groundStationID = groundStationID;
	}

	public NodeMessage(Vector<String> waypoints, String groundStationID) {
		super();
		this.type = MessageType.SET_FLIGHT_PLAN;
		this.waypoints = waypoints;
		this.grazeInformation = null;
		this.groundStationID = groundStationID;
	}

	public NodeMessage(Vector<String> grazeInformation) {
		super();
		this.type = MessageType.GRAZE_RETURN;
		this.grazeInformation = grazeInformation;
	}

	public Vector<String> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Vector<String> waypoints) {
		this.waypoints = waypoints;
	}

	public Vector<String> getGrazeInformation() {
		return grazeInformation;
	}

	public void setGrazeInformation(Vector<String> grazeInformation) {
		this.grazeInformation = grazeInformation;
	}

	public String getGroundStationID() {
		return groundStationID;
	}

	public void setGroundStationID(String groundStationID) {
		this.groundStationID = groundStationID;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	
}
