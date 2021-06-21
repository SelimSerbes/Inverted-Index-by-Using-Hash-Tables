import java.io.IOException;


public class Main {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		FileOperations a = new FileOperations();
		
		//timing operation
		double start = System.currentTimeMillis();
		a.listFilesForFolder(a.folder);
		double end = System.currentTimeMillis() - start;
		double second = end / 1000;
		
		//timing operation
		double start2 = System.currentTimeMillis();
		a.scan();
		double end2 = System.currentTimeMillis() - start2;
		double second2 = end2 / 1000.0;
		
		System.out.println("\ntotal search time = " + second2 + " max = " + a.hashTable_words.max + " min = "
				+ a.hashTable_words.min);

		System.out.println("\ntotal entry = " + a.hashTable_words.entry + " table size = " + a.hashTable_words.table_size
				+ " collusion = " + a.hashTable_words.collusion + " time = " + second);
		
		System.out.println("\nType 'x' for exit !!!");
		
		while (true) {
			try {
				System.out.print("\n>Search: ");
				String input = a.scan.nextLine();
				if (input.equals("x"))
					break;
				a.hashTable_words.get(input);
			} catch (Exception e) {
				System.out.println("Wrong input");
			}
		}
	}
}
