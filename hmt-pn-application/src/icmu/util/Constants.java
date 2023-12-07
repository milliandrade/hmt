package icmu.util;

public class Constants {
	// constants
	/** ContextNet addresses */
	// ContextNet
	public static String GATEWAY_IP_ADDRESS = "127.0.0.1";

	public static String getGATEWAY_IP_ADDRESS() {
		return GATEWAY_IP_ADDRESS;
	}

	public static void setGATEWAY_IP_ADDRESS(String gATEWAY_IP_ADDRESS) {
		GATEWAY_IP_ADDRESS = gATEWAY_IP_ADDRESS;
	}

	public static final int GATEWAY_PORT_NUMBER = 5500;

	public static String getGATEWAY_IP() {
		return GATEWAY_IP;
	}

	public static void setGATEWAY_IP(String gATEWAY_IP) {
		GATEWAY_IP = gATEWAY_IP;
	}

	public static String GATEWAY_IP = "scp.inf.puc-rio.br";
	public static final int GATEWAY_PORT = 5500;
	public static final int DEFAULT_HENCE = 12;
	public static final String GATEWAY_IP_LIST[] = {
			"scp.inf.puc-rio.br", // group 1
			"scp.inf.puc-rio.br" // group 2
	};
	public static final int GATEWAY_PORT_LIST[] = {
			5501, // group 1
			5502 // group 2
	};

	// Simulação
	public static final String PN_UUID = "01111111-1111-1111-1111-111111111111";
	public static final String GROUND_STATION_UUID = "01111111-1111-1111-1111-000000000000";
	public static final String NO_INFORMATION = "01111111-1111-1111-1111-000000000404";

	// Banco de dados

	// public static final String SERVER_NAME = "45.56.126.161:3306";
	public static final String SERVER_NAME = "db4free.net:3306";
	// public static final String SERVER_NAME = "192.168.100.196:3306";

	public static final String DATABASE = "hmt";

	public static String getUSERNAME() {
		return USERNAME;
	}

	public static void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public static String USERNAME = "hmt_username";

	public static String PASSWD = "hmt_password";

	public static String getPASSWD() {
		return PASSWD;
	}

	public static void setPASSWD(String pASSWD) {
		PASSWD = pASSWD;
	}

	public static final int GRAZE_TIMEOUT = 6;

	public static String MYSQL_ADDRESS = "jdbc:mysql://45.56.126.161:3306/";

	public static String getMYSQL_ADDRESS() {
		return MYSQL_ADDRESS;
	}

	public static void setMYSQL_ADDRESS(String mYSQL_ADDRESS) {
		MYSQL_ADDRESS = mYSQL_ADDRESS;
	}

}
