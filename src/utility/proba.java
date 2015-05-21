package utility;

import java.util.ArrayList;

public class proba {

	public static void main(String[] args) {
		ArrayList<ArrayStack<String>> screen2 = initialize();

		for (int i = 0; i < screen2.size(); i++) {
			System.out.print(i + " " + screen2.get(i).peek() + " ");
			if (i == 19 || i == 39 || i == 59 || i == 79 || i == 99 || i == 119
					|| i == 139 || i == 159 || i == 179 || i == 199 || i == 219
					|| i == 239 || i == 259 || i == 279 || i == 299 || i == 319
					|| i == 339 || i == 359 || i == 379 || i == 399) {

				System.out.println("\n");
			}
		}

		System.out.println(screen[3][4]);
		System.out.println(screen2.get(3 * 20 + 4).peek());
	}

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

	private static ArrayList<ArrayStack<String>> initialize() {
		ArrayList<ArrayStack<String>> screen2 = new ArrayList<ArrayStack<String>>();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				screen2.add(new ArrayStack<String>(screen[i][j]));
			}
		}
		return screen2;
	}
}
