package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

public class Main {

  static class Vehicle {}

//  static class Car extends Vehicle {}
//
//  static class Bike extends Vehicle {}
//
//  public Vehicle getVehicle() {
//    return new Bike();
//  }

  public static void main(String[] args) {

    // Vehicle -> Car, Bus, Bike

    //temp();
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    //OrderManager orderManager = new OrderManager(new PaymentService());
    OrderManager orderManager = applicationContext.getBean("orderManager", OrderManager.class);
    orderManager.createOrder(List.of( "LAPTOP", "PHONE"), PaymentMethod.UPI);
  }

  private static void temp() {
    List<Person> persons = new ArrayList<>();
    Person p1 = new Person();
    p1.age = 45;
    p1.dob = LocalDate.of(1968,5,20);
    p1.name = "Suresh";
    p1.heightInCms = 167;
    p1.weight = new BigDecimal("68.4567937597");
    p1.religion = Religion.HINDU;

    persons.add(p1);

    Person p2 = new Person();
    p2.age = 45;
    p2.dob = LocalDate.of(1974,9,2);
    p2.name = "Krishna";
    p2.heightInCms = 180;
    p2.weight = new BigDecimal("74");
    p2.religion = Religion.MUSLIM;

    persons.add(p2);

    Person p3 = new Person();
    p3.age = 70;
    p3.dob = LocalDate.of(1995,5,20);
    p3.name = "Avinash";
    p3.heightInCms = 181;
    p3.weight = new BigDecimal("78.045");
    p3.religion = Religion.CHRISTIAN;

    persons.add(p3);

//    for(int i=0;i<persons.size();i++) {
//      Person p = persons.get(i);
//      if(p.religion == Religion.HINDU) {
//        System.out.println(p);
//      }
//    }

    // 1. Predicate, 2. Consumer, 3. Supplier, 4. Function

    Predicate<Person> hinduPersonCheck = (person) -> {
      return person.religion == Religion.HINDU;
    };

    Consumer<Person> personConsumer = (person) -> System.out.println(person);

    persons.stream().filter(person -> abc(hinduPersonCheck, person)).forEach(personConsumer);
    //System.out.println(hinduPersons);

    //Random random = new Random();
//    IntStream.range(0, 1000).forEach(val -> {
//      Person p = new Person();
//      p.age = random.nextInt(10,70);
//    });



  }

  public static boolean abc(Predicate<Person> personPredicate, Person person) {
    return personPredicate.test(person);
  }

  static class Person {
    int age;
    String name;
    LocalDate dob;

    BigDecimal weight;

    int heightInCms;

    Religion religion;

    public String toString() {
      return "name: " + name + ", age: " + age;
    }

  }

  enum Religion { HINDU, CHRISTIAN, MUSLIM}

  static class HinduPersonCheck implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
      System.out.println("predicate called");
      return person.religion == Religion.HINDU;
    }
  }

  static class PersonPrinter implements Consumer<Person> {

    @Override
    public void accept(Person person) {
      System.out.println(person);
    }
  }
}