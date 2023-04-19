public class GarbageRecyclingApp {
    public static void main(String[] args) {
        // read the trash can from file
        FileIO fileIO = new FileIO();
        TrashCan trashCan = fileIO.readTrashCan();
        
        

        System.out.println("Trash Can: " + trashCan.getItemCount());
        trashCan.displayItems();
        
        // separate the garbage into recycling bins
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

        
        // display the recycling bins and the updated trash can

        trashCan.plasticBin.displayItems();
        trashCan.paperBin.displayItems();
        trashCan.glassBin.displayItems();
        trashCan.fabricBin.displayItems();
        trashCan.metalBin.displayItems();
        trashCan.organicBin.displayItems();
        
        
        System.out.println("Trash Can (updated): " + trashCan.getItemCount());
        trashCan.displayItems();

        // update the trash can file
        fileIO.updateTrashCan(trashCan);

    }
}