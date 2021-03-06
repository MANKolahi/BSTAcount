package Data;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Account implements ComparableData , Comparable<Account>{
    private final String firstName;
    private final String lastName;
    private ID id;
    private final int age;
    private final String socialSecurityNumber;
    private final  String gender;
    private Path picture ;

    public Account(String firstName, String lastName, String gender, int age, String socialSecurityNumber){
        try {
            if (Stream.of("male", "Male", "female", "Female").noneMatch(gender::equals))
                throw new IllegalArgumentException("gender must be male or female");
            if (age <= 0)
                throw new IllegalArgumentException("age must be > 0");
            if (socialSecurityNumber.isEmpty())
                throw new IllegalArgumentException("socialSecurityNumber must be > 0");
            if (firstName == null)
                throw new IllegalArgumentException("firstName can't be a null value");
            if (lastName == null)
                throw new IllegalArgumentException("lastName can't be a null value");
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.id = new ID(firstName, lastName);
            this.socialSecurityNumber = socialSecurityNumber;
            setDefaultPic();
        }catch (Exception ex){
            throw new IllegalArgumentException("Account create failed");
        }
    }

    public String getID() {
        return id.getID();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getGender() {
        return gender;
    }

    public Path getPicture() {
        return picture;
    }

    public void setPicture(Path picturePath) {
        setImage(picturePath);
    }

    private void setDefaultPic(){
        try {
            if (gender.equals("male") || gender.equals("Male")) {
                setImage("Male.jpg");
            } else {
                setImage("Female.jpg");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void setImage(String url) {
        picture = Paths.get(url);
    }

    private void setImage(Path path) {
        picture = path;
    }

    @Override
    public BigDecimal getComparableValue(){
        return id.getDecimalID();
    }

    @Override
    public String toString(){
        return String.format("%nFirstName: %s  LastName: %s%nID: %s%nAge: %d%nSocialSecurityNumber: %s%nGender: %s%n",
                getFirstName(),getLastName(),getID(),getAge(),getSocialSecurityNumber(),getGender());
    }

    @Override
    public int compareTo(Account o) {
        return id.getCode().compareTo(o.getComparableValue());
    }
}
