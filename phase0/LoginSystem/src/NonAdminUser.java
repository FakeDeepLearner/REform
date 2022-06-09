public class NonAdminUser extends User implements Bannable{
    @Override
    public boolean checkUserBanStatus(User u) {
        return false;
    }

    @Override
    public boolean temporarilyBanUser(User u) {
        return false;
    }

    @Override
    public boolean unbanUser(User u) {
        return false;
    }

}
