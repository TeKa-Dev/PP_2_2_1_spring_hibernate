package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Car car;
      try {
         car = (Car) sessionFactory.getCurrentSession()
                 .createQuery("from Car where model = :m and series = :s")
                 .setParameter("m", model)
                 .setParameter("s", series)
                 .list()
                 .get(0);

      } catch (IndexOutOfBoundsException e) {
         throw new RuntimeException("\nCaused: persistence " + Car.class +
                 " with the given parameters model=" + model +
                 ", series=" + series + " was not found in the database");
      }
      return car.getOwner();
   }

}
