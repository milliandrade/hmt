package icmu.nodes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import ckafka.data.SwapData;
import icmu.util.NodeMessage;
import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;

public class NodeSender implements NodeConnectionListener {

	private static String gatewayIP = "127.0.0.1";
	private static int gatewayPort = 5500;
	private MrUdpNodeConnection connection;
	private UUID myUUID;

	public NodeSender() {
		myUUID = UUID.fromString("02222222-2222-2222-2222-222222222222");
		InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
		try {
			System.out.println("Inicializando o NodeSender");
			connection = new MrUdpNodeConnection(myUUID);
			System.out.println("Criou o MrUdp");
			connection.addNodeConnectionListener(this);
			System.out.println("adicionou o listener");
			connection.connect(address);
			System.out.println("Realizou a conexão");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			NodeMessage msg = new NodeMessage();
//			msg.setGrazeInformation(initGraze());
			msg.setGroundStationID("02222222-2222-2222-2222-222222222222");
			Logger.getLogger("").setLevel(Level.ALL);

			NodeSender sender = new NodeSender();
			System.out.println("Iniciando...");
			sender.sendMessage(msg);
			System.out.println("executou o sendMessage");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(NodeMessage msg) {
		ApplicationMessage message = new ApplicationMessage();
		message.setRecipientID(UUID.fromString("01111111-1111-1111-1111-111111111111"));
		message.setSenderID(myUUID);

		String messageText = "";
		try {
			SwapData data = new SwapData();
			data.setTopic("AppModel");
			Vector<String> tmpGraze = msg.getGrazeInformation();
			System.out.println("sendMessage");
			if (tmpGraze != null && tmpGraze.size() > 0) {
				for (int i = 0; i < tmpGraze.size(); i++) {
					System.out.println("mensgem: " + messageText);
					messageText = tmpGraze.get(i);
					System.out.println("Gateway ID" + message.getRecipientGatewayID());
					System.out.println("Recipient ID" + message.getRecipientID());
					System.out.println("Sender ID" + message.getSenderID());

					message = new ApplicationMessage();
					data = new SwapData();
					data.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
//					data.setTopic("Graze : ");
					message.setContentObject(data);
					connection.sendMessage(message);
					System.out.println("[sender] " + messageText);
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public void connected(NodeConnection remoteCon) {
		ApplicationMessage message = new ApplicationMessage();
		message.setContentObject("Registering");

		try {
			connection.sendMessage(message);
			System.out.println("[sender]conexão registrada");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newMessageReceived(NodeConnection remoteCon, Message message) {
		System.out.println("Sender received the message!!");
		System.out.println(message.getContent());
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

	public static Vector<String> initGraze() throws IOException {
		Vector<String> grazes = new Vector<String>();
		BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\mille\\eclipse-workspace\\ICMU-T1-rastreamento-cliente\\src\\icmu\\util\\grazes.txt"));

		String line = br.readLine();
//		Date tmpDate = null;
		while (line != null) {
//			tmpDate = new Date();
			grazes.add(line);
			line = br.readLine();
		}
		br.close();
		return grazes;
	}
}