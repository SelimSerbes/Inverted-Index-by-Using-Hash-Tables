import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileOperations {
	private BufferedReader br;
	public static Main words=new Main();
	 String[] stopwords;
	 static HashTable<String, SingleLinkedList> hashTable_words = new HashTable<String, SingleLinkedList>();
		static Scanner scan = new Scanner(System.in);
		static File folder = new File("bbc");
	 String DELIMITERS = "[-+=" + " " + // space
			"\r\n " + // carriage return line fit
			"1234567890" + // numbers
			"’'\"" + // apostrophe
			"(){}<>\\[\\]" + // brackets
			":" + // colon
			"," + // comma
			"‒–—―" + // dashes
			"…" + // ellipsis
			"!" + // exclamation mark
			"." + // full stop/period
			"«»" + // guillemets
			"-‐" + // hyphen
			"?" + // question mark
			"‘’“”" + // quotation marks
			";" + // semicolon
			"/" + // slash/stroke
			"⁄" + // solidus
			"␠" + // space?
			"·" + // interpunct
			"&" + // ampersand
			"@" + // at sign
			"*" + // asterisk
			"\\" + // backslash
			"•" + // bullet
			"^" + // caret
			"¤¢$€£¥₩₪" + // currency
			"†‡" + // dagger
			"°" + // degree
			"¡" + // inverted exclamation point
			"¿" + // inverted question mark
			"¬" + // negation
			"#" + // number sign (hashtag)
			"№" + // numero sign ()
			"%‰‱" + // percent and related signs
			"¶" + // pilcrow
			"′" + // prime
			"§" + // section sign
			"~" + // tilde/swung dash
			"¨" + // umlaut/diaeresis
			"_" + // underscore/understrike
			"|¦" + // vertical/pipe/broken bar
			"⁂" + // asterism
			"☞" + // index/fist
			"∴" + // therefore sign
			"‽" + // interrobang
			"※" + // reference mark
			"]";
	
	
	public void read_stopwords(String input_file) throws IOException{
		try {
		File file = new File(input_file);
		br = new BufferedReader(new FileReader(file));
		@SuppressWarnings("unused")
		String row;
		int i=0;
		while ((row = br.readLine()) != null) {
			i++;
		}
		stopwords=new String[i];
		i=0;
		br.close();
		br = new BufferedReader(new FileReader(file));
		row="";
		while ((row = br.readLine()) != null) {
			i++;
		}
		br.close();
		
		}
		catch(Exception e){
			System.out.println("ERROR !");
		}
	}
	
	public String delete_stop_words(String text) throws IOException {
		text=text.replace("I", "i");
		text=text.toLowerCase();
		String[] splitted = text.split(" ");
		read_stopwords("stop_words_en.txt");
		for (int i = 0; i < splitted.length; i++) {
			for (int j = 0; j < stopwords.length; j++) {
				if(splitted[i].equals(stopwords[j])) {
					splitted[i]="";
				}
			}
		}
		String text2="";
		for (int i = 0; i < splitted.length; i++) {
			if (!splitted[i].equals("")) {
				text2+=splitted[i]+" ";
			}
		}
		return text2;
	}
	public  String delete_delimiters(String text) {
		String[] splitted = text.split(DELIMITERS);
		String text2="";
		for (int i = 0; i <splitted.length; i++) {
			if (!splitted[i].equals("")) {
				text2+=splitted[i].toLowerCase().trim()+" ";
			}
		}
		return text2.trim();
	}
	
	public void listFilesForFolder(File folder) throws IOException {
	  try {
		 for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	String path=fileEntry.getPath();
	        	File file = new File(path);
	        	String temp_path=path.replace("bbc\\", "");
	        	BufferedReader br = new BufferedReader(new FileReader(file));
	    		String row;
	    		while ((row = br.readLine()) != null) {
	    			String text=delete_delimiters(delete_stop_words(row));
	    			String[] splittedtext=text.split(" ");
	    			for (int i = 0; i < splittedtext.length; i++) {
	    				if(splittedtext[i]!="" && splittedtext[i].length()>1) {
	    				SingleLinkedList path_value=new SingleLinkedList();
	    				path_value.addToEnd(temp_path, 0);
	    				hashTable_words.put(splittedtext[i], path_value);
	    				}
					}
	    		}
	    		br.close();
	        }
	    }
	  }
	  catch(Exception e) {
		  System.out.println("ERROR !");
	  }
	}
	
	
	public void scan() throws IOException {
		try {
		File file = new File("1000.txt");
		br = new BufferedReader(new FileReader(file));
		String row;
		while ((row = br.readLine()) != null) {
			hashTable_words.get_with_timing(row.toLowerCase());
		}
		br.close();
		}
		catch(Exception e) {
			System.out.println("ERROR !");
		}
	}	
}
