public interface Bannable {

    /**
     * Returns whether the specified user is currently banned.
     * @param u user to check if banned.
     * @return true if the user is banned.
     */
    boolean checkUserBanStatus(User u);

    /**
     * Bans specified user temporarily.
     * @param u user to temporarily ban.
     * @return true if the user is successfully banned.
     */
    boolean temporarilyBanUser(User u);

    /**
     * Unbans specified user.
     * @param u user to unban.
     * @return true if the user is successfully unbanned.
     */
    boolean unbanUser(User u);

}
