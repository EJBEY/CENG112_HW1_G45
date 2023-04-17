
public class Garbage {

    private String name;
    private String type;
    private int amount;

    public Garbage(String name, String type, int amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name + ", " + type + ", " + amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Garbage) {
            Garbage otherGarbage = (Garbage) obj;
            return name.equals(otherGarbage.name) && type.equals(otherGarbage.type) && amount == otherGarbage.amount;
        }
        return false;
    }
}
