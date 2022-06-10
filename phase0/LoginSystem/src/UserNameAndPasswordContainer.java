import java.util.HashMap;

public class UserNameAndPasswordContainer<String, User> extends HashMap<String, User>{
    @Override
    public User get(Object key) throws UserNotFoundException{
        if(this.containsKey(key)){
            return super.get(key);
        }
        else{
            throw new UserNotFoundException();
        }
    }
}
