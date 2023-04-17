
import java.util.ArrayList;
public class TrashCan {




    private static final int MAX_SIZE = 450;
    private static ArrayList<Garbage> garbageList;

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
        System.out.println("Trash Can Contents:");
        for (Garbage garbage : garbageList) {
            System.out.println(garbage);
        }
        System.out.println();
    }

    public void dump() {
        garbageList.clear();
    }

    public boolean transferTo(IBag<Garbage> targetBag, Garbage item) {
        if (!contains(item)) {
            return false;
        }
        boolean removed = remove(item) != null;
        boolean added = targetBag.add(item);
        return removed && added;
    }

    public static boolean separate(GarbageRecyclingApp app) {
        for (Garbage garbage : garbageList) {
            if (garbage.getType().equalsIgnoreCase("plastic")) {
                app.plasticBin.add(garbage);
            } else if (garbage.getType().equalsIgnoreCase("paper")) {
                app.paperBin.add(garbage);
            } else if (garbage.getType().equalsIgnoreCase("glass")) {
                app.glassBin.add(garbage);
            } else if (garbage.getType().equalsIgnoreCase("fabric")) {
                app.fabricBin.add(garbage);
            } else if (garbage.getType().equalsIgnoreCase("metal")) {
                app.metalBin.add(garbage);
            } else if (garbage.getType().equalsIgnoreCase("organic")) {
                app.organicBin.add(garbage);
            } else {
                // unrecognized garbage type, do nothing
            }
        }
        return true;
    }
}