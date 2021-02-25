package ru.vkchan.bot.search;


import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.vkchan.bot.controller.Photo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

public class PostToImgSearch {

    public static String post(File file) throws IOException {
      /*  final Content getResult = Request.Get("http://jsonplaceholder.typicode.com/posts?_limit=10").
                execute().returnContent();
        System.out.println(getResult.asString()); */

        final Collection<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("image", Photo.imgToByte()));

        final Content postResultForm = Request.Post("https://trace.moe/api/search")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        return postResultForm.asString();
    }
}
