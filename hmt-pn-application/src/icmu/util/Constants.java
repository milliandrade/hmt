package icmu.util;

public class Constants {
	// constants
	/** ContextNet addresses */
//ContextNet
	public static final String GATEWAY_IP_ADDRESS = "127.0.0.1";
	public static final int GATEWAY_PORT_NUMBER = 5500;
	public static final String GATEWAY_IP = "scp.inf.puc-rio.br";
	public static final int GATEWAY_PORT = 5500;
	public static final int DEFAULT_HENCE = 12;
	public static final String GATEWAY_IP_LIST[] = {
		"scp.inf.puc-rio.br",	// group 1
		"scp.inf.puc-rio.br"	// group 2
	};
	public static final int GATEWAY_PORT_LIST[] = {
		5501,					// group 1
		5502					// group 2
	};
	
//Simulação
	public static final String PN_UUID="01111111-1111-1111-1111-111111111111";
	public static final String GROUND_STATION_UUID = "01111111-1111-1111-1111-000000000000";
	public static final String NO_INFORMATION = "01111111-1111-1111-1111-000000000404";

//Banco de dados
	
//	public static final String SERVER_NAME = "45.56.126.161:3306";
	public static final String SERVER_NAME = "db4free.net:3306";
//	public static final String SERVER_NAME = "192.168.100.196:3306";
	
	public static final String DATABASE = "hmt";

//	public static final String USERNAME = "millena";
	public static final String USERNAME = "millena";
	
	public static final String PASSWD = "Spoilt69";
	
	public static final int GRAZE_TIMEOUT = 6; 
	
}
