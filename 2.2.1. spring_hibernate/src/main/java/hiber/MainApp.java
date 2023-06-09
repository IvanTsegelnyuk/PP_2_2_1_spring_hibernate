package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("toyota", 10);
      Car car2 = new Car("honda", 50);
      Car car3 = new Car("suzuki", 100);

      User user1 = new User("testName1", "testLastName1", "test1@gmail.com");
      user1.setCar(car1);
      User user2 = new User("testName2", "testLastName2", "test2@gmail.com");
      user2.setCar(car2);
      User user3 = new User("testName3", "testLastName3", "test3@gmail.com");
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCarAndSeries("toyota", 910));
      System.out.println(userService.getUserByCarAndSeries("honda", 50));
      System.out.println(userService.getUserByCarAndSeries("suzuki", 150));

      context.close();
   }
}
