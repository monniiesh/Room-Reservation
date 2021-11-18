import java.util.Arrays;
public class OccupancyTester {

    private static boolean trigger = true;
    public static void testPerson(){

        try{
            Person person1 = new Person("John");
            Person person2 = new Person("David");
            Person person3 = new Person("John");

            String expectedOutput1 = "John";
            String expectedOutput2 = "David";
            boolean expectedBool1 = true;
            boolean expectedBool2 = false;
            boolean test = true;

            if (!(person1.getName().equals(expectedOutput1))){
                System.out.println("Failed : Person objected created did not match the expected output");
                test = false;
            }

            if (!(person2.getName().equals(expectedOutput2))){
                System.out.println("Failed : Person objected created did not match the expected output");
                test = false;
            }

            if (person1.equals(person2)){
                System.out.println("Failed : Two Person instances with different name are considered equal");
                test = false;
            }

            if (person1.equals(person3)){
                System.out.println("Failed : Two Person instances with same name are not considered equal");
                test = false;
            }

            if (person1.equals(expectedOutput2)){
                System.out.println("Failed : Person and a string object is considered equal");
                test = false;
            }

            if (person1.isWaiting() == true){
                person2.toggleWaiting();
                if (!(person1.isWaiting() == expectedBool2)){
                    System.out.println("Failed : toggleWaiting method of Person class did not match the expected output");
                    test = false;
                }
            }

            if (person1.isWaiting() == false){
                person2.toggleWaiting();
                if (!(person1.isWaiting() == expectedBool1)){
                    System.out.println("Failed : toggleWaiting method of Person class did not match the expected output");
                    test = false;
                }
            }

            if (test == true){
                System.out.println("Passed : testPerson()");
            }
        }
        catch(Exception e){
            System.out.println("Error : Constructor was not created as expected and hence other methods are not tested.");
        }
    }

    public static void testRoomConstructor(){

        try {
            Room roomTest = new Room("roomTest", 10);
            String[] expectedArray = {"roomTest"};
            if (!(Arrays.equals(Room.getNames(), expectedArray))){
                trigger = false;
            }
            boolean testMain = false;
            {
                boolean test = false;
                try {
                    Room firstRoom = new Room("FirstRoom", 0);
                } catch (IllegalArgumentException e) {
                    test = true;
                    testMain = true;
                }
                if (test == false) {
                    System.out.println("Error : Room was created when capacity was 0");
                }
            }
            {
                boolean test = false;
                try {
                    Room firstRoom = new Room("FirstRoom", 0);
                    Room secondRoom = new Room("FirstRoom", 0);
                } catch (IllegalArgumentException e) {
                    test = true;
                    testMain = true;
                }
                if (test == false) {
                    System.out.println("Error : Room with same name was created");
                }
            }
            if (testMain == true){
                System.out.println("Passed : testRoomConstructor()");
            }
        }
        catch(Exception e){
            trigger = false;
        }
    }

    public static void testRoomAccessors(){

        boolean test = true;
        Room room1 = new Room("Room1", 10);
        Room room2 = new Room("Room2", 7);

        String expectedOutName1 = "Room1";
        String expectedOutName2 = "Room2";

        int expectedOutputCapacity1 = 10;
        int expectedOutputCapacity2 = 7;

        int expectedOutputCOVIDCapacity1 = 5;
        int expectedOutputCOVIDCapacity2 = 4;

        String[] expectedArray = {"Room1", "Room2"};

        if (!(Arrays.equals(Room.getNames(),expectedArray))){
            System.out.println("Error : getNames() gave an unexpected output");
            test  = false;
        }

        if (!(room1.getName().equals(expectedOutName1))){
            System.out.println("Error : getName() gave an unexpected output");
            test = false;
        }

        if (!(room2.getName().equals(expectedOutName2))){
            System.out.println("Error : getName() gave an unexpected output");
            test = false;
        }

        if (!(room1.getCapacity() == expectedOutputCapacity1)){
            System.out.println("Error : getCapacity() gave an unexpected output");
            test = false;
        }

        if (!(room2.getCapacity() == expectedOutputCapacity2)){
            System.out.println("Error : getCapacity() gave an unexpected output");
            test = false;
        }

        if (!(room1.getCOVIDCapacity() == expectedOutputCOVIDCapacity1)){
            System.out.println("Error : getCOVIDCapacity() gave an unexpected output");
            test = false;
        }

        if (!(room2.getCOVIDCapacity() == expectedOutputCOVIDCapacity2)){
            System.out.println("Error : getCOVIDCapacity() gave an unexpected output");
            test = false;
        }

        if (!(room2.getOccupancy() == 0)){
            System.out.println("Error : getOccupancy() gave an unexpected output");
            test = false;
        }

        if (test == true){
            System.out.println("Passed : testRoomAccessors()");
        }

    }

    public static void testRoomCheckIn(){
        boolean mainTest = true;
        {
            boolean test = false;
            try{
                Person personA = null;
                Room roomA = new Room("RoomA", 3);
                roomA.checkIn(personA);
            }
            catch (IllegalArgumentException e){
                test = true;
                mainTest = true;
            }

            if (test == false){
                System.out.println("Error : Null input is not managed.");
            }
        }
        {
            boolean test = false;
            try{
                Person personA = new Person("PersonA");
                Person personB = new Person("PersonA");
                Room roomB = new Room("RoomB", 5);
                roomB.checkIn(personA);
                roomB.checkIn(personB);
            }
            catch (IllegalArgumentException e) {
                test = true;
                mainTest = true;
            }
            if (test == false){
                System.out.println("Error : Two different person object of the same name was added");
            }

        }
        {
            Person dude = new Person("Dude");
            Person man = new Person("Man");
            Person boy = new Person("Boy");
            Person girl = new Person("Girl");
            Person woman = new Person("Woman");
            Room roomTesting = new Room("TestingRoom", 8);
            Person[] occupants = new Person[8];
            int currentOccupancy = 4;
            int COVIDOccupancy = 4;
            roomTesting.checkIn(girl);
            roomTesting.checkIn(man);
            roomTesting.checkIn(dude);
            roomTesting.checkIn(boy);
            Person[] expectedOutputArray = {girl, null, man, null, dude, null, boy, null};
            int expectedOccupancy = 4;
            if(!(roomTesting.getOccupancy() == expectedOccupancy)){
                System.out.println("Error : the current occupancy of the room is different than expected");
                mainTest = false;
            }
            if (!(Arrays.equals(occupants, expectedOutputArray))){
                System.out.println("Error : room occupants do not match the expected order or they are missing");
                mainTest = false;
            }
            if (dude.isWaiting() == true){
                System.out.println("Error : the waiting status of the person is not updated to false");
                mainTest = false;
            }
            if (man.isWaiting() == true){
                System.out.println("Error : the waiting status of the person is not updated to false");
                mainTest = false;
            }
            if (boy.isWaiting() == true){
                System.out.println("Error : the waiting status of the person is not updated to false");
                mainTest = false;
            }
            if (girl.isWaiting() == true){
                System.out.println("Error : the waiting status of the person is not updated to false");
                mainTest = false;
            }

            if (roomTesting.checkIn(woman) == true){
                System.out.println("Error : An additional peron was added beyond COVID Capacity");
                mainTest = false;
            }

        }
        if (mainTest == true){
            System.out.println("Passed : testRoomCheckIn()");
        }


    }

    public static void testRoomCheckOut(){
        boolean mainTest = true;
        {
            boolean test = false;
            try{
                Person personA = null;
                Room roomA = new Room("RoomA", 3);
                roomA.checkOut(personA);
            }
            catch (IllegalArgumentException e){
                test = true;
                mainTest = true;
            }

            if (test == false){
                System.out.println("Error : Null input is not managed.");
            }
        }
        {
            Person dude = new Person("Dude");
            Person man = new Person("Man");
            Person boy = new Person("Boy");
            Person girl = new Person("Girl");
            Person woman = new Person("Woman");
            Room roomTesting = new Room("TestingRoom", 8);
            Person[] occupants = {dude, null, man, null, boy, null, girl, null};
            Person[] expectedOccupants = {dude, null, null, null, boy, null, girl, null};
            int currentOccupancy = 4;
            roomTesting.checkOut(man);

            if (!(Arrays.equals(expectedOccupants, occupants))) {
                System.out.println("Error : the person was not checked out properly");
                mainTest = false;
            }
            if (!(currentOccupancy == 3)) {
                System.out.println("Error : the occupancy list was not decremented");
                mainTest = false;
            }
            if (roomTesting.checkOut(woman) == true) {
                System.out.println("Error : the method checked Out a person who is not the occupant of the room");
            }
        }
        if (mainTest == true){
            System.out.println("Passed : testRoomCheckIn()");
        }

    }




    public static void main(String[] args) {
        testPerson();
        testRoomConstructor();
        if (trigger == true){
            testRoomAccessors();
            testRoomCheckIn();
            testRoomCheckOut();
        }
        else{
            System.out.println("ERROR : As the constructor creation failed, Room objet did not perform as expected.");
        }
    }
}
