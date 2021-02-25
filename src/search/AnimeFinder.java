package ru.vkchan.bot.search;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Scanner;

public class AnimeFinder {
    private  final static String API="https://trace.moe/api/search?url=";
    public static String getNameOfAnime(String JsonObj) throws IOException {
        String time;
        String request=JsonObj;
        assert request != null;
        JsonObject object=JsonParser.parseString(request).getAsJsonObject();
        System.out.println(object);
        String name="";
        try {
            name = object.getAsJsonArray("docs").get(0).getAsJsonObject().get("title_english").getAsString();
        } catch (Exception e){
            name = object.getAsJsonArray("docs").get(0).getAsJsonObject().get("title_romaji").getAsString();
        }
        System.out.println(name);
        try {
             time = object.getAsJsonArray("docs").get(0).getAsJsonObject().get("episode").getAsString();
        } catch (Exception e) {
            time = "";
        }
        int similarity=0;
        try {
            similarity = Math.round(100 * object.getAsJsonArray("docs").get(0).getAsJsonObject().get("similarity").getAsFloat());
        } catch (Exception e){
            System.out.println("No similarity");
        }
        String times="";
        if (!time.equals("")) times+= time +" серия\n";
        return "С вероятностью "+ similarity +"% Это аниме \""+ name + "\" "+ times +"\n"+AnimeShikimori.output(name);
    }

   /*public static void main(String[] args) throws IOException {
      //  System.out.print("Введите ссылку на картинку >");
     //   Scanner scanner=new Scanner(System.in);
     //   String url=scanner.nextLine();
        //System.out.println(getNameOfAnime(url));
        System.out.println(AnimeShikimori.output("Атака титанов"));
    }*/
}
