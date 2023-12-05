package icmu.nodes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import ckafka.data.SwapData;
import icmu.util.Constants;
import icmu.util.NodeMessage;
import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;

public class DroneNode implements NodeConnectionListener {

	private static String gatewayIP = Constants.GATEWAY_IP_ADDRESS;
	private static int gatewayPort = Constants.GATEWAY_PORT_NUMBER;
	private MrUdpNodeConnection connection;
	private UUID myUUID;
	public String grazeInfo;
	public boolean endTracking = false;

	public DroneNode() {
		myUUID = UUID.fromString("01111111-1111-1111-1111-111111111111");

		InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
		System.out.println("Inicializou o InetSocketAddress com: \t" + gatewayIP + "\t" + gatewayPort);
		try {
			connection = new MrUdpNodeConnection(myUUID);
			connection.addNodeConnectionListener(this);
			connection.connect(address);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DroneNode(String myUUID, String grazeId, boolean end, String graze) {
		this.myUUID = UUID.fromString(myUUID);
		this.endTracking = end;
		this.grazeInfo = graze;
		InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
		System.out.println("Inicializou o InetSocketAddress com: \t" + gatewayIP + "\t" + gatewayPort);
		try {
			connection = new MrUdpNodeConnection(this.myUUID);
			connection.connect(address);
			for (int i = 0; i < 10000; i++) {

			}
			sendMessageFromString(grazeId, graze);
			for (int i = 0; i < 10000; i++) {

			}
//			connection.removeNodeConnectionListener(this);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.INFO);

		if (args.length > 3) {
			String grazesTmp = "";
			for (int i = 3; i < args.length; i++) {
				grazesTmp = grazesTmp + args[i] + "\t";
			}
			DroneNode node = new DroneNode(args[0], args[1], Boolean.getBoolean(args[2]), grazesTmp);
		} else {
			System.out.println("Sem informações sobre rastreamento");
			DroneNode node2 = new DroneNode(Constants.NO_INFORMATION, "null", true,
					"Sem informações sobre rastreamento");
			
		}
	}

	public void connected(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		message.setContent("Registering".getBytes(StandardCharsets.UTF_8));

		try {
			connection.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newMessageReceived(NodeConnection remoteCon, Message message) {
		System.out.print("[DroneNode] [receivedMessage] :\t");

		SwapData dataReceived = (SwapData) message.getContentObject();
		String tmp1 = new String(dataReceived.getMessage(), StandardCharsets.UTF_8);

		System.out.println(tmp1);

		ApplicationMessage appMessage = new ApplicationMessage();

		this.sendMessage(appMessage);

	}

	public void reconnected(NodeConnection remoteCon, SocketAddress endPoint, boolean wasHandover,
			boolean wasMandatory) {
	}

	public void disconnected(NodeConnection remoteCon) {
	}

	public void unsentMessages(NodeConnection remoteCon, List<Message> unsentMessages) {
	}

	public void internalException(NodeConnection remoteCon, Exception e) {
	}

	public void sendMessage(ApplicationMessage msg) {
			try {
				connection.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void sendMessageFromString(String grazeId, String info) {
		ApplicationMessage message = new ApplicationMessage();

		String messageText = "[REMETENTE]\t" + this.myUUID.toString();
		messageText = messageText + "\n[ACAO]\tresponse";
		messageText = messageText + "\n[TRACK_ID]\t" + grazeId;
		messageText = messageText + "\n[STATUS]\t" + endTracking;
		messageText = messageText + "\n[OXID]\t" + info;
		try {
			SwapData data = new SwapData();
			data.setTopic("AppModel");

			message = new ApplicationMessage();
			data = new SwapData();
			message.setRecipientID(UUID.fromString(Constants.PN_UUID));
			message.setSenderID(myUUID);

			data.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
			data.setTopic("AppModel");
			message.setContentObject(data);
			connection.sendMessage(message);
			String msgTmp = new String(data.getMessage(), StandardCharsets.UTF_8);
			System.out.println("[sendMessage] [sender] " + msgTmp);

//			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

	public Vector<String> emulate() {
		Vector<String> ids = new Vector<String>();
		UUID tmp = UUID.randomUUID();
		for (int i = 0; i < 6; i++) {
			tmp = UUID.randomUUID();
			ids.insertElementAt(tmp.toString(), i);
		}

		return ids;
	}

}