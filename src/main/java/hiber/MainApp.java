package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      Car car5 = new Car("УАЗ", 222);
      Car car6 = new Car("Ford", 556);
      user5.setCar(car5);
      user6.setCar(car6);
      userService.add(user5);
      userService.add(user6);

      System.out.println(userService.getUser("УАЗ", 222));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("ModelCar = "+user.getCar().getModel());
         System.out.println("SeriesCar = "+user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
