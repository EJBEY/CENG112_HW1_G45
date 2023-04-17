import java.util.Random;

public class PlasticRecycleBin implements IBag<Garbage> {
	public static PlasticRecycleBin instance;
    private final int maxSize;
    private final Garbage[] items;
    private int itemCount;

    public PlasticRecycleBin(int size) {
        maxSize = size;
        items = new Garbage[maxSize];
        itemCount = 0;
    }
    public static PlasticRecycleBin getInstance() {
        if (instance == null) {
            instance = new PlasticRecycleBin(450);
        }
        return instance;
    }
    @Override
    public boolean add(Garbage newItem) {
        if (isFull()) {
            return false;
        }
        items[itemCount] = newItem;
        itemCount++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return itemCount == 0;
    }

    @Override
    public boolean isFull() {
        return itemCount == maxSize;
    }

    @Override
    public Garbage removeByIndex(int index) {
        if (index < 0 || index >= itemCount) {
            return null;
        }
        Garbage removedItem = items[index];
        for (int i = index; i < itemCount - 1; i++) {
            items[i] = items[i + 1];
        }
        items[itemCount - 1] = null;
        itemCount--;
        return removedItem;
    }

    @Override
    public Garbage remove() {
        if (isEmpty()) {
            return null;
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(itemCount);
        return removeByIndex(randomIndex);
    }

    @Override
    public Garbage remove(Garbage item) {
        int index = getIndexOf(item);
        return removeByIndex(index);
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getIndexOf(Garbage item) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Garbage item) {
        return getIndexOf(item) != -1;
    }

    @Override
    public void displayItems() {
        if (isEmpty()) {
            System.out.println("The plastic recycle bin is empty.");
        } else {
            System.out.println("Plastic Recycle Bin:");
            for (int i = 0; i < itemCount; i++) {
                System.out.println("- " + items[i].getName());
            }
        }
    }

    @Override
    public void dump() {
        for (int i = 0; i < itemCount; i++) {
            items[i] = null;
        }
        itemCount = 0;
    }

    public boolean transferTo(IBag<Garbage> targetBag, Garbage item) {
        if (targetBag == null || item == null || !contains(item)) {
            return false;
        }
        PlasticRecycleBin targetBin = (PlasticRecycleBin) targetBag;
        if (targetBin.isFull()) {
            return false;
        }
        targetBin.add(item);
        remove(item);
        return true;
    }
}