package ru.vkchan.bot.kinterface;

import com.vk.api.sdk.objects.messages.KeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Keys {
    private static boolean initialised=false;
    static ArrayList<KeyboardButton> menuTop=new ArrayList<KeyboardButton>();
    static ArrayList<KeyboardButton> menuBot=new ArrayList<KeyboardButton>();
    public static List<List<KeyboardButton>> MENU = new ArrayList<List<KeyboardButton>>();
    static ArrayList<KeyboardButton> Back=new ArrayList<KeyboardButton>();
    public static List<List<KeyboardButton>> BackKey = new ArrayList<List<KeyboardButton>>();
    static ArrayList<KeyboardButton> begin=new ArrayList<KeyboardButton>();
    public static List<List<KeyboardButton>> Begin=new ArrayList<List<KeyboardButton>>();

    public static void init(){
        if (!initialised){
        menuTop.add(new Button(Type.SEARCH));
        menuBot.add(new Button(Type.PIC));
        Back.add(new Button(Type.BACK));
        begin.add(new Button(Type.BEGIN));
        BackKey.add(Back);
        MENU.add(menuTop); MENU.add(menuBot);
        Begin.add(begin);
            initialised=true;
        }
    }
}
