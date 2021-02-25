package ru.vkchan.bot.kinterface;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;

import java.util.List;

public class CustomKeyboard extends Keyboard {
    public CustomKeyboard(List<List<KeyboardButton>> list) {
        Keys.init();
        this.setButtons(list);
    }
}
