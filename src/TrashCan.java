import java.util.ArrayList;
import java.util.Random;
public class TrashCan {
    private static final int MAX_SIZE = 450;
    public static ArrayList<Garbage> garbageList;

    
    static Random rand = new Random();
    static int[] sizes = {5,10,15};
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
        System.out.println("Trash Can Contents:" + garbageList.size());
        int amount = 1;
		for (int i = 0; i < garbageList.size() - 1; i++) {

			if (garbageList.get(i).toString() == garbageList.get(i+1).toString()) {
				amount++;
			}
			else {
				System.out.printf("%d %s, \n", amount,garbageList.get(i).toString());
				amount = 1;
			}
            
		}
        System.out.printf("%d %s \n", amount, garbageList.get(getItemCount() - 1).toString());
    }

    public void dump() {
        garbageList.clear();
    }

    public boolean transferTo(IBag<Garbage> targetBag, Garbage item) {
        if (!contains(item)) {
            return false;
        }
        remove(item);
        targetBag.add(item);
        return true;
    }

    public static boolean separate(Garbage item) {
        if (item.type.equalsIgnoreCase("plastic")) {
            plasticBin.add(item);
            return true;
        } else if (item.type.equalsIgnoreCase("paper")) {
            paperBin.add(item);
            return true;
        } else if (item.type.equalsIgnoreCase("glass")) {
            glassBin.add(item);
            return true;
        } else if (item.type.equalsIgnoreCase("fabric")) {
            fabricBin.add(item);
            return true;
        } else if (item.type.equalsIgnoreCase("metal")) {
            metalBin.add(item);
            return true;
        } else if (item.type.equalsIgnoreCase("organic")) {
            organicBin.add(item);
            return true;
        } else {
            return false;
        }
    }
}