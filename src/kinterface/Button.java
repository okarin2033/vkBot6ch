package ru.vkchan.bot.kinterface;

import com.vk.api.sdk.objects.messages.*;

public class Button extends KeyboardButton {
    public Button(Type type) {
        setAction(new KeyboardButtonAction().setLabel(type.name).setType(TemplateActionTypeNames.TEXT));
        setColor(KeyboardButtonColor.POSITIVE);
    }
}
