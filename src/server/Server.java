package server;

import game.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utility.ArrayStack;

public class Server {

	private static ArrayList<Player> players = new ArrayList<Player>();
	private static String[][] screen = {
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
					"w", "w", "w", "w", "w", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e",
					"e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e",
					"e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w",
					"e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w",
					"e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e",
					"e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e",
					"e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w",
					"e", "e", "w", "w", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w",
					"e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w",
					"e", "e", "e", "e", "e", "e", "w" },
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
					"w", "w", "w", "w", "w", "w", "w" } };
	// level is defined column by column

	private final static String[] coordinates = { "1 3", "1 6", "1 8", "1 16",
			"2 3", "2 7", "2 13", "2 17", "1 3", "3 8", "3 13", "3 16", "4 1",
			"4 5", "4 11", "4 18", "5 3", "5 9", "5 16", "6 3", "6 7", "6 14",
			"6 17", "7 1", "7 5", "7 17", "8 2", "8 5", "8 13", "8 18", "9 2",
			"9 5", "9 10", "9 16", "10 4", "10 9", "10 12", "10 14", "11 2",
			"11 6", "11 11", "11 17", "12 1", "12 4", "12 7", "12 13", "13 7",
			"13 10", "13 12", "13 16", "14 1", "14 6", "14 8", "14 12", "15 2",
			"15 4", "15 11", "15 14", "16 5", "16 8", "16 11", "17 1", "17 8",
			"17 10", "17 14", "18 2", "18 7", "18 12", "18 12", "18 15" };
	private final static String[] direction = { "up", "down", "left", "right" };

	private static ArrayList<ArrayStack<String>> screen2 = screenToStacks();

	private static ArrayList<ArrayStack<String>> screenToStacks() {
		ArrayList<ArrayStack<String>> temp = new ArrayList<ArrayStack<String>>();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				temp.add(new ArrayStack<String>(screen[i][j]));
			}
		}
		return temp;
	}

	public static Player initilizePlayer(Player player) {
		int rand = (int) (Math.random() * (coordinates.length - 1));
		String[] playerCoord = coordinates[rand].split(" ");

		player.setXpos(Integer.parseInt(playerCoord[0]));
		player.setYpos(Integer.parseInt(playerCoord[1]));

		rand = (int) (Math.random() * (direction.length - 1));
		player.setDirection(direction[rand]);
		addPlayerToScreen(player);
		return player;
	}

	static void addPlayerToScreen(Player player) {
		screen2.get(player.getXpos() * 20 + player.getYpos()).push(
				player.getName());

		System.out.println("Player " + player.getName() + " was set on "
				+ player.getXpos() + " " + player.getYpos());

		int count = 0;
		for (Player pl : players) {
			if (!pl.getName().equals(player.getName())) {
				count++;
			}
		}
		System.out.println("count " + count);
		System.out.println("size " + count);
		if (count == players.size()) {
			player = player;
			players.add(player);
			System.out.println(player + " was added");
		} else {
			int index = players.indexOf(player);
			players.get(index).setXpos(player.getXpos());
			players.get(index).setYpos(player.getYpos());
		}

	}

	public static synchronized ArrayList<Player> legalMove(Player player) {

		int x = player.getXpos(), y = player.getYpos();
		String direction = player.getDirection();

		if (direction.equals("right")) {
			x = player.getXpos() + 1;
		}
		if (direction.equals("left")) {
			x = player.getXpos() - 1;
		}
		if (direction.equals("up")) {
			y = player.getYpos() - 1;
		}
		if (direction.equals("down")) {
			y = player.getYpos() + 1;
		}

		int index = players.indexOf(player);
		System.out.println(players.get(index));

		System.out.println("Coordinates to move to "
				+ screen2.get(x * 20 + y).peek() + " are " + x + " " + y);

		if (screen2.get(x * 20 + y).peek().equals("w")) {
			System.out.println("w");
			players.get(index).subOnePoint();

		} else if (screen2.get(x * 20 + y).peek().equals("e")) {
			System.out.println("e");
			players.get(index).addOnePoint();

			if (screen2.get(player.getXpos() * 20 + player.getYpos()).peek()
					.equals(player.getName())) {
				screen2.get(player.getXpos() * 20 + player.getYpos()).pop();
			} else {
				ArrayStack<String> temp = new ArrayStack<String>();
				while (!screen2.get(player.getXpos() * 20 + player.getYpos())
						.peek().equals(player.getName())) {
					temp.push(screen2.get(
							player.getXpos() * 20 + player.getYpos()).pop());
				}
				screen2.get(player.getXpos() * 20 + player.getYpos()).pop();

				for (int i = 0; i < temp.size(); i++) {
					screen2.get(player.getXpos() * 20 + player.getYpos()).push(
							temp.pop());

				}
			}

			players.get(index).setXpos(x);
			players.get(index).setYpos(y);
			players.get(index).setDirection(direction);

			screen2.get(x * 20 + y).push(players.get(index).getName());

		} else {
			ArrayStack<String> crashed = screen2.get(x * 20 + y);
			ArrayStack<String> temp = new ArrayStack<String>();

			players.get(index).addPoints(50 * (crashed.size() - 1));
			int indexCrashed = 0;

			for (Player player2 : players) {
				if (player2.getName().equals(crashed.peek())) {
					indexCrashed = players.indexOf(player2);
					players.get(indexCrashed).removePoints(50);
					temp.push(crashed.pop());
					System.out.println("popped " + temp);
					System.out.println("peeked " + crashed.peek());
				}
			}

			for (int i = 0; i < temp.size(); i++) {
				screen2.get(x * 20 + y).push(temp.pop());
			}

			System.out.println("Size of crashed " + crashed.size());

			if (screen2.get(player.getXpos() * 20 + player.getYpos()).peek()
					.equals(player.getName())) {
				screen2.get(player.getXpos() * 20 + player.getYpos()).pop();
			} else {
				ArrayStack<String> temp2 = new ArrayStack<String>();
				while (!screen2.get(player.getXpos() * 20 + player.getYpos())
						.peek().equals(player.getName())) {
					temp2.push(screen2.get(
							player.getXpos() * 20 + player.getYpos()).pop());
				}
				screen2.get(player.getXpos() * 20 + player.getYpos()).pop();

				for (int i = 0; i < temp2.size(); i++) {
					screen2.get(player.getXpos() * 20 + player.getYpos()).push(
							temp2.pop());

				}
			}

			players.get(index).setXpos(x);
			players.get(index).setYpos(y);
			players.get(index).setDirection(direction);

			screen2.get(x * 20 + y).push(players.get(index).getName());

		}
		return players;
	}

	public static synchronized ArrayList<Player> legalShot(Player player) {
		int x = player.getXpos(), y = player.getYpos();
		int index = players.indexOf(player);

		if (player.getDirection().equals("right")) {
			x++;
			while (screen2.get(20 * x + y).peek().equals("e")) {
				x++;
			}
		} else if (player.getDirection().equals("left")) {
			x--;
			while (screen2.get(20 * x + y).peek().equals("e")) {
				x--;
			}
		} else if (player.getDirection().equals("up")) {
			y--;
			while (screen2.get(20 * x + y).peek().equals("e")) {
				y--;
			}
		} else if (player.getDirection().equals("down")) {
			y++;
			while (screen2.get(20 * x + y).peek().equals("e")) {
				y++;
			}
		}

		players.get(index).setxShoot(x);
		players.get(index).setyShoot(y);
		System.out.println("shots to " + x + " " + y);
		if (screen2.get(20 * x + y).peek().equals("w")) {

			return players;
		}

		ArrayStack<String> crashed = screen2.get(x * 20 + y);

		int indexCrashed = 0;
		int count = crashed.size();

		for (int i = 0; i < count; i++) {

			for (Player player2 : players) {
				System.out.println("player from the arraylist " + player2);
				System.out.println("top of crashed players " + crashed.peek());
				if (player2.getName().equals(crashed.peek())) {
					indexCrashed = players.indexOf(player2);
					players.get(index).addPoints(player2.getPoint() / 3);
					players.get(indexCrashed).setPoint(0);
					crashed.pop();
					initilizePlayer(player2);
				}
			}
		}

		System.out.println("Size of crashed " + crashed.size());

		return players;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Server waiting for clients...");
		ServerSocket welcome = new ServerSocket(7913);
		Socket connection;
		InputFromClientsThread in;
		OutputToClientThread out = OutputToClientThread
				.getOutputToClientThread();

		while (true) {
			connection = welcome.accept();
			System.out.println("Player connected");
			in = new InputFromClientsThread(connection, out);
		}

	}
}
