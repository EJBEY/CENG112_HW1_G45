import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
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
            for (int i = 0; i < trashCan.getItemCount(); i++) {
                Garbage garbage = trashCan.garbageList.get(i);
                writer.write(garbage.name + ", " + garbage.type + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + TRASH_CAN_FILE);
            return false;
        }
    }
}

