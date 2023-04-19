
public class Garbage {

    public String name;
    public String type;

    public Garbage(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ", " + type;
    }

    @Override
    public boolean equals(Object obj) {
    	return this == obj;
    }
}
