package useCases;

import Entities.Bannable;
import Entities.NonAdminUser;
import Entities.User;
import Entities.UserNameAndPasswordContainer;

public class RestrictUser {
    private UserNameAndPasswordContainer<String, User> interface_users;

    public RestrictUser(UserNameAndPasswordContainer<String, User> interface_users) {
        this.interface_users = interface_users;
    }

    /**
     * Temporarily bans Entities.User if they are a Entities.NonAdminUser.
     *
     * @param username of the Entities.User to be banned.
     * @return true if the Entities.User is successfully banned.
     */
    public boolean banNonAdminUser(String username) {
        User u = interface_users.get(username);
        if (u instanceof Bannable) {
            NonAdminUser user = (NonAdminUser) u;
            return user.temporarilyBanUser();
        } else {
            return false;
        }
    }

    /**
     * Unbans Entities.User if they are a Entities.NonAdminUser.
     *
     * @param username of the Entities.User to be unbanned.
     * @return true if the Entities.User is successfully unbanned.
     */
    public boolean unbanNonAdminUser(String username) {
        User u = interface_users.get(username);
        if (u instanceof Bannable) {
            NonAdminUser user = (NonAdminUser) u;
            return user.unbanUser();
        } else {
            return false;
        }
    }

    /**
     * Deletes the Entities.User if they are a Entities.NonAdminUser.
     *
     * @param username of the Entities.User to be deleted.
     * @return true if the Entities.User is successfully deleted.
     */
    public boolean deleteNonAdminUser(String username) {
        User u = interface_users.get(username);
        if (u instanceof Bannable) {
            interface_users.remove(username);
            return true;
        } else {
            return false;
        }
    }
}
