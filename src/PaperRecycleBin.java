import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaperRecycleBin implements IBag<Garbage> {

    private final int maxSize;
    private final Garbage[] items;
    private int itemCount;

    public PaperRecycleBin(int size) {
        maxSize = size;
        items = new Garbage[maxSize];
        itemCount = 0;
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
            System.out.println("The paper recycle bin is empty.");
        } else {
            System.out.println("Paper Recycle Bin:" + maxSize);
            // Create a new list to hold unique items and their counts
            List<String> uniqueItems = new ArrayList<>();
            List<Integer> itemCounts = new ArrayList<>();
            
            // Iterate over the garbage list and count the number of occurrences of each item
            for (Garbage item : items) {
                String itemName = item.toString();
                if (uniqueItems.contains(itemName)) {
                    int index = uniqueItems.indexOf(itemName);
                    int count = itemCounts.get(index) + 1;
                    itemCounts.set(index, count);
                } else {
                    uniqueItems.add(itemName);
                    itemCounts.add(1);
                }
            }
            
            // Print the unique items and their counts
            for (int i = 0; i < uniqueItems.size(); i++) {
                System.out.printf("%d %s, \n", itemCounts.get(i), uniqueItems.get(i));
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

    @Override
    public boolean transferTo(IBag<Garbage> targetBag, Garbage item) {
        if (targetBag == null || item == null || !contains(item)) {
            return false;
        }
        PaperRecycleBin targetBin = (PaperRecycleBin) targetBag;
        if (targetBin.isFull()) {
            return false;
        }
        remove(item);
        return true;
    }
}