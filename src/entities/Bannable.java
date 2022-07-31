package entities;

public interface Bannable {

    /**
     * Returns whether the user is currently banned.
     * @return true if the user is banned.
     */
    boolean checkUserBanStatus();

    /**
     * Bans user temporarily.
     * @return true if the user is successfully banned.
     */
    boolean temporarilyBanUser();

    /**
     * Unbans user.
     * @return true if the user is successfully unbanned.
     */
    boolean unbanUser();

}
