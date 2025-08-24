import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;

    public Person(String name, String surname) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please insert correct Person name");
        }
        this.name = name;
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Please insert correct Person surname");
        }
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this(name, surname);
        if (age < 0) {
            throw new IllegalArgumentException("Please insert correct Person age");
        }
        this.age = age;
    }

    public Person(PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.surname = personBuilder.surname;
        this.age = personBuilder.age;
        this.address = personBuilder.address;
    }


    public boolean hasAge() {
        if (OptionalInt.of(age).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean hasAddress() {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        if (hasAge()) {
            return OptionalInt.of(age);
        }
        System.out.println("Age has not been updated. Please, insert age");
        return null;
    }

    public String getAddress() {
        if (hasAddress()) {
            return address;
        }
        System.out.println("Address has not been updated. Please, insert address");
        return null;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        age++;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }

    //статический внутренний класс
    public static class PersonBuilder {
        private String name;
        private String surname;
        private int age;
        private String address;

        public PersonBuilder() {
            super();
        }

        public PersonBuilder setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Please insert correct Person name");
            }
            this.name = name;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            if (surname == null || surname.trim().isEmpty()) {
                throw new IllegalArgumentException("Please insert correct Person surname");
            }
            this.surname = surname;
            return this;
        }

        public PersonBuilder setAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Please insert correct Person age");
            }
            this.age = age;
            return this;
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Person build() {
            Person person = null;
            if (validatePerson()) {
                person = new Person(this);
            } else {
                System.out.println("Sorry! Person can't be build.");
            }
            return person;
        }

        private boolean validatePerson() {
            if (surname == null || surname.trim().isEmpty()) {
                throw new IllegalArgumentException("Please insert correct Person surname");
            } else if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Please insert correct Person name");
            }else if (age < 0) {
                throw new IllegalArgumentException("Please insert correct Person age");
            }
            return true;
        }


    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder childBuilder = new PersonBuilder().setSurname(this.surname).setAddress(this.address);
        return childBuilder;
    }
}