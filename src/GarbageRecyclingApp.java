import java.util.Random;

public class GarbageRecyclingApp {
	public static PlasticRecycleBin plasticBin;
	public static PlasticRecycleBin paperBin;
	public static PlasticRecycleBin glassBin;
	public static PlasticRecycleBin fabricBin;
	public static PlasticRecycleBin organicBin;
	public static PlasticRecycleBin metalBin;
	
    public static void main(String[] args) {
        // read the trash can from file
        FileIO fileIO = new FileIO();
        TrashCan trashCan = fileIO.readTrashCan();
        
        // create the recycling bins
        Random rand = new Random();
        plasticBin.getInstance();
        paperBin.getInstance();
        glassBin.getInstance();
        fabricBin.getInstance();
        organicBin.getInstance();
        metalBin.getInstance();
        // separate the garbage into recycling bins
        while (!trashCan.isEmpty()) {
            Garbage item = trashCan.remove();
            if (!item.getName().isEmpty()) {
                if (!organicBin.contains(item)) {
                    if (plasticBin.contains(item)) {
                        plasticBin.transferTo(plasticBin, item);
                    } else if (paperBin.contains(item)) {
                        paperBin.transferTo(paperBin, item);
                    } else if (glassBin.contains(item)) {
                        glassBin.transferTo(glassBin, item);
                    } else if (fabricBin.contains(item)) {
                        fabricBin.transferTo(fabricBin, item);
                    } else if (metalBin.contains(item)) {
                        metalBin.transferTo(metalBin, item);
                    } else {
                        organicBin.transferTo(organicBin, item);
                    }
                }
            }
        }
        
        // update the trash can file
        fileIO.updateTrashCan(trashCan);
        
        // display the recycling bins and the updated trash can
        System.out.println("Trash Can: " + trashCan);
        System.out.println("Plastic Recycling Bin: " + plasticBin);
        System.out.println("Paper Recycling Bin: " + paperBin);
        System.out.println("Glass Recycling Bin: " + glassBin);
        System.out.println("Fabric Recycling Bin: " + fabricBin);
        System.out.println("Metal Recycling Bin: " + metalBin);
        System.out.println("Organic Recycling Bin: " + organicBin);
        System.out.println("Trash Can (updated): " + fileIO.readTrashCan());
    }
}

