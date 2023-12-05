package icmu.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class Graze {
	String id;
	LocalDateTime start;
	LocalDateTime end;
	int hence_id;
	Vector<String> presentIDs;
	Vector<String> absentIDs;
	boolean status; // 1 - OK; 0 - ABSENT OX

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Vector<String> getPresentIDs() {
		return presentIDs;
	}

	public void setPresentIDs(Vector<String> presentIDs) {
		this.presentIDs = presentIDs;
	}

	public Vector<String> getAbsentIDs() {
		return absentIDs;
	}

	public void setAbsentIDs(Vector<String> absentIDs) {
		this.absentIDs = absentIDs;
	}

	public int getHence_id() {
		return hence_id;
	}

	public void setHence_id(int hence_id) {
		this.hence_id = hence_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Graze(LocalDateTime start, LocalDateTime end, Vector<String> presentIDs, Vector<String> absentIDs) {
		super();
		this.start = start;
		this.end = end;
		this.presentIDs = presentIDs;
		this.absentIDs = absentIDs;
		this.hence_id = Constants.DEFAULT_HENCE;
	}

	public Graze() {
		this.setStart(LocalDateTime.now());
		this.id = UUID.randomUUID().toString();
//		this.setEnd(LocalDateTime.now());
		this.presentIDs = new Vector<String>();
		this.absentIDs = new Vector<String>();
		this.hence_id = Constants.DEFAULT_HENCE;
	}

	public boolean insertPresentID(String id) {
		boolean inseriu = false;
		if (!this.hasPresentID(id)) {
			this.presentIDs.add(id);
			inseriu = true;
		}
		return inseriu;
	}

	public static void main(String[] args) {
		Graze g = new Graze();
		g.insertPresentID("68e2dada-a0e5-41c8-8e39-cd8e0f519a7c");
		g.insertPresentID("555d6901-1382-49af-963c-554240f3999e");
		g.insertPresentID("3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7");
		g.insertPresentID("68e2dada-a0e5-41c8-8e39-cd8e0f519a7c");
		g.insertPresentID("68e2dada-a0e5-41c8-8e39-cd8e0f519a7c");
		g.insertPresentID("68e2dada-a0e5-41c8-8e39-cd8e0f519a7c");
		
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		g.insertAbsentId("72cc84f6-89c8-48b6-ac58-56df1d565cd3");
		
		System.out.println("presentes:: " + g.getPresentIDs().size());
		System.out.println("ausentes:: " + g.getAbsentIDs().size());
		System.out.println(g.toString());
		
	}

	public boolean hasPresentID(String id) {
		boolean present = false;
		for (int i = 0; i < this.presentIDs.size() ; i++) {
			if (this.getPresentIDs().get(i).equalsIgnoreCase(id)) {
				present = true;
			}
		}
		return present;
	}

	public boolean hasAbsentID(String id) {
		boolean present = false;
		for (int i = 0; i < this.absentIDs.size(); i++) {
			if (this.absentIDs.get(i).equalsIgnoreCase(id)) {
				present = true;
			}
		}
		return present;
	}
	
	public void insertAbsentId(String id) {
		if (!this.hasAbsentID(id)) {
			this.absentIDs.add(id);
		}
	}

	public void removePresentID(String id) {
		if (this.hasPresentID(id)) {
			this.presentIDs.remove(id);
		}
	}
	
	public void removeAbsentID(String id) {
		if (this.hasAbsentID(id)) {
			this.absentIDs.remove(id);
		}
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		if (this.end != null) {
			return "Graze [date=" + dtf.format(this.start) + " to " + dtf.format(this.end) + ", presentIDs="
					+ presentIDs + ", absentIDs=" + absentIDs + "]";
		} else {
			return "Graze [date=" + dtf.format(this.start) + " to ... , presentIDs=" + presentIDs + ", absentIDs="
					+ absentIDs + "]";
		}
	}

	public String onlyDateToString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dtf.format(this.getStart());
	}

	public boolean isStatus() {
		if (this.getAbsentIDs() != null && this.getAbsentIDs().size() > 0) {
			this.setStatus(false);
		} else {
			this.setStatus(true);
		}
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String saveNewGraze() {
		String bdInsert = "";
		// (id, date, start_time, end_time, hence_id, alert)
		if (this.getEnd() != null) {
			bdInsert = "INSERT INTO `graze` (id, date, start_time, end_time, hence_id, alert, time) VALUES(";

		} else {
			bdInsert = "INSERT INTO `graze` (id, date, start_time, hence_id, alert, time) VALUES(";
		}

		bdInsert = bdInsert + "\'" + this.getId() + "\', ";
		bdInsert = bdInsert + "\'" + this.getStart().getYear() + "-" + this.getStart().getMonthValue() + "-"
				+ this.getStart().getDayOfMonth() + "\', ";
		bdInsert = bdInsert + "\'" + this.getStart().getHour() + ":" + this.getStart().getMinute() + "\', ";
		if (this.getEnd() != null) {
			bdInsert = bdInsert + "\'" + this.getEnd().getHour() + ":" + this.getEnd().getMinute() + "\', ";
		}
		bdInsert = bdInsert + "\'" + this.getHence_id() + "\', ";
		if (this.isStatus()) {
			bdInsert = bdInsert + "\'1\', ";
		} else {
			bdInsert = bdInsert + "\'0\', ";
		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		bdInsert = bdInsert + "\'" + time + "\');";

		return bdInsert;
	}

	public String updateGraze() {
		String bdInsert = "UPDATE `graze` SET ";
		// (id, date, start_time, end_time, hence_id, alert)
		bdInsert = bdInsert + "`date` = \'" + this.getStart().getYear() + "-" + this.getStart().getMonthValue() + "-"
				+ this.getStart().getDayOfMonth() + "\', ";
		bdInsert = bdInsert + " `start_time` = \'" + this.getStart().getHour() + ":" + this.getStart().getMinute()
				+ "\', ";
		if (this.getEnd() != null) {
			bdInsert = bdInsert + " `end_time` = \'" + this.getEnd().getHour() + ":" + this.getEnd().getMinute() + "\', ";
		}
//		bdInsert = bdInsert + " hence_id = \'" + this.getHence_id() + "\', ";
		if (this.isStatus()) {
			bdInsert = bdInsert + " `alert` = \'1\',";
		} else {
			bdInsert = bdInsert + " `alert` = \'0\',";
		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		bdInsert = bdInsert + " `time` = \'" + time + "\'";
		bdInsert = bdInsert + " WHERE `id` = \'" + this.getId() + "\';";
		return bdInsert;
	}

	public String insertAbsentsToBD() {
		String bdInsert = "";
//		TABLE `oxen_in_graze` (id, graze_id, ox_id, is_present)
		if (this.getAbsentIDs() != null && this.getAbsentIDs().size() > 0) {
			for (int i = 0; i < this.getAbsentIDs().size(); i++) {
				bdInsert = bdInsert
						+ "INSERT INTO `oxen_in_graze` (`id`,`graze_id`,`ox_id`,`is_present`) VALUES (NULL,";
				bdInsert = bdInsert + "\'" + this.getId() + "\',";
				bdInsert = bdInsert + "\'" + this.getAbsentIDs().get(i) + "\',";
				bdInsert = bdInsert + "\'" + 0 + "\');\n";
			}
		}

		return bdInsert;
	}

	public String insertPresents() {
		String bdInsert = "";
//		INSERT INTO `oxen_in_graze` (`id`, `graze_id`, `ox_id`, `is_present`) VALUES (NULL, '', '', '0')
//		INSERT INTO 'oxen_in_graze' ('graze_id', 'ox_id', 'is_present') VALUES ('3741c4b6-0fde-48cd-8fc3-cbcc024d27d1', '3d796907-9ba6-4590-adec-69c2bf5b4430', '1'); 
//		TABLE `oxen_in_graze` (id, graze_id, ox_id, is_present)
		if (this.getPresentIDs() != null && this.getPresentIDs().size() > 0) {
			for (int i = 0; i < this.getPresentIDs().size(); i++) {
				bdInsert = bdInsert
						+ "INSERT INTO `oxen_in_graze` (`id`,`graze_id`,`ox_id`,`is_present`) VALUES (NULL,";
				bdInsert = bdInsert + "\'" + this.getId() + "\',";
				bdInsert = bdInsert + "\'" + this.presentIDs.get(i) + "\',";
				bdInsert = bdInsert + "\'" + 1 + "\');\n";
			}
		}
		return bdInsert;
	}
	
	public String insertPresentIDtoBD(String id) {
		String bdInsert="";
		bdInsert = bdInsert
				+ "INSERT INTO `oxen_in_graze` (`id`,`graze_id`,`ox_id`,`is_present`) VALUES (NULL,";
		bdInsert = bdInsert + "\'" + this.getId() + "\',";
		bdInsert = bdInsert + "\'" + id + "\',";
		bdInsert = bdInsert + "\'" + 1 + "\');\n";
		return bdInsert;
	}

}
