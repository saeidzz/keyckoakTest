package ir.itsurena.saba.security.dao;
import javax.annotation.Resource;
import ir.itsurena.saba.security.model.Person;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public class UserDao {

    @Resource(name="redisTemplate")
    private SetOperations<String, Person> setOps ;


    public void addUser(String KEY,Person... persons) {
        setOps.add(KEY, persons);
    }

    public Set<Person> getUsers(String KEY) {
        return setOps.members(KEY);
    }
    public long getNumberOfUsers(String KEY) {
        return setOps.size(KEY);
   }
    public long removeUser(String KEY,Person... persons) {
        return setOps.remove(KEY, (Object[])persons);
    }
}