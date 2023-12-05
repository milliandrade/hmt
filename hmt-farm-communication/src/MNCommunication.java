
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ckafka.data.SwapData;
import icmu.nodes.GroundNode;
import lac.cnclib.net.NodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;
import main.java.ckafka.mobile.CKMobileNode;
import main.java.ckafka.mobile.tasks.SendLocationTask;

public class MNCommunication extends CKMobileNode {
	private ObjectMapper objectMapper;
	private int stepNumber = 0;

	/**
	 * Constructor
	 */
	public MNCommunication() {
		objectMapper = new ObjectMapper();
	}

	/**
	 * main<br>
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		MNCommunication comm = new MNCommunication();
//		comm.execute();

//		// Inicialização de teste
//		args = new String[8];
//		args[0] = UUID.randomUUID().toString();
//		args[1] = Boolean.toString(false);
//		Vector<String> tmp = comm.emulate();
//		for (int j = 0; j < 6; j++) {
//			args[j + 2] = tmp.get(j);
//		}
//		tmp.add(0, args[0]);
//		tmp.add(1,args[1]);

//		String grazesTmp = "";
//		for (int i = 2; i < args.length; i++) {
//			grazesTmp = grazesTmp + args[i] + "\t";
//		}
//		Vector<String> tmp = new Vector();
//		for (int i=0; i<args.length; i++) {
//			tmp.add(args[i]);
//		}
//		comm.sendMessageToPN(tmp);
//		
//		// Calls close() to properly close MN method after shut down
//		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//			close();
//		}));
//	}

	/**
	 * fazTudo<br>
	 * Read user option from keyboard (unicast or groupcast message)<br>
	 * Read destination receipt from keyboard (UUID or Group)<br>
	 * Read message from keyboard<br>
	 * Send message<br>
	 */
	private void execute() {
		Vector<String> tmp = emulate();

		sendMessageToPN(tmp);
//		sendUnicastMessage(tmp);

//		System.out.println("FIM!");
		System.exit(0);
	}

	/**
	 * Sends a message to processing nodes<br>
	 * 
	 * @param keyboard
	 */
	private void sendMessageToPN(Vector<String> message) {
		String messageText = "";
		ApplicationMessage sentMessage = createDefaultApplicationMessage();
		SwapData data = new SwapData();

		if (message != null) {
			for (int i = 0; i < message.size(); i++) {
				System.out.println("mensgem: " + messageText);
				messageText = message.get(i).toString();
				sentMessage = createDefaultApplicationMessage();
				data = new SwapData();
				data.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
				data.setTopic("AppModel");
				sentMessage.setContentObject(data);
				sendMessageToGateway(sentMessage);
			}
		}
	}

	/**
	 * Sends a unicast message
	 * 
	 * @param keyboard
	 */
	private void sendUnicastMessage(Vector<String> message) {
		System.out
				.println("Mensagem unicast. Entre com o UUID do indivíduo:\n" + "HHHHHHHH-HHHH-HHHH-HHHH-HHHHHHHHHHHH");
		String uuid = "01111111-1111-1111-1111-111111111111";

		SwapData privateData = new SwapData();

		String messageText = "";
		ApplicationMessage sentMessage = createDefaultApplicationMessage();

		if (message != null) {
			for (int i = 0; i < message.size(); i++) {
				messageText = message.get(i).toString();
				sentMessage = createDefaultApplicationMessage();
				privateData = new SwapData();
				privateData.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
				privateData.setTopic("PrivateMessageTopic");
				privateData.setRecipient(uuid);
				sentMessage.setContentObject(privateData);
				sendMessageToGateway(sentMessage);
			}
		}

	}

	/**
	 * sendGroupcastMessage<br>
	 * Sends a groupcast message<br>
	 * 
	 * @param keyboard
	 */
	private void sendGroupcastMessage(Scanner keyboard) {
		// get message content
		String group;
		System.out.print("Mensagem groupcast. Entre com o número do grupo: ");
		group = keyboard.nextLine();
		System.out.print("Entre com a mensagem: ");
		String messageText = keyboard.nextLine();
		System.out.println(String.format("Enviando mensagem |%s| para o grupo %s.", messageText, group));
		// create and send the message
		SwapData groupData = new SwapData();
		groupData.setMessage(messageText.getBytes(StandardCharsets.UTF_8));
		groupData.setTopic("GroupMessageTopic");
		groupData.setRecipient(group);
		ApplicationMessage message = createDefaultApplicationMessage();
		message.setContentObject(groupData);
		sendMessageToGateway(message);
	}

	/**
	 * Method called when the mobile node connects with the Gateway
	 *
	 * @post send location task is scheduled
	 */
	@Override
	public void connected(NodeConnection nodeConnection) {
		try {
			logger.debug("Connected");
			final SendLocationTask sendlocationtask = new SendLocationTask(this);
			this.scheduledFutureLocationTask = this.threadPool.scheduleWithFixedDelay(sendlocationtask, 5000, 60000,
					TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			logger.error("Error scheduling SendLocationTask", e);
		}
	}

	/**
	 * 
	 */
	@Override
	public void newMessageReceived(NodeConnection nodeConnection, Message message) {
		logger.debug("New Message Received");
		try {
			SwapData swp = fromMessageToSwapData(message);
			if (swp.getTopic().equals("Ping")) {
				message.setSenderID(this.mnID);
				sendMessageToGateway(message);
			} else {
				String str = new String(swp.getMessage(), StandardCharsets.UTF_8);
				logger.info(String.format("Message received from %s: %s", message.getRecipientID(), str));
			}
		} catch (Exception e) {
			logger.error("Error reading new message received");
		}
	}

	@Override
	public void disconnected(NodeConnection nodeConnection) {
	}

	@Override
	public void unsentMessages(NodeConnection nodeConnection, List<Message> list) {
	}

	@Override
	public void internalException(NodeConnection nodeConnection, Exception e) {
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
