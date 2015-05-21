package server;

import game.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class OutputToClientThread implements Observer {

	private static OutputToClientThread thr;
	ArrayList<InputFromClientsThread> inputs;
	String send = "";
	String name;

	private OutputToClientThread() {
		inputs = new ArrayList<InputFromClientsThread>();
		// System.out.println("out constructor");

	}

	public static OutputToClientThread getOutputToClientThread() {
		if (thr == null) {
			thr = new OutputToClientThread();
		}
		// System.out.println("instance returned");
		return thr;
	}

	public void add(InputFromClientsThread input) {
		this.inputs.add(input);
	}

	@Override
	public void update(Observable o, Object arg) {
		String fromInputThr = arg.toString();
		String toClient = "";
		if (fromInputThr.startsWith("MAKE")) {
			toClient = "MAKE";

			for (InputFromClientsThread input : inputs) {
				toClient += input.getPlayer().toString() + "/";
			}
			System.out.println("Send to client " + toClient);
			for (InputFromClientsThread input : inputs) {
				input.sendToClient().println(toClient);
			}
		} else if (fromInputThr.startsWith("MOVE")) {

			String[] temp = fromInputThr.substring(4).split("/");
			Player tempPl;
			String[] playerInfo;

			for (int i = 0; i < temp.length; i++) {
				playerInfo = null;
				playerInfo = temp[i].split(" ");
				tempPl = new Player();

				tempPl.setName(playerInfo[0]);
				tempPl.setXpos(Integer.parseInt(playerInfo[1]));
				tempPl.setYpos(Integer.parseInt(playerInfo[2]));
				tempPl.setDirection(playerInfo[3]);
				tempPl.setPoint(Integer.parseInt(playerInfo[4]));
				tempPl.setxShoot(Integer.parseInt(playerInfo[5]));
				tempPl.setyShoot(Integer.parseInt(playerInfo[6]));

				for (InputFromClientsThread input : inputs) {
					if (input.getPlayer().getName().equals(tempPl.getName())) {
						input.getPlayer().setXpos(
								Integer.parseInt(playerInfo[1]));
						input.getPlayer().setYpos(
								Integer.parseInt(playerInfo[2]));
						input.getPlayer().setDirection(playerInfo[3]);
						input.getPlayer().setPoint(
								Integer.parseInt(playerInfo[4]));
					}
				}

			}
			toClient = "MOVE";

			for (InputFromClientsThread input : inputs) {
				toClient += input.getPlayer().toString() + "/";
			}

			System.out.println("Send to client " + toClient);
			for (InputFromClientsThread input : inputs) {
				input.sendToClient().println(toClient);
			}

		} else if (fromInputThr.startsWith("FIRE")) {

			String[] temp = fromInputThr.substring(4).split("/");
			Player tempPl;
			String[] playerInfo;

			for (int i = 0; i < temp.length; i++) {
				playerInfo = null;
				playerInfo = temp[i].split(" ");
				tempPl = new Player();

				tempPl.setName(playerInfo[0]);
				tempPl.setXpos(Integer.parseInt(playerInfo[1]));
				tempPl.setYpos(Integer.parseInt(playerInfo[2]));
				tempPl.setDirection(playerInfo[3]);
				tempPl.setPoint(Integer.parseInt(playerInfo[4]));
				tempPl.setxShoot(Integer.parseInt(playerInfo[5]));
				tempPl.setyShoot(Integer.parseInt(playerInfo[6]));

				for (InputFromClientsThread input : inputs) {
					if (input.getPlayer().getName().equals(tempPl.getName())) {
						input.getPlayer().setXpos(
								Integer.parseInt(playerInfo[1]));
						input.getPlayer().setYpos(
								Integer.parseInt(playerInfo[2]));
						input.getPlayer().setDirection(playerInfo[3]);
						input.getPlayer().setPoint(
								Integer.parseInt(playerInfo[4]));
					}
				}

			}
			toClient = "FIRE";

			for (InputFromClientsThread input : inputs) {
				toClient += input.getPlayer().toString() + "/";
			}

			System.out.println("Send to client " + toClient);
			for (InputFromClientsThread input : inputs) {
				input.sendToClient().println(toClient);
			}

		}

	}
}
