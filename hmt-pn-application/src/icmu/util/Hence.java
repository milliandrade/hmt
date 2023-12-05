package icmu.util;

public class Hence {
	int id;
	String name;
	double minX;
	double maxX;
	double minY;
	double maxY;

	public Hence(double x1, double x2, double y1, double y2) {
		this.setName("virtual hence");
		this.setMinX(x1);
		this.setMaxX(x2);
		this.setMinY(y1);
		this.setMaxY(y2);
	}

	public Hence(String name, double x1, double x2, double y1, double y2) {
		this.setName(name);
		this.setMinX(x1);
		this.setMaxX(x2);
		this.setMinY(y1);
		this.setMaxY(y2);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the minX
	 */
	public double getMinX() {
		return minX;
	}

	/**
	 * @param minX the minX to set
	 */
	public void setMinX(double minX) {
		this.minX = minX;
	}

	/**
	 * @return the maxX
	 */
	public double getMaxX() {
		return maxX;
	}

	/**
	 * @param maxX the maxX to set
	 */
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	/**
	 * @return the minY
	 */
	public double getMinY() {
		return minY;
	}

	/**
	 * @param minY the minY to set
	 */
	public void setMinY(double minY) {
		this.minY = minY;
	}

	/**
	 * @return the maxY
	 */
	public double getMaxY() {
		return maxY;
	}

	/**
	 * @param maxY the maxY to set
	 */
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public String saveNewHence() {
		String bdInsert = "";
		// (hence_id, hence_name, map_minX, map_minY, map_maxX, map_maxY)

		bdInsert = "INSERT INTO hence (hence_name, map_minX, map_minY, map_maxX, map_maxY) VALUES(";
		bdInsert = bdInsert + " \'" + this.getName() + "\', ";
		bdInsert = bdInsert + " \'" + this.getMinX() + "\', ";
		bdInsert = bdInsert + " \'" + this.getMinY() + "\', ";
		bdInsert = bdInsert + " \'" + this.getMaxX() + "\', ";
		bdInsert = bdInsert + " \'" + this.getMaxY() + "\');  ";

		return bdInsert;
	}

	public String updateGraze() {
		String bdInsert = "UPDATE hence SET ";
		// (id, date, start_time, end_time, hence_id, alert)
		bdInsert = bdInsert + " hence_name = \'" + this.getName() + "\', ";
		bdInsert = bdInsert + " map_minX = \'" + this.getMinX() + "\', ";
		bdInsert = bdInsert + " map_minY = \'" + this.getMinY() + "\', ";
		bdInsert = bdInsert + " map_maxX = \'" + this.getMaxX() + "\', ";
		bdInsert = bdInsert + " map_maxY = \'" + this.getMaxY() + "\')  ";
		if (this.getId() > 0) {
			bdInsert = bdInsert + " WHERE id = \'" + this.getId() + "\';";
		} else {
			bdInsert = bdInsert + " WHERE name = \'" + this.getName() + "\';";
		}
		return bdInsert;
	}

}
