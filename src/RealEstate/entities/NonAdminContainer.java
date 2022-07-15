package RealEstate.entities;

import LoginSystem.entities.NonAdminUser;
import LoginSystem.exceptions.UserNotFoundException;

public class NonAdminContainer<String, NonAdminUser> extends Container<java.lang.String, LoginSystem.entities.NonAdminUser>
{
    @Override
    public LoginSystem.entities.NonAdminUser get(Object key) throws IllegalArgumentException {
        try{
            return super.get(key);
        }
        catch (IllegalArgumentException illegalArgumentException){
            throw new UserNotFoundException();
        }
    }
}
