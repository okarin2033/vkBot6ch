package ru.vkchan.bot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.vkchan.bot.controller.GroupMess;
import ru.vkchan.bot.controller.Photo;
import ru.vkchan.bot.temp.UsersStatus;

import java.util.concurrent.TimeUnit;

public class Main {
    final static int
            groupId=91059910;
    final static String
            GROUP_TOKEN="978688da1c2af3a0a3ed507e9cbcfaffe4684b5e56f562249cb3229060469d5c2783b6b300c9271eedc5f";
    static VkApiClient vk;
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        Thread1 thread1=new Thread1();
        thread1.start();
        System.out.println("F");

        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        GroupActor actor = new GroupActor(groupId, GROUP_TOKEN);
        Integer ts=vk.messages().getLongPollServer(actor).execute().getTs();
        GroupMess.init(vk,actor,ts);

        while (true){
            GroupMess.work();
            GroupMess.ts=vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(1000);
        }
  //    System.out.println(Photo.uploadPhoto(actor));

    }
public static VkApiClient getApi(){
        return vk;
}
}


class Thread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000000);
                UsersStatus.clear();
                System.out.println("Cache cleared");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
