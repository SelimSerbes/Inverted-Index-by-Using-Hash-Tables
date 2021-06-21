
public class HashTable<K, V> extends AbstractHash<K, V> { 
	static long collusion = 0; 
	static double max = 0;
	static double min = 0;
	static int count = 0;

	protected HashTable() {
		super();
	}

	protected int prime_number(int prime) {
		int primenumber = 0;
		primenumber = prime;
		while (true) {
			int control = 0;
			primenumber = primenumber + 1;
			for (int j = 2; j < primenumber; j++) {
				if (primenumber % j == 0) {
					control = 1;
					break;
				}
			}
			if (control == 0) {
				break;
			}
			
		}
		prime=primenumber;
		return prime;
	}

	protected int hashFunction(int key) {
		return key % table_size;
	}

	protected void get(K key) {
		boolean flag = false;
		int hash = hashFunction(Polynomial_Accumulation_Function(key));
		// linear search

		/*
		 * for (int linear = hash; linear < table_size; linear++) { collusion++; if
		 * (table[linear] != null && table[linear].getKey().equals((String) key)) {
		 * ((SingleLinkedList) table[linear].getValue()).display((String) key); flag =
		 * true; break; }
		 * 
		 * }
		 */

		// double search

		int d = 41 - Polynomial_Accumulation_Function(key) % 41;
		int j = 1;
		while (true) {
			int double_hashing = (hash + j * d) % table_size;
			if (table[double_hashing] != null && table[double_hashing].getKey().equals((String) key)) {
				table[double_hashing].getValue().display((String) key);
				flag = true;
				break;
			}
			if (j > table_size) {
				break;
			}
			j++;
		}

		if (flag == false) {
			System.out.println("Not found!");
		}
	}

	protected void remove(K key) {
		boolean flag = false;
		int hash = hashFunction(Simple_Summation_Function(key));
		// linear search

		/*
		 * for (int linear = hash; linear < table_size; linear++) { collusion++; if
		 * (table[linear] != null && table[linear].getKey().equals((String) key)) {
		 * table[linear].getValue().head.setName("DEFUNCT");
		 * table[linear].getValue().head.setValue(0); table[linear].setKey("DEFUNCT");
		 * flag = true; break; }
		 * 
		 * }
		 */
		// double search
		int d = 41 - Polynomial_Accumulation_Function(key) % 41;
		int j = 1;
		while (true) {
			int double_hashing = (hash + j * d) % table_size;
			if (table[double_hashing] != null && table[double_hashing].getKey().equals((String) key)) {
				table[double_hashing].getValue().head.setName("DEFUNCT");
				table[double_hashing].getValue().head.setValue(0);
				table[double_hashing].setKey("DEFUNCT");
				flag = true;
				break;
			}
			if (j > table_size) {
				break;
			}
			j++;
		}
		if (flag == false) {
			System.out.println("Not found!");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void Linear(int hash, K key, V value) {
		SingleLinkedList values = new SingleLinkedList();
		values = (SingleLinkedList) value;
		for (int linear = hash; linear < table_size; linear++) {
			collusion++;
			if (table[linear] == null) {
				table[linear] = new HashEntry(key, value);
				table[linear].getValue().head.setValue(table[linear].getValue().head.getValue() + 1);
				entry++;
				break;
			} else if (table[linear] != null && table[linear].getKey().equals((String) key)
					&& ((SingleLinkedList) table[linear].getValue()).head.getName() != values.head.getName()) {
				table[linear].getValue().addTohead(values.head.getName(), values.head.getValue());
				table[linear].getValue().head.setValue(table[linear].getValue().head.getValue() + 1);
				break;
			} else if (table[linear] != null && table[linear].getKey().equals((String) key)
					&& table[linear].getValue().head.getName() == values.head.getName()) {
				table[linear].getValue().head.setValue(table[linear].getValue().head.getValue() + 1);
				break;
			}
			if (linear == table_size - 1) {
				linear = 0;
			}
		}
		if (entry > table_size * 0.5) {
			resize(prime_number(2 * table_size));
		}
	}

	@SuppressWarnings("unchecked")
	protected void resize(int capacity) {
		HashEntry<String, SingleLinkedList>[] temp_table = (HashEntry<String, SingleLinkedList>[]) new HashEntry[table_size];
		int temp_size = table_size;
		temp_table = table;
		table_size = capacity;
		table = new HashEntry[table_size];
		entry = 0;
		int size = 0;
		while (size < temp_size) {
			if (temp_table[size] != null) {
				((SingleLinkedList) temp_table[size].getValue()).head
						.setValue(((SingleLinkedList) temp_table[size].getValue()).head.getValue() - 1);
				put((K) temp_table[size].getKey(), (V) temp_table[size].getValue());
			}
			size++;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void Double_Hashing(int hash, K key, V value) {
		SingleLinkedList values = new SingleLinkedList();
		values = (SingleLinkedList) value;
		int d = 41 - Polynomial_Accumulation_Function(key) % 41;

		for (int j = 1; j < table_size; j++) {
			collusion++;
			int double_hashing = (hash + j * d) % table_size;
			if (table[double_hashing] == null) {
				table[double_hashing] = new HashEntry(key, value);
				table[double_hashing].getValue().head.setValue(table[double_hashing].getValue().head.getValue() + 1);
				entry++;
				break;
			} else if (table[double_hashing] != null && table[double_hashing].getKey().equals((String) key)
					&& table[double_hashing].getValue().head.getName() != values.head.getName()) {
				table[double_hashing].getValue().addTohead(values.head.getName(), values.head.getValue());
				table[double_hashing].getValue().head.setValue(table[double_hashing].getValue().head.getValue() + 1);

				break;
			} else if (table[double_hashing] != null && table[double_hashing].getKey().equals((String) key)
					&& table[double_hashing].getValue().head.getName() == values.head.getName()) {
				table[double_hashing].getValue().head.setValue(table[double_hashing].getValue().head.getValue() + 1);
				break;
			}
		}

		if (entry > table_size * 0.5) {
			resize(prime_number(2 * table_size));
		}
	}

	protected void get_with_timing(K key) {
		double start = System.currentTimeMillis();
		get(key);
		double end = System.currentTimeMillis() - start;
		double second2 = (double) end / 1000.0;
		if (count == 0) {
			second2 = min = max;
		}
		if (second2 < min) {
			min = second2;
		}
		if (second2 > max) {
			max = second2;
		}
		System.out.println(second2);
		count++;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void put(K key, V value) {
		SingleLinkedList values = new SingleLinkedList();
		values = (SingleLinkedList) value;
		int hash = hashFunction(Polynomial_Accumulation_Function(key));
		if (table[hash] != null && !table[hash].getKey().equals((String) key)) {

			// linear
			// Linear(hash, key, value);

			// double
			Double_Hashing(hash, key, value);

		} else if (table[hash] != null && table[hash].getKey().equals((String) key)
				&& table[hash].getValue().head.getName() != values.head.getName()) {
			table[hash].getValue().addTohead(values.head.getName(), values.head.getValue());
			table[hash].getValue().head.setValue(table[hash].getValue().head.getValue() + 1);
		} else if (table[hash] != null && table[hash].getKey().equals((String) key)
				&& table[hash].getValue().head.getName() == values.head.getName()) {
			table[hash].getValue().head.setValue(table[hash].getValue().head.getValue() + 1);
		} else {
			table[hash] = new HashEntry(key, value);
			table[hash].getValue().head.setValue(table[hash].getValue().head.getValue() + 1);
			entry++;
			if (entry > table_size * 0.5) {
				resize(prime_number(2 * table_size));
			}
		}

	}
}
