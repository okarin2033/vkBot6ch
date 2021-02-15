package ru.vkchan.bot.controller;

import com.vk.api.sdk.actions.Photos;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import ru.vkchan.bot.kinterface.CustomKeyboard;
import ru.vkchan.bot.kinterface.Keys;

import ru.vkchan.bot.search.AnimeFinder;
import ru.vkchan.bot.search.AnimeShikimori;
import ru.vkchan.bot.search.PostToImgSearch;
import ru.vkchan.bot.temp.UsersStatus;

import java.net.URL;
import java.util.List;
import java.util.Random;

public class GroupMess {
    static MessagesGetLongPollHistoryQuery historyQuery;
    public static Integer ts;
    static VkApiClient vk;
    static GroupActor actor;
    static Photos photos;
    static String attachmentUrl;
    public static void init(VkApiClient vk, GroupActor actor, Integer ts) {
        GroupMess.actor = actor;
        GroupMess.vk = vk;
        GroupMess.ts = ts;
        photos=new Photos(vk);
    }

    static Random random = new Random();

    public static void work() throws ClientException, ApiException {

        historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
        List<Message> messages = historyQuery.execute().getMessages().getItems();
       if (!messages.isEmpty()) {
            messages.forEach(message -> {
                try {
                        messageHandler(message, message.getFromId());
                } catch (ClientException | ApiException e) {
                    e.printStackTrace();
                }
            });
        }

    }
    public static void messageHandler(Message message, int id) throws ClientException, ApiException {         //   vk.messages().send(actor).message("Сначала вступи в группу b-b-baka! ").attachment("photo91059910_457254607").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
        System.out.println(message.toString());
        try {
            attachmentUrl = Photo.getPhotoUrl(message.toString());
            Photo.urlToFile(new URL(attachmentUrl), Photo.photo);
            System.out.println(attachmentUrl);
        //   System.out.println(AnimeFinder.getNameOfAnime(PostToImgSearch.post(Photo.photo)));
        } catch (Exception e)
        {
            System.out.println("Message without attachment");
        }
            if (!UsersStatus.checkExist(message.getFromId()))
                UsersStatus.setStatus("null", message.getFromId());
            System.out.println();
            if (UsersStatus.getStatus(message.getFromId()).equals("wft") && (!message.getText().equals("Назад"))) {
                vk.messages().send(actor).message("Ищем аниме в базе данных...").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                try {
                    vk.messages().send(actor).message(AnimeShikimori.output(message.getText())).userId(message.getFromId()).attachment(Photo.uploadPhoto(actor,AnimeShikimori.getImageUrl())).randomId(random.nextInt(10000)).execute();
                } catch (Exception e) {
                    vk.messages().send(actor).message("По запросу ничего не найдено").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                    e.printStackTrace();
                    //   UsersStatus.setStatus("null", message.getFromId());
                }
            }

            if (UsersStatus.getStatus(message.getFromId()).equals("wfi") && (!message.getText().equals("Назад"))) {
                vk.messages().send(actor).message("Ищем скрин в базе данных...").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                try {
                    //vk.messages().send(actor).message(AnimeFinder.getNameOfAnime(attachmentUrl)).*/attachment(Photo.uploadPhoto(actor,AnimeShikimori.getImageUrl())).attachment(Photo.uploadPhoto(actor,attachmentUrl)).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                    vk.messages().send(actor).message(AnimeFinder.getNameOfAnime(PostToImgSearch.post(Photo.photo))).attachment(Photo.uploadPhoto(actor,AnimeShikimori.getImageUrl())).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                } catch (Exception e) {
                    //   UsersStatus.setStatus("null", message.getFromId());
                    vk.messages().send(actor).message("По запросу ничего не найдено").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
              e.printStackTrace();
                }
            }
            //
       // if (message.getText().equals("Тест фото"))
         //   vk.messages().send(actor).message("Обложка аниме с шикимори").attachment(Photo.uploadPhoto(actor)).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
            //
        if (message.getText().equals("Привет"))
                vk.messages().send(actor).message("Привет! Напиши \"Начать\" чтобы воспользоваться функциями бота <3").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
            if (message.getText().equals("Начать"))
                vk.messages().send(actor).message("Добро пожаловать! Сейчас наш бот умеет искать аниме по картинке и искать информацию о нем в базе данных").keyboard(new CustomKeyboard(Keys.MENU)).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
            if (message.getText().equals("Поиск аниме")) {
                UsersStatus.setStatus("wft", message.getFromId());
                vk.messages().send(actor).message("Введите название аниме для поиска").keyboard(new CustomKeyboard(Keys.BackKey)).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
            }
            if (message.getText().equals("Поиск по картинке")) {
                UsersStatus.setStatus("wfi", message.getFromId());
                vk.messages().send(actor).message("Отравте скрин из аниме(или ссылку, но подождите пока вк не загрузит ее в виде изображения) для поиска по картинке").keyboard(new CustomKeyboard(Keys.BackKey)).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
            }
            if (message.getText().equals("Назад")) {
                UsersStatus.setStatus("null", message.getFromId());
                vk.messages().send(actor).message("Выберите команду").keyboard(new CustomKeyboard(Keys.MENU)).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                UsersStatus.setStatus("null", message.getFromId());
            }
    }
}
