//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03-Room-Reservation
// Course: CS 300 Spring 2021
//
// Author: Monniiesh Velmurugan
// Email: mvelmurugan@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * Contains all methods used by Room Object.
 */
public class Room {
  private static ArrayList<String> names = new ArrayList<>(10);
  private String name;
  private int totalCapacity;
  private Person[] occupants;
  private int currentOccupancy = 0;
  private int COVIDCapacity;

  /**
   * Accessor method used to get the names of rooms.
   *
   * @return An array of string which has the names of the rooms
   */
  public static String[] getNames() {
    String[] temp = new String[Room.names.size()];
    for (int i = 0; i < Room.names.size(); i++) {
      temp[i] = Room.names.get(i);
    }
    return temp;
  }

  /**
   * Constructor of Room class. Room object is created if room capacity is more than 0 or if the
   * name of the room is not taken. Otherwise, the constructor throws IllegalArgumentError.
   *
   * @param name     Name of the room
   * @param capacity Capacity of the room
   */
  public Room(String name, int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("Error : Room capacity cannot be less than 1");
    } else if (Room.names.contains(name)) {
      throw new IllegalArgumentException("Error : Room with the same name already exist");
    } else {
      this.name = name;
      totalCapacity = capacity;
      occupants = new Person[totalCapacity];
      if (totalCapacity % 2 == 0) {
        COVIDCapacity = totalCapacity / 2;
      } else {
        COVIDCapacity = (totalCapacity / 2) + 1;
      }
      Room.names.add(name);
    }
  }

  /**
   * Accessor method to get the name of room.
   *
   * @return name of the room
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor method to get the current occupancy of the room.
   *
   * @return Current occupancy of the room
   */
  public int getOccupancy() {
    return currentOccupancy;
  }

  /**
   * Accessor method to get the room capacity during COVID situation.
   *
   * @return capacity during COVID situation
   */
  public int getCOVIDCapacity() {
    return COVIDCapacity;
  }

  /**
   * Accessor method to get the room capacity during normal situation.
   *
   * @return room capacity during normal situation
   */
  public int getCapacity() {
    return totalCapacity;
  }

  /**
   * This method check if a Person already exist in the occupants array(room).
   *
   * @param p the person object whcih is being checked in the array(room)
   * @return true if the person is in the room, false if the person is not in the room.
   */
  public boolean contains(Person p) {
    for (int i = 0; i < occupants.length; i++) {
      if (i % 2 == 0) {
        if (p.equals(occupants[i])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks in a person into the room. It throws an illegal argument error if the 
   * person is already in the room or person is null. It also manages the room capacity.
   *
   * @param in the person who needs to be checked in
   * @return returns true if the person is checked in, false if the room is full or the person did
   *         not check in succesfully
   */
  public boolean checkIn(Person in) {
    if (in == null) {
      throw new IllegalArgumentException("Error : Inputted person is Null object");
    } else if (contains(in)) {
      throw new IllegalArgumentException("Error : Person Already Exists");
    } else if (currentOccupancy == COVIDCapacity) {
      return false;
    } else {
      for (int i = 0; i < occupants.length; i++) {
        if (i % 2 == 0) {
          if (occupants[i] == null) {
            occupants[i] = in;
            currentOccupancy++;
            in.toggleWaiting();
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * This method cheks out a person from the room. It also maintains the current occupancy of the
   * room.
   *
   * @param out The person to be checked out
   * @return true if th eperson is checked out, false if the person was not checked out 
   *         successfully
   */
  public boolean checkOut(Person out) {
    if (out == null) {
      throw new IllegalArgumentException("Error : Inputted person is Null object");
    }
    if (contains(out)) {
      out.toggleWaiting();
      currentOccupancy--;
      for (int i = 0; i < occupants.length; i++) {
        if ((i % 2) == 0) {
          if (out.equals(occupants[i])) {
            occupants[i] = null;
          }
        }
      }
      return true;
    }
    return false;
  }

  /**
   * This method prints all the names of the occupants of the room in the exact order of their
   * seating arrangement.
   *
   * @return a string of names of ht occupants which needs to be printed
   */
  public String toString() {
    String output = name + "\n===\n";
    for (int i = 0; i < occupants.length; i++) {
      if (occupants[i] == null) {
        output += "-\n";
      } else {
        output += (occupants[i].getName() + "\n");
      }
    }
    return output;
  }
}


