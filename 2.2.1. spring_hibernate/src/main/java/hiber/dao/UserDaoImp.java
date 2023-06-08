package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      if (user.getCar() == null) sessionFactory.getCurrentSession().save(user);
      else {
         Car car = user.getCar();
         sessionFactory.getCurrentSession().save(car);
         sessionFactory.getCurrentSession().save(user);
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUser(long id) {
      User user = sessionFactory.getCurrentSession().get(User.class, id);
      return user;
   }

   @Override
   public User getUserByCar(String model, int series) {
      Query<User> query = sessionFactory.getCurrentSession().createQuery("from User as u where u.car.model=:param1 and u.car.series=:param2");
      query.setParameter("param1", model);
      query.setParameter("param2", series);
      //List<User> list = query.list();
      User user = null;
      if (!query.list().isEmpty()) {
         user = query.list().get(0);
         System.out.println(user);
      }
      return user;
   }
}
