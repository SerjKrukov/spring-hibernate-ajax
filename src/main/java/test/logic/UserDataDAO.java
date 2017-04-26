package test.logic;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class UserDataDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void addUserData(UserData userData) {
        entityManager.persist(userData);
        return;
    }
    @SuppressWarnings("unchecked")
    public List<UserData> getAllUserData() {
        return entityManager.createQuery("from UserData").getResultList();
    }
    public UserData getById(long id) {
        return entityManager.find(UserData.class, id);
    }
    public UserData getByName(String name) {
        return (UserData) entityManager.createQuery("from UserData name = :name").setParameter("name", name).getSingleResult();
    }
    public void updateUserData(UserData userData) {
        entityManager.merge(userData);
        return;
    }
    public void removeUserData(UserData userData) {
        if (entityManager.contains(userData)) {
            entityManager.remove(userData);
        } else {
            entityManager.remove(entityManager.merge(userData));
        }
    }
}
