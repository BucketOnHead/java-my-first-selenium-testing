package swaglabs.test.login.script;

/**
 * Цель: проверить форму входа
 */
public interface AuthFormTest {

    /**
     * Цель: проверить авторизацию с пустым логином
     * <p>
     * Предусловия:
     * <p>
     * Шаги:
     * <ol>
     *   <li>открыть страницу авторизации</li>
     *   <li>ввести верный пароль</li>
     *   <li>нажать войти или enter</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>авторизация не пройдена</li>
     *   <li>сообщение отображается</li>
     *   <li>текст сообщения совпадает с шаблоном</li>
     * </ul>
     */
    void authEmptyUsername();

    /**
     * Цель: проверить авторизацию с пустым паролем
     * <p>
     * Предусловия:
     * <p>
     * Шаги:
     * <ol>
     *   <li>открыть страницу авторизации</li>
     *   <li>ввести верный логин</li>
     *   <li>нажать войти или enter</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>авторизация не пройдена</li>
     *   <li>сообщение отображается</li>
     *   <li>текст сообщения совпадает с шаблоном</li>
     * </ul>
     */
    void authEmptyPassword();

    /**
     * Цель: проверить авторизацию с пустыми полями
     * <p>
     * Предусловия:
     * <p>
     * Шаги:
     * <ol>
     *   <li>открыть страницу авторизации</li>
     *   <li>нажать войти или enter</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>авторизация не пройдена</li>
     *   <li>сообщение отображается</li>
     *   <li>текст сообщения совпадает с шаблоном</li>
     * </ul>
     */
    void authEmptyUsernameAndPassword();
}
