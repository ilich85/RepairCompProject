package com.level.main;

import com.level.dao.entity.*;
import com.level.dao.interfaces.EntityDao;
import com.level.hibernateFactory.Factory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FillInfoForTest {
    static void fill() {
        String date = new SimpleDateFormat().format(new Date());
        User user1 = new User("anton", "anton@mail.ru", "K@kouToPa$$", "0572626364");
        User user2 = new User("andrey", "andrey@gmail.com", "123456789", "0677345689");
        User user3 = new User("oksana", "oksana@gmail.com", "Roksana", "0997325518");

        EntityDao userDao = Factory.getInstance().getUsersDao();
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        EntityDao commentDao = Factory.getInstance().getCommentsDao();
        commentDao.add(new Comments(date, "Хорошая компания. Успехов им и процветания.", user2));
        commentDao.add(new Comments(date, "Все отлично сделали, и гарантию дали приличную. Я доволен.", user3));
        commentDao.add(new Comments(date, "Добрый отзывчивый персонал.", user2));
        commentDao.add(new Comments(date, "Быстро и качественно все сделали. Вопросов нет.", user3));

        Factory.getInstance().getAdminsDao()
                .add(new Admin("superadmin", "admin@gmail.com", "superadmin"));

        EntityDao messageDao = Factory.getInstance().getMessagesDao();
        messageDao.add(new Messages(date, "С 15 по 25 ноября проходит акция на мониторы б/у." +
                "Успей купить себе монитор по выгодной цене!"));
        messageDao.add(new Messages(date, "С 18 по 23 ноября проходит акция на системные блоки б/у." +
                "Успей купить себе системный блок по выгодной цене!"));

        EntityDao orderDao = Factory.getInstance().getOrdersDao();
        orderDao.add(new Orders("При включении кнопки питания, не происходит ничего.", date, user3));
        orderDao.add(new Orders("Сильно греется ноутбук. Пора чистить?", date, user1));
        orderDao.add(new Orders("При включении ноутбука, черный экран.", date, user2));

        EntityDao serviceDao = Factory.getInstance().getServicesDao();
        serviceDao.add(new Services("Замена экрана на ноутбуке", 200));
        serviceDao.add(new Services("Установка Windows", 100));
        serviceDao.add(new Services("Замена клавиатуры на ноутбуке", 30));
        serviceDao.add(new Services("Очистка от пыли и замена термопасты", 100));
        serviceDao.add(new Services("Установка программ", 100));
    }
}