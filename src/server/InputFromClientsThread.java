package server;

import game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class InputFromClientsThread extends Observable implements Runnable {

	private Thread thrInput;
	private Socket connection;
	private BufferedReader in;
	private PrintWriter out;
	private OutputToClientThread output;
	private Player player;

	InputFromClientsThread(Socket connection, OutputToClientThread output)
			throws IOException {
		this.player = new Player();
		this.connection = connection;
		this.output = output;
		out = new PrintWriter(this.connection.getOutputStream(), true);
		startThread();
		// System.out.println("input constructor");
	}

	public void startThread() {
		thrInput = new Thread(this);
		output.add(this);
		addObserver(output);
		thrInput.start();
	}

	public PrintWriter sendToClient() {
		return out;
	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
		} catch (IOException e1) {
			System.out.println("buffer");
			e1.printStackTrace();
		}

		String fromUser = null;
		String toOutputThr = "";
		while (true) {
			try {
				fromUser = in.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			while (fromUser != null) {
				if (fromUser.startsWith("NAME")) {
					System.out.println("Received from client " + fromUser);

					player.setName(fromUser.substring(4));
					this.player = Server.initilizePlayer(player);

					toOutputThr = "MAKE" + player;
					System.out.println("Send to outputToclient thread "
							+ toOutputThr);

					setChanged();
					notifyObservers(toOutputThr);
					toOutputThr = null;
					fromUser = null;

				} else if (fromUser.startsWith("MOVE")) {
					System.out.println(fromUser);
					Player temp = this.player;
					temp.setDirection(fromUser.substring(4));

					ArrayList<Player> checkedMoves = Server.legalMove(temp);
					toOutputThr = "MOVE";

					for (Player checkedPlayer : checkedMoves) {
						toOutputThr += checkedPlayer.toString() + "/";
					}

					System.out.println("Send to output thread " + toOutputThr);
					setChanged();
					notifyObservers(toOutputThr);
					toOutputThr = null;
					fromUser = null;

				} else if (fromUser.startsWith("FIRE")) {
					System.out.println(fromUser);

					ArrayList<Player> checkedMoves = Server
							.legalShot(this.player);
					toOutputThr = "FIRE";

					for (Player checkedPlayer : checkedMoves) {
						toOutputThr += checkedPlayer.toString() + "/";
					}

					System.out.println("Send to output thread " + toOutputThr);
					setChanged();
					notifyObservers(toOutputThr);
					toOutputThr = null;
					fromUser = null;

				}

				toOutputThr = null;
				fromUser = null;
			}
		}
	}
}
