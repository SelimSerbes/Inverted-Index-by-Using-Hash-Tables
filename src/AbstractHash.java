
public abstract class AbstractHash<K, V> {
	protected static int table_size;
	protected static int entry;
	HashEntry<String,SingleLinkedList>[] table;

	
	AbstractHash() {
		table_size=128;
		entry=0;
		createTable();
	}
	

	@SuppressWarnings("unchecked")
	protected void createTable() {
		table = (HashEntry<String,SingleLinkedList>[])new HashEntry[table_size];
		for (int i = 0; i < table_size; i++)
			table[i] = null;
	}
	
	protected abstract void put(K key, V value);

	protected abstract void get(K key);

	protected abstract void remove(K key);

	protected int Simple_Summation_Function(K key) {
		int key_value = 0;
		for (int i = 0; i < String.valueOf(key).length(); i++) {
			key_value += (Integer.valueOf(String.valueOf(key).charAt(i)) - 96);
		}
		return key_value;
	}

	protected int Polynomial_Accumulation_Function(K key) {
		int key_value = 0, polynomial = 3;
		for (int i = 0; i < String.valueOf(key).length(); i++) {
			key_value += (Integer.valueOf(String.valueOf(key).charAt(i)) - 96)
					* Math.pow(polynomial, String.valueOf(key).length() - i - 1);
		}
		
		return key_value;
	}
	
	protected abstract int hashFunction(int key);
	protected abstract void resize(int capacity);
	protected abstract void get_with_timing(K key);
	protected abstract void Linear(int hash, K key, V value);
	protected abstract void Double_Hashing(int hash, K key, V value);
	
	

}
