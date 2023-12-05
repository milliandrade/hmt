package icmu.management.aplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

import icmu.util.Drone;
import icmu.util.Graze;
import icmu.util.Hence;
import icmu.util.Ox;
import icmu.util.connection.DataBaseConnection;

public class Farm {
	String name;
	String description;
	Vector<Ox> oxen;
	Vector<Graze> grazes;
	Vector<Hence> hences;
	Vector<Drone> drones;
	boolean alert;
	Connection dbConnection;
	Statement query;
	String actualTrackingId;
//	public static String trackId = "01111111-5555-5555-5555-000000000001";

	public Farm() {
		this.setName("teste");
		this.setAlert(false);
		this.oxen = new Vector<Ox>();
		this.grazes = new Vector<Graze>();
		this.drones = new Vector<Drone>();
		this.hences = new Vector<Hence>();
		this.initializeFarm();
	}

	public Farm(String name) {
		this.setName(name);
		this.setAlert(false);
		this.oxen = new Vector<Ox>();
		this.grazes = new Vector<Graze>();
		this.drones = new Vector<Drone>();
		this.hences = new Vector<Hence>();
		dbConnection = DataBaseConnection.getDataBaseConnection();
		this.initializeFarm();

	}

	public Farm(String name, String description) {
		this.setName(name);
		this.setDescription(description);
		this.setAlert(false);
		this.oxen = new Vector<Ox>();
		this.grazes = new Vector<Graze>();
		this.drones = new Vector<Drone>();
		this.hences = new Vector<Hence>();
		dbConnection = DataBaseConnection.getDataBaseConnection();
		this.initializeFarm();
	}

	public Farm(String name, Vector<Ox> oxen, Vector<Graze> grazes, boolean alert) {
		this.name = name;
		this.oxen = oxen;
		this.grazes = grazes;
		this.drones = new Vector<Drone>();
		this.hences = new Vector<Hence>();
		this.alert = alert;
		dbConnection = DataBaseConnection.getDataBaseConnection();
		this.initializeFarm();
	}

	/**
	 * @return the dbConnection
	 */
	public Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * @param dbConnection the dbConnection to set
	 */
	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	/**
	 * @return the query
	 */
	public Statement getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(Statement query) {
		this.query = query;
	}

	/**
	 * @return the hences
	 */
	public Vector<Hence> getHences() {
		return hences;
	}

	/**
	 * @param hences the hences to set
	 */
	public void setHences(Vector<Hence> hences) {
		this.hences = hences;
	}

	/**
	 * @return the drones
	 */
	public Vector<Drone> getDrones() {
		return drones;
	}

	/**
	 * @param drones the drones to set
	 */
	public void setDrones(Vector<Drone> drones) {
		this.drones = drones;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<Ox> getOxen() {
		return oxen;
	}

	public void setOxen(Vector<Ox> oxen) {
		this.oxen = oxen;
	}

	public Vector<Graze> getGrazes() {
		return grazes;
	}

	public void setGrazes(Vector<Graze> grazes) {
		this.grazes = grazes;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	public void insertOx(Ox x) {
		String consulta = "";
//		try {
		if (x.getId().equalsIgnoreCase("")) {
			x.setId(this.generateOxID());
			this.getOxen().add(x);
//				consulta = x.saveNewOx();
//				this.query.executeUpdate(consulta);
			fileDrones();
		} else {
			if (this.getOxById(x.getId()) == null) {
				this.getOxen().add(x);
//					consulta = x.saveNewOx();
//					this.query.executeUpdate(consulta);	
//				fileDrones();
			}
		}

//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public void insertOx(String name, String breed, Date birth, double weight, String gender) {
		Ox tmp = new Ox(this.generateOxID(), name, Ox.getBreedString(breed), birth, weight,
				Ox.getGenderByString(gender));
		this.getOxen().add(tmp);
		String consulta = tmp.saveNewOx();
		try {
			this.query.executeUpdate(consulta);
			fileDrones();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertOx(String id, String name, String breed, Date birth, double weight, String gender) {
		Ox tmp = new Ox(id, name, Ox.getBreedString(breed), birth, weight, Ox.getGenderByString(gender));
		this.getOxen().add(tmp);
		String consulta = tmp.saveNewOx();
		try {
			this.query.executeUpdate(consulta);
			fileDrones();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<String> getOxenList() {
		Vector<String> ids = new Vector<String>();
		String tmp;
		Date birth;
		for (int i = 0; i < this.oxen.size(); i++) {
//			birth = this.getOxen().get(i).getBirth().setYear(this.getOxen().get(i).getBirth().getYear()-1900);
			tmp = this.getOxen().get(i).getName() + "\t" + this.getOxen().get(i).getBreedString() + "\t <"
					+ this.getOxen().get(i).getId() + "> \t " + this.getOxen().get(i).getBirth().toGMTString();
			ids.add(i, tmp);
		}
		return ids;
	}
	
	public Vector<String> getOxenIDList() {
		Vector<String> ids = new Vector<String>();
		String tmp;
		Date birth;
		for (int i = 0; i < this.oxen.size(); i++) {
			tmp = this.getOxen().get(i).getId();
			ids.add(i, tmp);
		}
		return ids;
	}

	public Vector<String> getOxenListWithIds(Vector<String> ids) {
		Vector<String> oxen = new Vector<String>();
		Ox tmp = null;
		String oxInfo = "";
		if (ids != null && ids.size() > 0) {
			for (int i = 0; i < ids.size(); i++) {
				tmp = this.getOxById(ids.get(i));
				if (tmp != null) {
					oxInfo = "<" + tmp.getId() + ">  " + tmp.getName();
					oxen.add(oxInfo);
				}
			}
		}

		return oxen;
	}

	public void insertHence(String name, double x1, double x2, double y1, double y2) {
		Hence tmp = new Hence(name, x1, x2, y1, y2);
		this.getHences().addElement(tmp);
		try {
			this.query.executeUpdate(tmp.saveNewHence());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertDrone() {
		Drone tmp = new Drone();
		this.getDrones().addElement(tmp);
		this.fileDrones();
		String consulta = "INSERT INTO drone;";
		try {
			this.query.executeUpdate(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizeFarmFromBD() {
		System.out.println("--------------- Atualizando o Farm");
		String consulta = "SELECT * FROM farm WHERE name = \'" + this.getName() + "\'";

		try {
			this.query = this.dbConnection.createStatement();
			ResultSet resultado = this.query.executeQuery(consulta);
			if (!resultado.next()) {
				query = this.dbConnection.createStatement();
				consulta = this.saveFarm();
				query.executeUpdate(consulta);
			} else {
				consulta = "SELECT * FROM ox;";
				resultado = this.query.executeQuery(consulta);
				Ox tmp;
				while (resultado.next()) {
//					Ox(String id, String name, OxBreed breed, Date birth, double weight, OxGender gender) 
					tmp = new Ox(resultado.getString("id"), resultado.getString("name"),
							Ox.getBreedString(resultado.getString("breed")), resultado.getDate("birth"),
							resultado.getDouble("weight"), Ox.getGenderByString(resultado.getString("gender")));
					tmp.setMom(resultado.getString("mom_id"));
					tmp.setDad(resultado.getString("dad_id"));
					tmp.setDeath(resultado.getDate("death"));
					if (this.getOxByName(tmp.getName())==null) {
						System.out.println(tmp.getName());
						this.insertOx(tmp);
					}
				}

				consulta = "SELECT * FROM graze;";
				resultado = this.query.executeQuery(consulta);
				Graze tmpG;
				while (resultado.next()) {
//					Ox(String id, String name, OxBreed breed, Date birth, double weight, OxGender gender) 
					tmpG = new Graze();
					tmpG.setId(resultado.getString("id"));
					Date tmpD = resultado.getDate("date");
					Time tmpT = resultado.getTime("start_time");
					int day = Integer.parseInt(tmpD.toGMTString().split(" ")[0]);
					int month = this.getMonth(tmpD.toGMTString().split(" ")[1]) ;
					int year = Integer.parseInt(tmpD.toGMTString().split(" ")[2]);
//					tmpG.setStart(LocalDateTime.of(tmpD.getYear(), tmpD.getMonth(), tmpD.getDay(), tmpT.getHours(),
//							tmpT.getMinutes()));
					tmpG.setStart(LocalDateTime.of(year, month, day, tmpT.getHours(),
							tmpT.getMinutes()));
					tmpT = resultado.getTime("end_time");
					if (tmpT!= null && tmpT.equals("null")) {
					tmpG.setEnd(LocalDateTime.of(year, month, day, tmpT.getHours(),
							tmpT.getMinutes()));
					}
					tmpG.setHence_id(resultado.getInt("hence_id"));
					tmpG.setStatus(resultado.getBoolean("alert"));
					consulta = "SELECT * FROM oxen_in_graze WHERE graze_id = " + tmpG.getId() + ";";
					if (this.getGrazesById(tmpG.getId()) == null) {
						ResultSet resultado2 = this.query.executeQuery(consulta);
						while (resultado2.next()) {
							if (resultado2.getBoolean("is_present")) {
								tmpG.getPresentIDs().add(resultado2.getString("ox_id"));
							} else {
								tmpG.getAbsentIDs().add(resultado2.getString("ox_id"));
							}
						}
						this.grazes.add(tmpG);
					}
				}
				fileDrones();
				fileOxen();

			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}

	}

	public String generateOxID() {
		String id = UUID.randomUUID().toString();
		boolean exist = false;
		for (int i = 0; (i < this.oxen.size() & !exist); i++) {
			if (this.getOxen().get(i).getId().equals(id)) {
				id = UUID.randomUUID().toString();
				i = 0;
			}
		}
		return id;
	}

	public void initialize() {
//	public Ox(String id, String name, String breed, Data birth, double weight )
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:\\Users\\mille\\git\\repository2\\ICMU-T1-rastreamento-bovino\\src\\icmu\\util\\animais.txt"));

			String line = br.readLine();
			Date tmpDate = null;
			while (line != null) {
				String[] linhas = line.split("\t");
				tmpDate = new Date(Integer.parseInt(linhas[3]) - 1900, Integer.parseInt(linhas[4]),
						Integer.parseInt(linhas[5]), Integer.parseInt(linhas[6]), Integer.parseInt(linhas[7]));
				this.insertOx(linhas[0], linhas[1], linhas[2], tmpDate, Double.parseDouble(linhas[8]), "MALE");
				line = br.readLine();
			}
			br.close();
		} catch (IOException ioe) {

		}

	}

	public Vector<Ox> getMissingOxen(Vector<String> foundIDs) {
		Vector<Ox> missing = new Vector<Ox>();
		boolean found = false;
		System.out.println("encontrados: " + foundIDs.size());
		for (int i = 0; i < this.getOxen().size(); i++) {

			for (int j = 0; j < foundIDs.size(); j++) {
//				System.out.println("ID armazenada: " + this.getOxen().get(i).getId());
//				System.out.println("ID encontrada: " + foundIDs.get(j));
//				System.out.println();
				if (this.getOxen().get(i).getId().equalsIgnoreCase(foundIDs.get(j).trim())) {
					found = true;
				}
			}
			if (!found) {
				missing.add(this.getOxen().get(i));
			}
			found = false;
		}
		System.out.println("ausentes: " + missing.size());
		return missing;
	}

	public static void main(String argv[]) {
		Farm fazenda = new Farm("Exemplo");

		System.out.println(fazenda.getName());
//		fazenda.initialize();
//		fazenda.initializeFarm();`
		String uuid = "";
		Ox boi = new Ox();
		for (int i=0; i<200; i++) {
			uuid = UUID.randomUUID().toString();
			boi = new Ox();
			boi.setId(uuid);
			boi.setName(uuid);
			fazenda.insertOx(boi);
		}
		BufferedWriter bWriter;
		try {
			bWriter = new BufferedWriter(new FileWriter(
					"C:\\Users\\mille\\omnetpp-5.6-src-windows\\omnetpp-5.6\\git\\gradys-simulations\\tmp_uuid.txt"));
			bWriter.flush();
//			System.out.println("#oxen: " + this.getOxen().size());
			for (int i = 0; i < fazenda.getOxen().size(); i++) {
				bWriter.write(fazenda.getOxen().get(i).getId() + "\n");

			}
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println(fazenda.getOxenIDList());

	}

	public void printOxen() {
		System.out.println("##########################################\n");
		System.out.println("Rebanho\n");
		System.out.println("##########################################\n\n\n\n");
		System.out.println("<ID>  NOME\tRAÇA\tNASCIMENTO");
		if (this.getOxen() != null && this.getOxen().size() > 0) {
			for (int i = 0; i < this.getOxen().size(); i++) {
				System.out.println("<" + this.getOxen().get(i).getId() + ">   " + this.getOxen().get(i).getName() + "\t"
						+ this.getOxen().get(i).getBreed() + "\t" + this.getOxen().get(i).getBirth().toGMTString());
			}
		} else {
			System.out.println("Não existe informações sobre o reabanho.");
		}
	}

	public void printGrazes() {
		System.out.println("\n\n\n##########################################\n");
		System.out.println("Informações sobre pastagem\n");
		System.out.println("##########################################\n\n\n\n");
		System.out.println("<DATA>\tSTATUS\t[ANIMAIS AUSENTES]");
		Vector<String> absents = null;
		if (this.getGrazes() != null && this.getGrazes().size() > 0) {
			for (int i = 0; i < this.getGrazes().size(); i++) {
				System.out.print("<" + this.getGrazes().get(i).onlyDateToString() + ">\t");
				if (this.getGrazes().get(i) != null && this.getGrazes().get(i).isStatus()) {
					System.out.print("SEM INTECORRENCIAS");
				} else {
					if (this.getGrazes().get(i) != null && this.getGrazes().get(i).getAbsentIDs().size() == 1) {
						System.out.print("ANIMAL AUSENTE: \t");
						System.out.println(this.getOxenListWithIds(this.getGrazes().get(i).getAbsentIDs()));
					} else if (this.getGrazes().get(i) != null && this.getGrazes().get(i).getAbsentIDs().size() > 1) {
						System.out.println("ANIMAIS AUSENTES:");
						absents = this.getOxenListWithIds(this.getGrazes().get(i).getAbsentIDs());
						if (absents != null && absents.size() > 0) {
							for (int j = 0; j < absents.size(); j++) {
								System.out.println("\t\t" + absents.get(j));
							}
						}
					} else {
						System.out.println("SEM INFORMAÇÕES");
					}

				}
				System.out.println();
			}
		} else {
			System.out.println("Não existe informações sobre o reabanho.");
		}
	}

	public Ox getOxById(String id) {
		Ox tmp = null;
		for (int i = 0; i < this.getOxen().size(); i++) {
			if (this.getOxen().get(i).getId().equalsIgnoreCase(id)) {
				tmp = this.getOxen().get(i);
				break;
			}
		}
		return tmp;
	}
	
	public Ox getOxByName(String name) {
		Ox tmp = null;
		for (int i = 0; i < this.getOxen().size(); i++) {
			if (this.getOxen().get(i).getName().equalsIgnoreCase(name)) {
				tmp = this.getOxen().get(i);
				break;
			}
		}
		return tmp;
	}


	public void addGraze(Graze graze) {

		Vector<Ox> missing = this.getMissingOxen(graze.getPresentIDs());
		if (missing.size() > 0) {
			for (int i = 0; i < missing.size(); i++) {
				graze.getAbsentIDs().add(missing.get(i).getId());
			}
		}
		this.insertGraze(graze);
		
	}

	public boolean hasGraze(String ID) {
		boolean found = false;
		if (this.getGrazes().size() > 0) {
			for (int i = 0; i < this.getGrazes().size(); i++) {
				if (this.getGrazes().get(i).getId().equalsIgnoreCase(ID)) {
					found = true;
				}
			}
		}
		return found;
	}

	public void insertOxIntoGraze(String grazeId, String oxId ) {
		boolean found = false;
		Graze tmp;
		int i = 0;
		if (this.getGrazes().size() > 0) {
			while (!found && (i < this.getGrazes().size())) {
				if (this.getGrazes().get(i).getId().equalsIgnoreCase(grazeId)) {
					found = true;
					tmp = this.getGrazes().get(i);
					boolean inseriu = tmp.insertPresentID(oxId);
					if (inseriu) {
						try {
							//System.out.println("inserir ox in ox_in_graze: \t" + tmp.insertPresentIDtoBD(oxId));
							this.getQuery()
							.executeUpdate(tmp.insertPresentIDtoBD(oxId));
						} catch (SQLException e) {
//							e.printStackTrace();
						}
					}
//					System.out.println("inseriu o boi " + oxId + "em " + grazeId);
				}
				i++;
			}
		}
	}

	public String saveFarm() {
		String bdInsert = "";
		// (name, description)

		bdInsert = "INSERT INTO farm VALUES(";
		bdInsert = bdInsert + "\'" + this.getName() + "\', ";
		bdInsert = bdInsert + "\'" + this.getDescription() + "\');";

		return bdInsert;
	}

	public String updateFarm() {
		String bdInsert = "UPDATE farm SET ";
		// (name, description)
		bdInsert = bdInsert + " name = \'" + this.getName() + "\', ";
		bdInsert = bdInsert + " description = \'" + this.getDescription() + "\'";
		bdInsert = bdInsert + " notify = \'" + 0 + "\'";
		bdInsert = bdInsert + " WHERE name = \'" + this.getName() + "\';";
		return bdInsert;
	}

	public void initializeFarm() {
		String consulta = "SELECT * FROM farm WHERE name = \'" + this.getName() + "\'";

		try {
			this.query = this.dbConnection.createStatement();
			ResultSet resultado = this.query.executeQuery(consulta);
			if (!resultado.next()) {
				query = this.dbConnection.createStatement();
				consulta = this.saveFarm();
				query.executeUpdate(consulta);
			} else {
				query = this.dbConnection.createStatement();
				consulta = "SELECT * FROM ox;";
				resultado = this.query.executeQuery(consulta);
				Ox tmp;
				while (resultado.next()) {
//					Ox(String id, String name, OxBreed breed, Date birth, double weight, OxGender gender)
//					System.out.println(resultado.getString("id"));
					tmp = new Ox(resultado.getString("id"), resultado.getString("name"),
							Ox.getBreedString(resultado.getString("breed")), resultado.getDate("birth"),
							resultado.getDouble("weight"), Ox.getGenderByString(resultado.getString("gender")));
					tmp.setMom(resultado.getString("mom_id"));
					tmp.setDad(resultado.getString("dad_id"));
					tmp.setDeath(resultado.getDate("death"));
					this.insertOx(tmp);
				}

				consulta = "SELECT * FROM graze;";
				resultado = this.query.executeQuery(consulta);
				Graze tmpG;
				Vector<String> grazesWithEndOpen = new Vector<String>();
				while (resultado.next()) {
//					Ox(String id, String name, OxBreed breed, Date birth, double weight, OxGender gender) 
					tmpG = new Graze();
					tmpG.setId(resultado.getString("id"));
					Date tmpD = resultado.getDate("date");
//					System.out.println(tmpD);
					Time tmpT = resultado.getTime("start_time");
//					System.out.println(tmpG.getId());
					int day = Integer.parseInt(tmpD.toGMTString().split(" ")[0]);
					int month = this.getMonth(tmpD.toGMTString().split(" ")[1]) ;
					int year = Integer.parseInt(tmpD.toGMTString().split(" ")[2]);
							
//					System.out.println(day);
//					System.out.println(month);
//					System.out.println(year);
					tmpG.setStart(LocalDateTime.of(year, month, day, tmpT.getHours(),
							tmpT.getMinutes()));
					tmpT = resultado.getTime("end_time");
					if (tmpT!=null) {
					tmpG.setEnd(LocalDateTime.of(year, month, day, tmpT.getHours(),
							tmpT.getMinutes()));
					}else {
						grazesWithEndOpen.add(tmpG.getId());
					}
					tmpG.setHence_id(resultado.getInt("hence_id"));
					tmpG.setStatus(resultado.getBoolean("alert"));
					
					this.grazes.add(tmpG);
				}
				
				for (int i=0; i< grazesWithEndOpen.size() ; i++) {
					if (grazesWithEndOpen.get(i)!=null) {
						tmpG = this.getGrazesById(grazesWithEndOpen.get(i));
						if (tmpG !=null && tmpG.getEnd()==null) {
							this.getGrazesById(grazesWithEndOpen.get(i)).setEnd(LocalDateTime.now());
						}
					}
				}
				
				//Adicionar os bois no rastreamento
				
				for (int i=0; i<this.getGrazes().size();i++) {
					consulta = "SELECT * FROM oxen_in_graze WHERE graze_id = \'" + this.getGrazes().get(i).getId() + "\';";
//					System.out.println(consulta);
					ResultSet resultado2 = this.query.executeQuery(consulta);
					while (resultado2.next()) {
						if (resultado2.getBoolean("is_present")) {
							this.getGrazes().get(i).insertPresentID(resultado2.getString("ox_id"));
						} else {
							this.getGrazes().get(i).insertAbsentId(resultado2.getString("ox_id"));
						}
					}
				}
				
				
				consulta = "SELECT * FROM drone;";
				resultado = this.query.executeQuery(consulta);
				while (resultado.next()) {
					this.getDrones().add(new Drone(resultado.getString("id")));
				}

				consulta = "SELECT * FROM hence;";
				resultado = this.query.executeQuery(consulta);
				Hence tmpH;
				while (resultado.next()) {
					tmpH = new Hence(resultado.getString("hence_name"), resultado.getInt("map_minX"),
							resultado.getInt("map_maxX"), resultado.getInt("map_minY"), resultado.getInt("map_maxY"));
					tmpH.setId(resultado.getInt("hence_id"));
					this.getHences().add(tmpH);
				}
				fileDrones();
				fileOxen();

			}
			System.out.println("------------------------------");
			System.out.println("::"+this.getName() + " inicializada!");
			System.out.println("#oxen:\t\t" + this.getOxen().size());
			System.out.println("#drones:\t" + this.getDrones().size());
			System.out.println("#grazes:\t" + this.getGrazes().size());
			System.out.println("------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void fileOxen() {
		BufferedWriter bWriter;
		try {
			bWriter = new BufferedWriter(new FileWriter(
					"C:\\Users\\mille\\omnetpp-5.6-src-windows\\omnetpp-5.6\\git\\gradys-simulations\\tmp_uuid.txt"));
			bWriter.flush();
//			System.out.println("#oxen: " + this.getOxen().size());
			for (int i = 0; i < this.getOxen().size(); i++) {
				bWriter.write(this.getOxen().get(i).getId() + "\n");

			}
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fileDrones() {
		BufferedWriter bWriter;
		try {
			bWriter = new BufferedWriter(new FileWriter(
					"C:\\Users\\mille\\omnetpp-5.6-src-windows\\omnetpp-5.6\\git\\gradys-simulations\\tmp_drones_uuid.txt"));
			bWriter.flush();
			bWriter.write(this.getGrazes().get(this.getGrazes().size()-1).getId() + "\n");
			bWriter.write(0 + "\n");
//			System.out.println("#drones: " + this.getDrones().size());
			for (int i = 0; i < this.getDrones().size(); i++) {
				bWriter.write(this.getDrones().get(i).getId() + "\n");

			}
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fileDrones(String grazeId) {
		BufferedWriter bWriter;
		try {
			bWriter = new BufferedWriter(new FileWriter(
					"C:\\Users\\mille\\omnetpp-5.6-src-windows\\omnetpp-5.6\\git\\gradys-simulations\\tmp_drones_uuid.txt"));
			bWriter.flush();
			bWriter.write(grazeId + "\n");
			bWriter.write(1 + "\n");
//			System.out.println("#drones: " + this.getDrones().size());
			for (int i = 0; i < this.getDrones().size(); i++) {
				bWriter.write(this.getDrones().get(i).getId() + "\n");

			}
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getDronesList() {
		String drones = "";
		for (int i = 0; i < this.getDrones().size(); i++) {
			drones = drones + "\t" + this.getDrones().get(i).getId();
		}
		return drones;
	}

	public Graze getGrazesById(String id) {
		Graze tmp = null;
		boolean found = false;
		int i = 0;
		while (!found && (i < this.getGrazes().size())) {
			if (this.getGrazes().get(i).getId().equalsIgnoreCase(id)) {
				found = true;
				tmp = this.getGrazes().get(i);
			}
			i++;
		}
		return tmp;
	}

	public void addDrone(String id) {
		boolean found = false;
		int i = 0;
		while (!found && (i < this.getDrones().size())) {
			if (this.getDrones().get(i).getId().equalsIgnoreCase(id)) {
				found = true;
			}
			i++;
		}
		if (!found) {
			this.drones.add(new Drone(id));
		}

	}

	public boolean existOx(String id) {
		return (this.getOxById(id) != null);

	}
	
	public void insertGraze(Graze tmpG) {
		boolean found = false;
		int i = 0;
		while (!found && (i < this.getGrazes().size())) {
			if (this.getGrazes().get(i).getId() == tmpG.getId()) {
				found = true;
			}
			i++;
		}
		if (!found) {
			this.grazes.add(tmpG);
			String insertT = tmpG.saveNewGraze();
			try {
				this.query.executeUpdate(insertT);
			} catch (SQLException e) {
				
			}
		}
		
	}
	
	
	public int getMonth(String month){
		int mes = 1;
		switch (month) {
		case "Dec":
			mes = 12;
			break;
		case "Jan": 
			mes = 1;
			break;
		case "Feb": 
			mes = 2;
			break;
		case "Mar": 
			mes = 3;
			break;
		case "Apr": 
			mes = 4;
			break;
		case "May": 
			mes = 5;
			break;
		case "Jun": 
			mes = 6;
			break;
		case "Jul": 
			mes = 7;
			break;
		case "Aug": 
			mes = 8;
			break;
		case "Sep": 
			mes = 9;
			break;
		case "Oct": 
			mes = 10;
			break;
		case "Nov": 
			mes = 11;
			break;
		default:
			break;
		}
		return mes;
	}

	public void finishGrazeUpdate(Graze tmpGraze) {
		if (tmpGraze!=null) {
			System.out.println("Prsentes"+tmpGraze.getPresentIDs());
			for (int i=0; i<this.getOxen().size();i++) {
				System.out.println("Finalizando Tracking: #oxen:" +this.getOxen().size() );
				System.out.println(this.getOxen().get(i).getId());
//				if (!tmpGraze.hasPresentID(this.getOxen().get(i).getId())) {
				if (!tmpGraze.getPresentIDs().contains(this.getOxen().get(i).getId())) {
					System.out.println("adicionar em ausentes");
					tmpGraze.insertAbsentId(this.getOxen().get(i).getId());
				}
			}
			
			for (int i=0; i<tmpGraze.getPresentIDs().size() ; i++) {
				if (tmpGraze.getAbsentIDs().contains(tmpGraze.getPresentIDs().get(i))) {
					tmpGraze.removeAbsentID(tmpGraze.getPresentIDs().get(i));
				}
			}
			try {
				String[] tmpA = tmpGraze.insertAbsentsToBD().split("\n");
				String consulta = "";
				for (int i = 0; i < tmpA.length; i++) {
					consulta = tmpA[i];
//					System.out.println("absent ox to BD:: " + consulta);
					this.getQuery().executeUpdate(consulta);
				}
//				this.getQuery().executeUpdate(tmpGraze.insertAbsentsToBD());
				tmpGraze.setEnd(LocalDateTime.now());
				if (tmpGraze.getAbsentIDs().size()>0) {
					tmpGraze.setStatus(true);
					this.setAlert(true);
				}
				this.getQuery().executeUpdate(tmpGraze.updateGraze());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the actualTrackingId
	 */
	public String getActualTrackingId() {
		return actualTrackingId;
	}

	/**
	 * @param actualTrackingId the actualTrackingId to set
	 */
	public void setActualTrackingId(String actualTrackingId) {
		this.actualTrackingId = actualTrackingId;
	}
	
	
}
