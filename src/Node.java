

public class Node {
	   Object data;
	   Node link; 
	   String name;
	   int value;

	   public Node(String name,int value) {
		     link = null;
		     this.name=name;
		     this.value=value;
	   }
	   
	   public Object getData() { return data; }
	   public void setData(Object data) { this.data = data;  }

	   public Node getLink() { return link;  }
	   public void setLink(Node link) { this.link = link;   }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
