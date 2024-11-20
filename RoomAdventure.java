// Name: Mark
// Date: 11/11/2024
// Description: Room Adventure using Java


import java.util.Scanner;

public class RoomAdventure {
    // game class

    // class variables
    private static Room currentRoom;
    private static String[] inventory = {null, null, null, null, null};
    private static String status;


    // consants - final makes it a constant
    final private static String DEFAULT_STATUS = "Sorry, I don't understand. Try [verb] [noun]";

    public static void main(String[] args) {
        setupGame();

        // while loop (our main loop for this)
        while (true) {
            System.out.println(currentRoom.toString());
            System.out.println("Inventory: ");

            // print out everything in the inventory
            for (int i = 0; i < inventory.length; i++) {
                System.out.print(inventory[i] + " ");  // getting a value from an array using its index
            }

            System.out.println("\nWhat would you like to do? ");

            // taking input
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            // processing our input
            String[] words = input.split(" ");

            if (words.length != 2) {
                status = DEFAULT_STATUS;
                System.out.println(status);
                continue;
            }

            String verb = words[0];
            String noun = words[1];

            switch (verb) {
                case "go":
                    handleGo(noun);
                    break;
                case "look":
                    handleLook(noun);
                    break;
                case "take":
                    handleTake(noun);
                    break;
                default:
                    status = DEFAULT_STATUS;
            }

            System.out.println(status);
        }

    }

    private static void handleGo(String noun) {
        String[] exitDirections = currentRoom.getExitDirections();
        Room[] exitDestinations = currentRoom.getExitDestinations();

        status = "I don't see that room";
        for (int i = 0; i < exitDirections.length; i ++) {
            if (noun.equals(exitDirections[i])) {
                currentRoom = exitDestinations[i];
                status = "Changed Room";
                break;
            }
            // how to do else if and else
            // else if ( condition ) {
            //
            // }
            // else {
            //
            // }
        }
    }

    private static void handleLook(String noun) {
        String[] items = currentRoom.getItems();
        String[] itemDescriptions = currentRoom.getItemDescriptions();
        
        status = "I don't see that item.";
        for (int i=0; i < items.length; i++) {
            if (noun.equals(items[i])) {
                status = itemDescriptions[i];
                break;
            }
        }
    }

    private static void handleTake(String noun) {
        status = "I can't grab that";
        for (int i = 0; i < currentRoom.grabbables.length; i ++) {
            if (noun.equals(currentRoom.grabbables[i])) {
                for (int j = 0; j < inventory.length; j++) {
                    if (inventory[j] == null) {
                        inventory[j] = noun;
                        status = String.format("Added %s to the inventory.", noun);
                        break;
                    }
                }
            }
        }
    }

    private static void setupGame() { // void means this function doesn't return anything
        // instantiate rooms
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");

        String[] room1ExitDirections = {"east", "south"}; // initializing an array
        Room[] room1ExitDestinations = {room2, room3};

        String[] room1Items = {"chair", "desk"};
        String[] room1ItemDescriptions = {
            "It is a chair.",
            "It is a desk. There is a key on it."
        };

        String[] room1Grabbables = {"key"};

        room1.setExitDirections(room1ExitDirections);
        room1.setExitDestinations(room1ExitDestinations);
        room1.setItems(room1Items);
        room1.setItemDescriptions(room1ItemDescriptions);
        room1.grabbables = room1Grabbables;

        String[] room2ExitDirections = {"east", "south"}; // initializing an array
        Room[] room2ExitDestinations = {room2, room4};
        String[] room2Items = {"rug", "fireplace"};
        String[] room2ItemDescriptions = {
            "It is red and blue",
            "It's on fire. There's a piece of coal in front of it."
        };

        String[] room2Grabbables = {"coal"};

        room2.setExitDirections(room2ExitDirections);
        room2.setExitDestinations(room2ExitDestinations);
        room2.setItems(room2Items);
        room2.setItemDescriptions(room2ItemDescriptions);
        room2.grabbables = room2Grabbables;

        String[] room3ExitDirections = {"north", "east"}; // initializing an array
        Room[] room3ExitDestinations = {room1, room4};
        String[] room3Items = {"bookcase", "book"};
        String[] room3ItemDescriptions = {
            "It is empty besides a book",
            "It is an 8th? Harry Potter book? They wrote more?"
        };

        String[] room3Grabbables = {"wand"};

        room3.setExitDirections(room3ExitDirections);
        room3.setExitDestinations(room3ExitDestinations);
        room3.setItems(room3Items);
        room3.setItemDescriptions(room3ItemDescriptions);
        room3.grabbables = room3Grabbables;

        String[] room4ExitDirections = {"north", "west"}; // initializing an array
        Room[] room4ExitDestinations = {room2, room3};
        String[] room4Items = {"couch", "microwave", "computer"};
        String[] room4ItemDescriptions = {
            "It is very comfortable, you start to drift away as you sink into the couch",
            "It is white and old. You want to heat up your burrito, but it is broken",
            "You sit in front of the screen, it is pulled up to the CSC 132 canvas page. You try to use the keyboard and mouse, but they are broken too.",
        };

        String[] room4Grabbables = {"keyboard", "mouse", "burrito"};

        room4.setExitDirections(room4ExitDirections);
        room4.setExitDestinations(room4ExitDestinations);
        room4.setItems(room4Items);
        room4.setItemDescriptions(room4ItemDescriptions);
        room4.grabbables = room4Grabbables;

        currentRoom = room1;
    }
}

class Room {

    // instance variables
    private String roomName;

    // a private instance variable that is an array of Strings
    private String[] exitDirections; // west, east, north, south, up, down, etc..
    private Room[] exitDestinations; // actual rooms tied to the directions west, east, etc..

    private String[] items; // item names like "key"
    private String[] itemDescriptions; // ex: "a golden key. maybe we can take it"

    public String[] grabbables;

    // public int[] numbers = new int[65];

    // constructor
    public Room(String name) {
        roomName = name;
    }

    // can have more than one constructor as long as the signature of the function
    // is different that all of the other constructors
    // public Room(int roomNumber) {
    
    //}

    // public Room(int otherValue, int moreNumbers) {
    
    //}

    // methods

    // setter ex:
    public void setExitDirections(String[] dirs) {
        exitDirections = dirs;
    }

    // getter ex:
    public String[] getExitDirections() {
        return exitDirections;
    }

    public void setExitDestinations(Room[] exitDestinations) {
        this.exitDestinations = exitDestinations;
    }

    public Room[] getExitDestinations() {
        return exitDestinations;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getItems() {
        return this.items;
    }

    public void setItemDescriptions(String[] itemDescriptions) {
        this.itemDescriptions = itemDescriptions;
    }

    public String[] getItemDescriptions() {
        return this.itemDescriptions;
    }

    public void setRoomName(String name) {
        this.roomName = name;
    }

    public String getRoomName() {
        return roomName;
    }

    // not doing getter/setter for grabbles since it is public already


    // __str__
    public String toString() {
        String result = "\n";
        result += "Location:" + roomName; // concatenation

        result += "\n You see: ";
        // for loop
        for (int i = 0; i < items.length; i++) {
            result += items[i] + " "; // accessing items in an array
        }

        result += "\nExits: ";

        // for each loops
        // for direction in exitDirections:
        for (String direction : exitDirections) {
            result += String.format("%s ", direction);
        }

        return result;
    }
}

