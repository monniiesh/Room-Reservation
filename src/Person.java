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

/**
 * This clk=ass contains methods used by Person Object.
 */
public class Person {
  private String name;
  private boolean isWaiting = true;

  /**
   * Constructor of the Person Class.
   * 
   * @param name - Name of the Person
   */
  public Person(String name) {
    this.name = name;
  }

  /**
   * Accessor Method to get the name of th eperson from Person object
   * 
   * @return name - name of the person
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor method to get the waiting status of the person
   *
   * @return true if the person is waiting to get into room or checked out, false if the person is
   *         in the room or checked in
   */
  public boolean isWaiting() {
    return isWaiting;
  }

  /**
   * Method which compared person object with other objects.
   *
   * @param o the other object with which person object si compared.
   * @return true if if both the objects are same, false if both person adn other object are
   *         different.
   */
  public boolean equals(Object o) {
    if (o instanceof Person) {
      return this.name.equals(((Person) o).name);
    } else {
      return false;
    }
  }

  /**
   * This method toggles isWaiting field to true if false adn vice-versa
   */
  public void toggleWaiting() {
    if (isWaiting == false) {
      isWaiting = true;
    } else {
      isWaiting = false;
    }
  }
}
