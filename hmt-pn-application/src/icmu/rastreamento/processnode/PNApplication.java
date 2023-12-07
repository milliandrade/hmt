package icmu.rastreamento.processnode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.SystemScheduler;
import org.apache.kafka.common.utils.Time;

import com.fasterxml.jackson.databind.ObjectMapper;

import ckafka.data.Swap;
import ckafka.data.SwapData;
import icmu.management.aplication.Farm;
import icmu.util.Graze;
import icmu.util.Ox;
import icmu.util.Constants;
import main.java.application.ModelApplication;

public class PNApplication extends ModelApplication {
	private Swap swap;
	private ObjectMapper objectMapper;
	private Farm farm;

	public PNApplication() {
		this.objectMapper = new ObjectMapper();
		this.swap = new Swap(objectMapper);
		// this.farm = new Farm();
		this.farm = new Farm("HMT Farm System");
		// this.farm.initializeFarm();
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null && !args[i].equalsIgnoreCase("")) {
					// System.out.println(i + ":" + args[i]);
					switch (i) {
						case 0:// args[0] => data_base_address
							Constants.setMYSQL_ADDRESS(args[i]);
							break;
						case 1:// args[1] => db_username
							Constants.setUSERNAME(args[i]);
							break;
						case 2:// args[2] => db_password
							Constants.setPASSWD(args[i]);
							break;
						case 3:// args[3] => gatway_ip_address
							Constants.setGATEWAY_IP_ADDRESS(args[i]);
							break;
						case 4:// args[4] => gatway_ip
							Constants.setGATEWAY_IP(args[i]);
							break;
						default:
							break;
					}
				}
			}

		}
		PNApplication pna = new PNApplication();
		System.out.println("execução...");
		pna.ApplicationBehavior();
	}

	public Swap getSwap() {
		return swap;
	}

	public void setSwap(Swap swap) {
		this.swap = swap;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public void ApplicationBehavior() {
		String uuid = Constants.GROUND_STATION_UUID;
		// String uuid = "02222222-2222-2222-2222-222222222222";
		// Farm farm = new Farm("Teste");
		boolean finish = false;
		try {
			int optionNotify = 0;
			Ox tmpOx = null;
			while (!finish) {
				// System.out.flush();
				String consulta = "SELECT notify FROM farm WHERE name=\'" + this.getFarm().getName() + "\';";

				ResultSet resultado = this.getFarm().getQuery().executeQuery(consulta);
				while (resultado.next()) {
					optionNotify = resultado.getInt("notify");
				}
				switch (optionNotify) {
					case 0:
						// Não houve alteração
						// System.out.println("sem alterações...");
						this.wait(1000000);
						break;
					case 1:
						// Solicitação de rastreamento
						System.out.println("solicitado o rastreamento...");

						consulta = "UPDATE farm SET notify = \'0\';";
						this.getFarm().getQuery().executeUpdate(consulta);

						this.sendUnicastMessage(new Vector<String>());

						break;
					case 2:
						// Inseriu um novo boi
						System.out.println("boi inserido...");
						this.getFarm().actualizeFarmFromBD();

						consulta = "UPDATE farm SET notify = \'0\';";
						this.getFarm().getQuery().executeUpdate(consulta);

						break;
					case 3:
						// TESTE ::: Inseriu um novo boi
						System.out.println("adicionando graze...");
						Graze tmp = new Graze();
						this.farm.addGraze(tmp);
						this.farm.insertOxIntoGraze(tmp.getId(), this.getFarm().getOxen().get(0).getId());
						this.farm.insertOxIntoGraze(tmp.getId(), this.getFarm().getOxen().get(1).getId());
						this.farm.insertOxIntoGraze(tmp.getId(), this.getFarm().getOxen().get(2).getId());
						this.farm.insertOxIntoGraze(tmp.getId(), this.getFarm().getOxen().get(3).getId());
						this.farm.insertOxIntoGraze(tmp.getId(), this.getFarm().getOxen().get(4).getId());
						tmp.setEnd(LocalDateTime.now());
						consulta = tmp.updateGraze();
						// this.farm.printOxen();
						this.farm.getQuery().executeUpdate(consulta);
						for (int i = 0; i < this.getFarm().getGrazesById(tmp.getId()).getPresentIDs().size(); i++) {
							System.out.println(this.getFarm().getGrazesById(tmp.getId()).getPresentIDs().get(i));
						}
						String[] tmpP = tmp.insertPresents().split("\n");
						for (int i = 0; i < tmpP.length; i++) {
							consulta = tmpP[i];
							this.farm.getQuery().executeUpdate(consulta);
						}

						String[] tmpA = tmp.insertAbsentsToBD().split("\n");
						for (int i = 0; i < tmpA.length; i++) {
							consulta = tmpA[i];
							System.out.println("absent ox to BD:: " + consulta);
							this.farm.getQuery().executeUpdate(consulta);
						}

						break;
					default:
						// sair
						System.out.println("...");
						this.wait(1000000);
						finish = true;
						System.out.println("FIM!");
						System.exit(0);
						break;
				}

				Graze tmpGraze = this.getFarm().getGrazes().get(this.getFarm().getGrazes().size() - 1);
				if (tmpGraze != null && tmpGraze.getEnd() == null && (LocalDateTime.now().getMinute()
						- tmpGraze.getStart().getMinute() >= Constants.GRAZE_TIMEOUT)) {
					tmpGraze.setEnd(LocalDateTime.now());
					this.farm.finishGrazeUpdate(tmpGraze);
					this.farm.getQuery().executeUpdate(tmpGraze.updateGraze());
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	private void sendUnicastMessage(Vector<String> msg) {
		// private void sendUnicastMessage(String msg) {
		// System.out.println("UUID:\nHHHHHHHH-HHHH-HHHH-HHHH-HHHHHHHHHHHH");
		try {
			String messageText = "[REMETENTE]\t" + Constants.PN_UUID + "\n";
			messageText = messageText + "[ACAO]tracking\n";
			messageText = messageText + "[DESTINO]\t" + Constants.GROUND_STATION_UUID + "\n";
			messageText = messageText + "[DRONES]" + this.getFarm().getDronesList();
			Graze tmpGraze = new Graze();
			messageText = messageText + "\n[TRACK_ID]\t" + tmpGraze.getId();
			this.farm.setActualTrackingId(tmpGraze.getId());
			// for (int i = 0; i < msg.size(); i++) {
			// messageText = msg.get(i);
			// System.out.print("Message: \n" + messageText);
			// System.out.println(String.format("Sending |%s| to %s", messageText,
			// Constants.GROUND_STATION_UUID));
			// }
			this.farm.fileDrones(tmpGraze.getId());
			this.farm.addGraze(tmpGraze);
			// String consulta = "UPDATE farm SET notify = \'0\';";
			// this.getFarm().getQuery().executeUpdate(tmpGraze.saveNewGraze());

			sendRecord(createRecord("PrivateMessageTopic", Constants.GROUND_STATION_UUID,
					swap.SwapDataSerialization(createSwapData(messageText))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recordReceived(ConsumerRecord arg0) {
		// this.logger.debug("Record Received " + arg0.value().toString());
		if (this.farm != null) {
			System.out.print("[PN] [receivedMessage] :\n");

			try {
				SwapData data = swap.SwapDataDeserialization((byte[]) arg0.value());
				String msg = new String(data.getMessage(), StandardCharsets.UTF_8);
				System.out.println("Mensagem recebida = \n" + msg);

				// System.out.println(String.format("Mensagem recebida de %s", arg0.key()));
				String[] lines = msg.split("\n");

				if (lines.length > 2) {
					String sender = lines[0].substring(12);
					String action = lines[1].substring(7);
					// System.out.println("action: " + action);
					switch (action) {
						case "response":
							// String track_id = lines[2].substring(11);
							String track_id = this.farm.getActualTrackingId();
							// System.out.println(track_id);
							// if (track_id!=null && track_id.equalsIgnoreCase("null")) {
							// System.out.println(lines[4].substring(7));
							// track_id = this.getFarm().getGrazes().get(this.farm.getGrazes().size() -
							// 1).getId();
							// this.getFarm().getGrazes().get(this.farm.getGrazes().size() -
							// 1).setEnd(null);
							// }
							boolean status = Boolean.parseBoolean(lines[3].substring(9));
							String oxen = lines[4].substring(7);
							String[] msgReceived = oxen.split("\t");
							// System.out.println(msgReceived[0]);
							Graze tmpGraze;
							if (msgReceived != null && msgReceived.length >= 1) {

								// if (this.getFarm() != null && !this.getFarm().hasGraze(track_id)) {
								// System.out.println("não achou o ID : " + track_id);
								//// tmpGraze = new Graze();
								// tmpGraze = this.getFarm().getGrazes().get(this.farm.getGrazes().size() - 1);
								// tmpGraze.setId(track_id);
								//// this.getFarm().addGraze(tmpGraze);
								// }
								// else {

								tmpGraze = this.farm.getGrazesById(track_id);

								// if (tmpGraze!=null) {
								// System.out.println(tmpGraze.toString() + "\t id: "+track_id);
								// }
								// }
								if (tmpGraze.getEnd() == null) {

									for (int i = 0; i < msgReceived.length; i++) {

										if (!msgReceived[i].equalsIgnoreCase("Sem informações sobre rastreamento")) {
											// System.out.println(i + "::ox received: " + msgReceived[i]);
											if (!tmpGraze.hasPresentID(msgReceived[i])) {
												tmpGraze.insertPresentID(msgReceived[i]);
												this.getFarm().getQuery()
														.executeUpdate(tmpGraze.insertPresentIDtoBD(msgReceived[i]));
											}

											// this.getFarm().insertOxIntoGraze(track_id, msgReceived[i]);
											// this.farm.getQuery()
											// .executeUpdate(tmpGraze.insertPresentIDtoBD(msgReceived[i]));
										}
									}
									// System.out.println(tmpGraze.updateGraze());
									// this.farm.getQuery().executeUpdate(tmpGraze.updateGraze());
								}
								System.out.println("Graze em andamento: " + tmpGraze.toString());
								if (/* status || */(tmpGraze.getEnd() == null && (LocalDateTime.now().getMinute()
										- tmpGraze.getStart().getMinute() >= Constants.GRAZE_TIMEOUT))) {
									tmpGraze.setEnd(LocalDateTime.now());
									this.farm.finishGrazeUpdate(tmpGraze);
								}
							}
							break;
						case "alive":
							break;
						default:
							break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void wait(int i) {
		for (int j = 0; j < i; j++)
			;
	}

}
