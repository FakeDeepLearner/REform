import java.util.HashMap;

public class UserNameAndPasswordContainer<String> extends HashMap<String, String> {
    @Override
    public String get(Object key) throws UserNotFoundException{
        if(this.containsKey(key)){
            return super.get(key);
        }
        else{
            throw new UserNotFoundException();
        }
    }
}
