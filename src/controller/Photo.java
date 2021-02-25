package ru.vkchan.bot.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.api.sdk.actions.Photos;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.responses.GetMessagesUploadServerResponse;
import com.vk.api.sdk.objects.photos.responses.PhotoUploadResponse;
import com.vk.api.sdk.objects.photos.responses.SaveMessagesPhotoResponse;
import org.apache.commons.io.FileUtils;
import ru.vkchan.bot.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.List;

public class Photo {
    static File photo=new File("C:\\Users\\Евгений\\Desktop\\VkBot\\src\\main\\resources\\kaguya.jpg");;
    static Gson g=new Gson();
    static VkApiClient vk= Main.getApi();
    public static String uploadPhoto(GroupActor actor, String url1) throws ClientException, ApiException, IOException {
        URL pUrl=new URL(url1);
        urlToFile(pUrl,photo);
        Photos photos=new Photos(vk);
            GetMessagesUploadServerResponse url=photos.getMessagesUploadServer(actor).execute();
        PhotoUploadResponse a= vk.upload().photo(url.getUploadUrl().toString(), photo).execute();
        List<SaveMessagesPhotoResponse> photo = photos.saveMessagesPhoto(actor, a.getPhoto()).server(a.getServer()).hash(a.getHash()).execute();
        int id = photo.get(0).getId();
        int albId = photo.get(0).getAlbumId();
        int ownerId=photo.get(0).getOwnerId();
        String accessKey = photo.get(0).getAccessKey();
        System.out.println("photo"+ownerId+"_"+id+"_"+accessKey);
        return "photo"+ownerId+"_"+id+"_"+accessKey;
    }
    public static String getPhotoUrl(String json){
        JsonObject jsonObject= (JsonObject) JsonParser.parseString(json);
     //   jsonObject.get("attachments");
  //      System.out.println(jsonObject.get("attachments").getAsJsonArray().get(0).getAsJsonObject().get("photo").getAsJsonObject().get("sizes").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString());

        String out = jsonObject.get("attachments").getAsJsonArray().get(0).getAsJsonObject().get("photo").getAsJsonObject().get("sizes").getAsJsonArray().get(5).getAsJsonObject().get("url").getAsString();
        System.out.println("out");
        return out;
    }
    public static void urlToFile(URL pUrl, File file) throws IOException {
        FileUtils.copyURLToFile(pUrl, file);
    }
    public static String imgToByte() throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(photo);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }
}