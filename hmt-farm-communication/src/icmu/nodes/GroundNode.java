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
import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;

public class GroundNode implements NodeConnectionListener {

	private static String gatewayIP = Constants.GATEWAY_IP_ADDRESS;
	private static int gatewayPort = Constants.GATEWAY_PORT_NUMBER;
	private MrUdpNodeConnection connection;
	private UUID myUUID;
	public String grazeInfo;

	public GroundNode() {
		myUUID = UUID.fromString("01111111-1111-1111-1111-0000000000");

		InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
		System.out.println("CONECTANDO-SE AO GATEWAY:: " + gatewayIP + "\t" + gatewayPort);
		try {
			connection = new MrUdpNodeConnection(myUUID);
			connection.connect(address);
			for (int i = 0; i < 10000; i++);
			sendMessage(null);
			connection.addNodeConnectionListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GroundNode(String myUUID, boolean end, String graze) {
		this.myUUID = UUID.fromString(myUUID);
		this.grazeInfo = graze;
		InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
		System.out.println("Inicializou o InetSocketAddress com: \t" + gatewayIP + "\t" + gatewayPort);
		try {
			connection = new MrUdpNodeConnection(this.myUUID);
//			connection.addNodeConnectionListener(this);
			connection.connect(address);
			for (int i = 0; i < 10000; i++) {

			}
			sendMessageFromString(graze);
			for (int i = 0; i < 10000; i++) {

			}
			connection.removeNodeConnectionListener(this);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.INFO);

		GroundNode node = new GroundNode();
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
		System.out.print("[GroundNode] [receivedMessage] :\t");

		SwapData dataReceived = (SwapData) message.getContentObject();
		String tmp1 = new String(dataReceived.getMessage(), StandardCharsets.UTF_8);

		System.out.print(tmp1);
		String[] lines = tmp1.split("\n");
		String drones = "";
		String trackId = "";
		if (lines.length > 3) {
			drones = lines[3].substring(9);
//			System.out.println("drones:" + drones);
			trackId = lines[4].substring(10);
//			System.out.println("trackid:" + trackId);
		}

		String trackingPck = drones + "\n" + trackId;

		for (int i = 0; i < 5000; i++)
			;

		this.sendMessageAck();

		for (int i = 0; i < 10000; i++)
			;

		connection.removeNodeConnectionListener(this);
		System.out.print(trackingPck);
		System.exit(0);
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
		ApplicationMessage message = new ApplicationMessage();

		String messageText = "[REMETENTE]\t" + myUUID.toString() + "\n";
		messageText = messageText + "[ACAO]\t alive \n";

		try {
			SwapData data = new SwapData();
			data.setTopic("AppModel");

			message = new ApplicationMessage();
			data = new SwapData();
			message.setRecipientID(UUID.fromString("01111111-1111-1111-1111-111111111111"));
			message.setSenderID(myUUID);

			data.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
			data.setTopic("AppModel");
			message.setContentObject(data);
			connection.sendMessage(message);
			String msgTmp = new String(data.getMessage(), StandardCharsets.UTF_8);
			System.out.println("[sendMessage] [sender] " + msgTmp);

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageAck() {
		ApplicationMessage message = new ApplicationMessage();

		String messageText = "[REMETENTE]\t" + myUUID.toString() + "\n";
		messageText = messageText + "[ACAO]\t ack \n";

		try {
			SwapData data = new SwapData();
			data.setTopic("AppModel");

			message = new ApplicationMessage();
			data = new SwapData();
			message.setRecipientID(UUID.fromString("01111111-1111-1111-1111-111111111111"));
			message.setSenderID(myUUID);

			data.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
			data.setTopic("AppModel");
			message.setContentObject(data);
			connection.sendMessage(message);
			String msgTmp = new String(data.getMessage(), StandardCharsets.UTF_8);
			System.out.println("[sendMessage] [sender] " + msgTmp);

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageFromString(String info) {
		ApplicationMessage message = new ApplicationMessage();

		String messageText = "\t";
		try {
			SwapData data = new SwapData();
			data.setTopic("AppModel");

//			Vector<String> tmpGraze = NodeSender.initGraze();

			messageText = messageText + System.currentTimeMillis() + "\n";
			messageText = messageText + info;

//			if (tmpGraze != null && tmpGraze.size() > 0) {
//				for (int i = 0; i < tmpGraze.size(); i++) {
//					messageText = messageText + tmpGraze.get(i) + "\t";
//				}
			message = new ApplicationMessage();
			data = new SwapData();
			message.setRecipientID(UUID.fromString("01111111-1111-1111-1111-111111111111"));
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