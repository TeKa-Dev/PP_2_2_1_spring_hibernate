package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Задание:
 * 1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.
 * 2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
 * 3. Добавьте этот класс в настройки hibernate.
 * 4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
 * 5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
 */

public class MainApp {
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("m1", 100)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("m2", 200)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("m3", 300)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("m4", 400)));

        System.out.println(userService.listUsers());

        System.out.println(userService.getUserByCar("m3", 200));

        context.close();
    }
}
