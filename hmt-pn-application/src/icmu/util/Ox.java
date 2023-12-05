package icmu.util;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class Ox {
	enum OxBreed {
		NELORE, ANGUS, GIR, BRAHMAN, BRANGUS, SENEPOL, HEREFORD, CARACU, CHAROLÊS, GUZERÁ, TABAPUÃ, SIMENTAL, LIMOUSIN,
		CHIANINA, DEVON, WAGYU;
	}

	enum OxGender {
		MALE, FEMALE
	};

	String id;
	String name;

	OxBreed breed;
	double weight;
	Date birth;
	Vector<Graze> grazes;

	String mom;
	String dad;
	Date death;
	OxGender gender;

	public Ox() {
		this.mom = "00000000-0000-0000-0000-000000000000";
		this.dad = "00000000-0000-0000-0000-000000000000";
	}

	public Ox(String id, String name, OxBreed breed, Date birth, double weight, OxGender gender) {
		this.setMom("00000000-0000-0000-0000-000000000000");
		this.setDad("00000000-0000-0000-0000-000000000000");
		this.setId(id);
		this.setName(name);
		this.setBreed(breed);
		this.setBirth(birth);
		this.setWeight(weight);
		this.setGender(gender);
		this.grazes = new Vector<Graze>();
	}

	public Ox(String name, OxBreed breed, Date birth, double weight, OxGender gender) {
		this.setMom("00000000-0000-0000-0000-000000000000");
		this.setDad("00000000-0000-0000-0000-000000000000");
		this.setId(UUID.randomUUID().toString());
		this.setName(name);
		this.setBreed(breed);
		this.setBirth(birth);
		this.setWeight(weight);
		this.setGender(gender);
		this.grazes = new Vector<Graze>();
	}

	public Ox(String name, OxBreed breed, Date birth, double weight, String mom, String dad, OxGender gender) {
		this.setId(UUID.randomUUID().toString());
		this.setName(name);
		this.setBreed(breed);
		this.setBirth(birth);
		this.setWeight(weight);
		this.setMom(mom);
		this.setDad(dad);
		this.setGender(gender);
		this.grazes = new Vector<Graze>();
	}

	public Ox(String name, String breed, Date birth, double weight, OxGender gender) {
		this.setId(UUID.randomUUID().toString());
		this.setMom("00000000-0000-0000-0000-000000000000");
		this.setDad("00000000-0000-0000-0000-000000000000");
		this.setName(name);
		this.setBreed(this.getBreedString(breed));
		this.setBirth(birth);
		this.setWeight(weight);
		this.setGender(gender);
		this.grazes = new Vector<Graze>();
	}

	public Ox(String name, String breed, Date birth, double weight, String mom, String dad, OxGender gender) {
		this.setId(UUID.randomUUID().toString());
		this.setName(name);
		this.setBreed(this.getBreedString(breed));
		this.setBirth(birth);
		this.setWeight(weight);
		this.setMom(mom);
		this.setDad(dad);
		this.setGender(gender);
		this.grazes = new Vector<Graze>();
	}

	public String getMom() {
		return mom;
	}

	public void setMom(String mom) {
		this.mom = mom;
	}

	public String getDad() {
		return dad;
	}

	public void setDad(String dad) {
		this.dad = dad;
	}

	public Date getDeath() {
		return death;
	}

	public void setDeath(Date death) {
		this.death = death;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OxBreed getBreed() {
		return breed;
	}

	public OxGender getGender() {
		return gender;
	}

	public void setGender(OxGender gender) {
		this.gender = gender;
	}

	public String getGenderString() {
		String genderName = "";
		switch (this.getGender()) {
		case MALE:
			genderName = "MALE";
			break;
		case FEMALE:
			genderName = "FEMALE";
			break;
		}
		return genderName;
	}

	public void setGender(String gender) {
		switch (gender) {
		case "MALE":
			this.setGender(OxGender.MALE);
			break;
		case "FEMALE":
			this.setGender(OxGender.FEMALE);
			break;
		}
	}

	public static OxGender getGenderByString(String gender) {
		OxGender ox_gender = OxGender.MALE;
		switch (gender) {
		case "MALE":
			ox_gender = OxGender.MALE;
			break;
		case "FEMALE":
			ox_gender = OxGender.FEMALE;
			break;
		}
		return ox_gender;
	}

	public String getBreedString() {
		String breedName = "";
		switch (this.getBreed()) {
//		NELORE, ANGUS, GIR, BRAHMAN, BRANGUS, SENEPOL, HEREFORD, CARACU, CHAROLÊS, GUZERÁ, TABAPUÃ, SIMENTAL, LIMOUSIN, CHIANINA, DEVON, WAGYU
		case NELORE:
			breedName = "NELORE";
			break;
		case ANGUS:
			breedName = "ANGUS";
			break;
		case GIR:
			breedName = "GIR";
			break;
		case BRAHMAN:
			breedName = "BRAHMAN";
			break;
		case BRANGUS:
			breedName = "BRANGUS";
			break;
		case SENEPOL:
			breedName = "SENEPOL";
			break;
		case HEREFORD:
			breedName = "HEREFORD";
			break;
		case CARACU:
			breedName = "CARACU";
			break;
		case CHAROLÊS:
			breedName = "CHAROLÊS";
			break;
		case GUZERÁ:
			breedName = "GUZERÁ";
			break;
		case TABAPUÃ:
			breedName = "TABAPUÃ";
			break;
		case SIMENTAL:
			breedName = "SIMENTAL";
			break;
		case LIMOUSIN:
			breedName = "LIMOUSIN";
			break;
		case CHIANINA:
			breedName = "CHIANINA";
			break;
		case DEVON:
			breedName = "DEVON";
			break;
		case WAGYU:
			breedName = "WAGYU";
			break;
		default:
			break;
		}
		return breedName;
	}

	public static OxBreed getBreedString(String breed) {
		OxBreed breedName = OxBreed.NELORE;
		switch (breed) {
		case "NELORE":
			breedName = OxBreed.NELORE;
			break;
		case "ANGUS":
			breedName = OxBreed.ANGUS;
			break;
		case "GIR":
			breedName = OxBreed.GIR;
			break;
		case "BRAHMAN":
			breedName = OxBreed.BRAHMAN;
			break;
		case "BRANGUS":
			breedName = OxBreed.BRANGUS;
			break;
		case "SENEPOL":
			breedName = OxBreed.SENEPOL;
			break;
		case "HEREFORD":
			breedName = OxBreed.HEREFORD;
			break;
		case "CARACU":
			breedName = OxBreed.CARACU;
			break;
		case "CHAROLÊS":
			breedName = OxBreed.CHAROLÊS;
			break;
		case "GUZERÁ":
			breedName = OxBreed.GUZERÁ;
			break;
		case "TABAPUÃ":
			breedName = OxBreed.TABAPUÃ;
			break;
		case "SIMENTAL":
			breedName = OxBreed.SIMENTAL;
			break;
		case "LIMOUSIN":
			breedName = OxBreed.LIMOUSIN;
			break;
		case "CHIANINA":
			breedName = OxBreed.CHIANINA;
			break;
		case "DEVON":
			breedName = OxBreed.DEVON;
			break;
		case "WAGYU":
			breedName = OxBreed.WAGYU;
			break;
		default:
			break;
		}
		return breedName;
	}

	public void setBreed(OxBreed breed) {
		this.breed = breed;
	}

	public void setBreed(String breed) {
		this.breed = getBreedString(breed);
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Vector<Graze> getGrazes() {
		return grazes;
	}

	public void setGrazes(Vector<Graze> grazes) {
		this.grazes = grazes;
	}
	
	public static OxBreed getBreed(int op) {
		return OxBreed.values()[op];
	}
	
	public static OxGender getGender(int op) {
		return OxGender.values()[op];
	}

	@Override
	public String toString() {
		return "Ox [id=" + id + ", name=" + name + ", breed=" + breed + ", weight=" + weight + ", birth=" + birth
				+ ", grazes=" + grazes + "]";
	}

	public static void main(String[] argvs) {
		for (int i = 0; i < 20; i++) {
			System.out.println(UUID.randomUUID());
		}
	}

	public String saveNewOx() {
		String bdInsert = "";
		if (this.getDeath() == null) {
			bdInsert = "INSERT INTO ox (id, name, birth, weight, breed, mom_id, dad_id, gender) VALUES(";
		} else {
			bdInsert = "INSERT INTO ox VALUES(";
		}

		// (id, name, birth, weight, breed, mom_id, dad_id, death, gender)
		bdInsert = bdInsert + "\'" + this.getId() + "\', ";
		bdInsert = bdInsert + "\'" + this.getName() + "\', ";
		bdInsert = bdInsert + "\'" + this.getBirth().getYear() + "-" + this.getBirth().getMonth() + "-"
				+ this.getBirth().getDay() + "\', ";
		bdInsert = bdInsert + "\'" + this.getWeight() + "\', ";
		bdInsert = bdInsert + "\'" + this.getBreed().ordinal() + "\', ";
		bdInsert = bdInsert + "\'" + this.getMom() + "\', ";
		bdInsert = bdInsert + "\'" + this.getDad() + "\', ";
		if (this.getDeath() != null) {
			bdInsert = bdInsert + "\'" + this.getDeath().getYear() + "-" + this.getDeath().getMonth() + "-"
					+ this.getDeath().getDay() + "\', ";
		}
		bdInsert = bdInsert + "\'" + this.getGender().ordinal() + "\');";
		return bdInsert;
	}

	public String updateOx() {
		String bdInsert = "UPDATE ox SET ";

		// (id, name, birth, weight, breed, mom_id, dad_id, death, gender)
		bdInsert = bdInsert + " name = \'" + this.getName() + "\', ";
		bdInsert = bdInsert + "birth = \'" + this.getBirth().getYear() + "-" + this.getBirth().getMonth() + "-"
				+ this.getBirth().getDay() + "\', ";
		bdInsert = bdInsert + "weight = \'" + this.getWeight() + "\', ";
		bdInsert = bdInsert + "breed = \'" + this.getBreed().ordinal() + "\', ";
		bdInsert = bdInsert + "mom_id = \'" + this.getMom() + "\', ";
		bdInsert = bdInsert + "dad_id = \'" + this.getDad() + "\', ";
		if (this.getDeath() != null) {
			bdInsert = bdInsert + "death = \'" + this.getDeath().getYear() + "-" + this.getDeath().getMonth() + "-"
					+ this.getDeath().getDay() + "\', ";
		}
		bdInsert = bdInsert + "gender = \'" + this.getGender().ordinal() + "\' ";
		bdInsert = bdInsert + "WHERE id = \'" + this.getId() + "\';";
		return bdInsert;
	}

}
