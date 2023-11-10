package swaglabs.test.login.script;

public interface StandardUserAuthTest {
    void authStandardUserBadUsername();

    void authStandardUserBadPassword();

    void authStandardUserBadUsernameAndPassword();

    void authStandardUserSuccess();
}
