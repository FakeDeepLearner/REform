package useCases.userUseCases;

import entities.users.Bannable;
import entities.users.NonAdminUser;
import entities.users.User;
import entities.containers.UserContainer;
import exceptions.UserCannotBeBannedException;


public class RestrictUser {
    private final UserContainer<String, User> interfaceUsers;

    public RestrictUser(UserContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;
    }

    /**
     * Temporarily bans entities.users.User if they are an entities.users.NonAdminUser.
     *
     * @param username of the entities.users.User to be banned.
     * @return true if the entities.users.User is successfully banned.
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
     * Unbans entities.users.User if they are an entities.users.NonAdminUser.
     *
     * @param username of the entities.users.User to be unbanned.
     * @return true if the entities.users.User is successfully unbanned.
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
     * Deletes the entities.users.User if they are an entities.users.NonAdminUser.
     *
     * @param username of the entities.users.User to be deleted.
     * @return true if the entities.users.User is successfully deleted.
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
