public class AdminUser extends User {
    public void banUser(Bannable u) {
        u.temporarilyBanUser();
    }

    public void unbanUser(Bannable u) {
        if (u.checkUserBanStatus()) {
            u.unbanUser();
        }
    }
}