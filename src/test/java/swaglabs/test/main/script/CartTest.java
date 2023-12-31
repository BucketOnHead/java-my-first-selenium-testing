package swaglabs.test.main.script;

/**
 * Цель: проверить взаимодействие с корзиной
 */
public interface CartTest {

    /**
     * Цель: проверить открытие корзины
     * <p>
     * Предусловия:
     * <ol>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Шаги:
     * <ol>
     *   <li>Нажать кнопку со значком тележки</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>Открытие страницы с корзиной</li>
     * </ul>
     */
    void clickCart();

    /**
     * Цель: проверить добавление товара в корзину
     * <p>
     * Предусловия:
     * <ol>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Шаги:
     * <ol>
     *   <li>Добавить товар в корзину</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>В корзине 1 товар</li>
     *   <li>Иконка с количеством товаров должна показывать 1</li>
     *   <li>Имя, описание и цена соответствуют добавленному товару</li>
     * </ul>
     */
    void addProduct();

    /**
     * Цель: проверить удаление товара из корзины
     * <p>
     * Предусловия:
     * <ol>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Шаги:
     * <ol>
     *   <li>добавить товар в корзину</li>
     *   <li>убрать товар из корзины с помощью кнопки на товаре</li>
     *   <li>перейти в корзину</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>корзина пуста</li>
     * </ul>
     */
    void removeProduct();

    /**
     * Цель: проверить удаление товара из корзины
     * <p>
     * Предусловия:
     * <ol>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Шаги:
     * <ol>
     *   <li>добавить товар в корзину</li>
     *   <li>зайти в корзину</li>
     *   <li>убрать товар из корзины</li>
     *   <li>обновить страницу</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>корзина пуста</li>
     * </ul>
     */
    void removeProductFromCart();

    /**
     * Цель: проверить, что товары сохраняются в порядке добавления
     * <p>
     * Предусловия:
     * <ol>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Шаги:
     * <ol>
     *   <li>Добавить 3 товара в корзину (в случайном порядке)</li>
     *   <li>Зайти в корзину</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>В корзине 3 товара</li>
     *   <li>Товары расположены в порядке добавления (проверять по имени)</li>
     * </ul>
     */
    void productInCartInAdditionOrder();

    /**
     * Цель: проверить сохранение товаров после выхода из аккаунта
     * <p>
     * Предусловия:
     * <ol>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Шаги:
     * <ol>
     *   <li>Добавить товар в корзину</li>
     *   <li>открыть левое меню</li>
     *   <li>выбрать logout</li>
     *   <li>пройти авторизацию</li>
     * </ol>
     * Ожидаемый результат:
     * <ul>
     *   <li>В корзине 1 товар</li>
     *   <li>Иконка с количеством товаров должна показывать 1</li>
     *   <li>Имя, описание и цена соответствуют добавленному товару</li>
     * </ul>
     */
    void cartSaveAfterLogout();
}
