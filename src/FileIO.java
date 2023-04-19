import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    private final String TRASH_CAN_FILE = "C:\\Users\\voyi0\\Downloads\\garbage.txt";
    public TrashCan readTrashCan() {
        TrashCan trashCan = new TrashCan();

        try {
            File file = new File(TRASH_CAN_FILE);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String type = parts[1].trim();
                int amount = Integer.parseInt(parts[2].trim());

                // Create Garbage objects and add them to the TrashCan
                for (int i = 0; i < amount; i++) {
                    Garbage garbage = new Garbage(name, type);
                    if (!trashCan.isFull()) {
                        trashCan.add(garbage);
					}
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return trashCan;
    }

    public boolean updateTrashCan(TrashCan trashCan) {
        try {
            FileWriter writer = new FileWriter(TRASH_CAN_FILE);

            // Create a new list to hold unique items and their counts
            List<String> uniqueItems = new ArrayList<>();
            List<Integer> itemCounts = new ArrayList<>();
            
            // Iterate over the garbage list and count the number of occurrences of each item
            for (Garbage item : trashCan.garbageList) {
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
            	writer.write(itemCounts.get(i) + uniqueItems.get(i) + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + TRASH_CAN_FILE);
            return false;
        }
    }
}

