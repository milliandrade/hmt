package icmu.util;

public class Drone {
	enum DroneType {
		READER, TRACKER
	};

	String id;
	DroneType type;
	String name;

	public Drone() {

	}

	public Drone(String Id) {
		this.setId(Id);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public DroneType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DroneType type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
