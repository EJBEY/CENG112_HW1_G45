import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrashCan {
    private static final int MAX_SIZE = 450;
    public static ArrayList<Garbage> garbageList;
    
    //Random element for Recycle Bin sizes
    static Random rand = new Random();
    static int[] sizes = {5,10,15};
    //Recycle Bins
    public static PlasticRecycleBin plasticBin  = new PlasticRecycleBin(sizes[rand.nextInt(3)]);
    public static PaperRecycleBin paperBin = new PaperRecycleBin(sizes[rand.nextInt(3)]);
    public static GlassRecycleBin glassBin = new GlassRecycleBin(sizes[rand.nextInt(3)]);
    public static FabricRecycleBin fabricBin = new FabricRecycleBin(sizes[rand.nextInt(3)]);
    public static OrganicRecycleBin organicBin = new OrganicRecycleBin(sizes[rand.nextInt(3)]);
    public static MetalRecycleBin metalBin = new MetalRecycleBin(sizes[rand.nextInt(3)]);

    public TrashCan() {
        garbageList = new ArrayList<Garbage>();
    }

    public boolean add(Garbage newItem) {
        if (isFull()) {
            return false;
        }
        garbageList.add(newItem);
        return true;
    }

    public boolean isEmpty() {
        return garbageList.isEmpty();
    }

    public boolean isFull() {
        return getItemCount() == MAX_SIZE;
    }

    public Garbage removeByIndex(int index) {
        if (index < 0 || index >= getItemCount()) {
            return null;
        }
        return garbageList.remove(index);
    }

    public Garbage remove() {
        if (isEmpty()) {
            return null;
        }
        return garbageList.remove(getItemCount() - 1);
    }

    public Garbage remove(Garbage item) {
        int index = getIndexOf(item);
        if (index == -1) {
            return null;
        }
        return garbageList.remove(index);
    }

    public int getItemCount() {
        return garbageList.size();
    }

    public int getIndexOf(Garbage item) {
        return garbageList.indexOf(item);
    }

    public boolean contains(Garbage item) {
        return garbageList.contains(item);
    }

    public void displayItems() {
        
        // Create a new list to hold items and their counts
        List<String> uniqueItems = new ArrayList<>();
        List<Integer> itemCounts = new ArrayList<>();


        for (Garbage item : garbageList) {
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
            System.out.printf("%s,%d \n", uniqueItems.get(i),itemCounts.get(i));
        }
    }


    public void dump() {
        garbageList.clear();
    }

    public boolean transferTo(IBag<Garbage> targetBag, Garbage item) {
        if (!contains(item)) {
        	System.out.println("a");
            return false;
        }
        remove(item);
        targetBag.add(item);
        return true;
    }

    public boolean separate(Garbage item) {

        if (item.type.equalsIgnoreCase("plastic")) {
            return transferTo(plasticBin, item);
        } else if (item.type.equalsIgnoreCase("paper")) {
            return transferTo(paperBin, item);
        } else if (item.type.equalsIgnoreCase("glass")) {
            return transferTo(glassBin, item);
        } else if (item.type.equalsIgnoreCase("fabric")) {
            return transferTo(fabricBin, item);
        } else if (item.type.equalsIgnoreCase("metal")) {
            return transferTo(metalBin, item);
        } else if (item.type.equalsIgnoreCase("organic")) {
            return transferTo(organicBin, item);
        } else {
            return false;
        }
    }
}