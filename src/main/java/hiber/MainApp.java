package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ruslan", "Perepelitsa", "ruslan_perepelitsa@gmail.com");
        User user2 = new User("Ivan", "Ivanov", "ivan_ivanov@gmail.com");
        User user3 = new User("Petr", "Petrov", "petr_petrov@gmail.com");
        User user4 = new User("Nikolay", "Nikolaev", "kolya1@gmail.com");

        Car car1 = new Car("Camry", 50);
        Car car2 = new Car("Corolla", 100);
        Car car3 = new Car("A4", 8);
        Car car4 = new Car("A6", 6);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        System.out.println("Все пользователи с машинами:");
        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println("Пользователь с определенной машиной:");
        System.out.println(userService.getUserByCar("A4", 8));
        context.close();
    }
}

