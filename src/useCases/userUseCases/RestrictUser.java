package useCases.userUseCases;

import entities.Bannable;
import entities.NonAdminUser;
import entities.User;
import entities.containers.UserContainer;
import exceptions.UserCannotBeBannedException;


public class RestrictUser {
    private final UserContainer<String, User> interfaceUsers;

    public RestrictUser(UserContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;
    }

    /**
     * Temporarily bans entities.User if they are an entities.NonAdminUser.
     *
     * @param username of the entities.User to be banned.
     * @return true if the entities.User is successfully banned.
     */
    public boolean banNonAdminUser(String username) {
        User u = interfaceUsers.get(username);
        if (u instanceof Bannable) {
            NonAdminUser user = (NonAdminUser) u;
            return user.temporarilyBanUser();
        } else {
            return false;
        }
    }

    /**
     * Unbans entities.User if they are an entities.NonAdminUser.
     *
     * @param username of the entities.User to be unbanned.
     * @return true if the entities.User is successfully unbanned.
     */
    public boolean unbanNonAdminUser(String username) {
        User u = interfaceUsers.get(username);
        if (u instanceof Bannable) {
            NonAdminUser user = (NonAdminUser) u;
            return user.unbanUser();
        } else {
            return false;
        }
    }

    /**
     * Deletes the entities.User if they are an entities.NonAdminUser.
     *
     * @param username of the entities.User to be deleted.
     * @return true if the entities.User is successfully deleted.
     */
    public boolean deleteNonAdminUser(String username) {
        User u = interfaceUsers.get(username);
        if (u instanceof Bannable) {
            interfaceUsers.remove(username);

            return true;
        } else {
            return false;
        }
    }

    public boolean isUserBanned(String username) {
        User u = interfaceUsers.get(username);
        if (u instanceof Bannable) {
            return ((Bannable) u).checkUserBanStatus();
        } else {
            throw new UserCannotBeBannedException();
        }
    }
}
