public class SingleLinkedList {
	public Node head;

	public SingleLinkedList() {
		head = null;
	}

	public void addToEnd(String x, int y) {
		Node newNode = new Node(x, y);

		if (head == null) {
			head = newNode;
		} else {
			Node temp = head;

			while (temp.getLink() != null) {
				temp = temp.getLink();
			}

			temp.setLink(newNode);
		}
	}

	public void addTohead(String x, int y) {
		Node newNode = new Node(x, y);

		if (head == null) {
			head = newNode;
		} else {
			if (true) {
				Node newnode = new Node(x, y);
				newnode.setLink(head);
				head = newnode;
			}
		}
	}

	public void value_plus_plus(String path) {
		Node temp = head;
		while (temp != null) {
			if(temp.getName().equals(path)) 
				temp.setValue(temp.getValue()+1);
			
			temp = temp.getLink();
		}
	}
	
	public String display(String key) {
		String output = "";
		System.out.println();
		Node temp = head;
		int amount=0;;
		while (temp != null) {
			output = "name="+key+" ,path="+temp.getName()+" ,value="+temp.getValue();
			amount+=temp.getValue();
			System.out.println(output);
			temp = temp.getLink();
			
		}
		System.out.println("\namount="+amount);
		return output;
	}

	public void removeAll(Object dataToRemove) {
		if (head == null) {
			System.err.println("The Linked List is empty");
		} else {
			while ((int) head.getData() == (int) dataToRemove && head.getData() != null) {
				head = head.getLink();
				if (head == null) {
					break;
				}
			}

			Node temp = head;
			Node prev = null;
			while (temp != null) {

				if ((int) temp.getData() == (int) dataToRemove) {
					prev.setLink(temp.getLink());
					temp = temp.getLink();
				} else {
					prev = temp;
					temp = temp.getLink();
				}
			}
		}
	}

	public boolean removeOne(Object dataToRemove) {
		if (head == null) {
			System.err.println("The Linked List is empty");
			return false;
		}

		else if ((int) head.getData() == (int) dataToRemove) {
			head = head.getLink();
			return true;
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((int) temp.getData() == (int) dataToRemove) {
					prev.setLink(temp.getLink());
					return true;
				}
				prev = temp;
				temp = temp.getLink();
			}
			System.out.println("Element" + dataToRemove + "could not be found!");
			return false;
		}
	}

	public int removeFirst() {
		int temp2 = (int) head.getData();
		if (head == null) {
			System.err.println("The Linked List is empty");

		}

		else if ((int) head.getData() == (int) temp2) {
			head = head.getLink();
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((int) temp.getData() == (int) temp2) {
					prev.setLink(temp.getLink());
					break;
				}
				prev = temp;
				temp = temp.getLink();
			}
			System.out.println("Element" + temp2 + "could not be found!");
		}
		return temp2;
	}

	public char removeFirst_char() {
		char temp2 = (char) head.getData();
		if (head == null) {
			System.err.println("The Linked List is empty");

		}

		else if ((char) head.getData() == (char) temp2) {
			head = head.getLink();
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((char) temp.getData() == (char) temp2) {
					prev.setLink(temp.getLink());
					break;
				}
				prev = temp;
				temp = temp.getLink();
			}
			System.out.println("Element" + temp2 + "could not be found!");
		}
		return temp2;
	}

	public int size() {
		int count = 0;
		if (head == null) {
			// System.err.println("The Linked List is empty");
		} else {
			Node temp = head;
			while (temp != null) {
				count++;
				temp = temp.getLink();
			}

		}
		return count;
	}

	public boolean search(Object dataToSearch) {
		if (head == null) {
			return false;
		} else {
			Node temp = head;

			boolean retVal = false;
			while (temp != null) {
				if ((int) temp.getData() == (int) dataToSearch) {
					retVal = true;
					break;
				}

				temp = temp.getLink();
			}

			return retVal;
		}
	}

	public boolean search_char(Object dataToSearch) {
		if (head == null) {
			return false;
		} else {
			Node temp = head;

			boolean retVal = false;
			while (temp != null) {
				if ((char) temp.getData() == (char) dataToSearch) {
					retVal = true;
					break;
				}

				temp = temp.getLink();
			}

			return retVal;
		}
	}

	public int findMax() {
		if (head == null) {
			System.err.println("The Linked List is empty");
			return Integer.MIN_VALUE;
		} else {

			int maxVal = Integer.MIN_VALUE;

			Node temp = head;

			while (temp != null) {
				if ((int) temp.getData() > maxVal) {
					maxVal = (int) temp.getData();
				}

				temp = temp.getLink();
			}

			return maxVal;
		}
	}

}
