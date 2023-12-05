package icmu.rastreamento.processnode;

public class FarmMessage {
	String[] ids;
	String groundStationID;
	
	public FarmMessage() {}
	
	public FarmMessage(String[] msg, String uid) {
		this.setGroundStationID(uid);
		this.setIds(msg);
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getGroundStationID() {
		return groundStationID;
	}

	public void setGroundStationID(String groundStationID) {
		this.groundStationID = groundStationID;
	}
	
	
}
