package ru.vkchan.bot.search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class AnimeShikimori {
    private static String stars;
    private static String description;
    private static String link;
    private static String imageUrl;
    private static void getFinPage(String input) throws IOException {
        String getLink=page(input).selectFirst("div[class=cover linkeable anime-tooltip]").attr("data-href");
        link=getLink;
        Document finalPage=page2(getLink);

        Element desc=finalPage.selectFirst("div[class=b-text_with_paragraphs]");
        description=desc.text();
        Element rating=finalPage.selectFirst("div[class=text-score]");
        stars=rating.text().split(" ")[0];
        String img=finalPage.selectFirst("div[class=c-poster]").selectFirst("img").attr("src");
        imageUrl=img;
        System.out.println(img);
    }

    private static Document page2(String inputURL) throws IOException {
        return Jsoup.connect(inputURL).timeout(3000).userAgent("Mozilla").get();
    }
    private static Document page(String inputString) throws IOException {
        StringBuilder url= new StringBuilder("https://shikimori.one/animes?search=");
        url.append(inputString.split(" ")[0]);
        String[] a =inputString.split(" ");
        for (int i=1; i<a.length; i++){
            url.append("+").append(a[i]);
        }
        return Jsoup.connect(url.toString()).userAgent("Mozilla").timeout(3000).get();
    }
    public static String output(String input) throws IOException {
        getFinPage(input);
        return link+"\nОценка: "+stars+"/10 по данным shikimori\n"+"\nКраткое описание:\n"+description;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    /*  public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        String name=" ";
        while (true){
        name=scanner.nextLine();
        if (name=="end") break;
        System.out.println(output(name));
        }
    }
*/


}
