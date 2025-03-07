package lab2;

import java.time.LocalDate;

public abstract class Person {
    protected String name;
    protected LocalDate birthDate;

    /**
     * Class constructor
     * @param name
     * @param birthDate
     */
    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * This method helps us to use System.out.println on an object
     * @return the string conversion of the object
     */
    @Override
    public String toString() {
        return "name=" + name + ", birthDate=" + birthDate;
    }
}
