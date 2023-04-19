public class GarbageRecyclingApp {
    public static void main(String[] args) {
        // Read the trash can from file
        FileIO fileIO = new FileIO();
        TrashCan trashCan = fileIO.readTrashCan();
        
        
        //Display the trash can
        System.out.println("Trash Can: " + trashCan.getItemCount());
        trashCan.displayItems();

        System.out.println("----------------------------------");
        // Separate the garbage into recycling bins
        int i = 0;
        while (i < trashCan.getItemCount()) {
        	if (trashCan.separate(trashCan.garbageList.get(i)) ) {
				i = trashCan.getIndexOf(trashCan.garbageList.get(i));
				i++;
			}
        	else {
				i++;
			}
        }

        // Update the trash can file
        fileIO.updateTrashCan(trashCan);
        
        // Display the recycling bins and the updated trash can

        trashCan.plasticBin.displayItems();
        trashCan.paperBin.displayItems();
        trashCan.glassBin.displayItems();
        trashCan.fabricBin.displayItems();
        trashCan.metalBin.displayItems();
        trashCan.organicBin.displayItems();

        System.out.println("----------------------------------");
        
        System.out.println("Trash Can (updated): " + trashCan.getItemCount());
        trashCan.displayItems();


    }
}