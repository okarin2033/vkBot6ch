package ru.vkchan.bot.kinterface;

public enum Type {
    PIC("Поиск по картинке"),SEARCH("Поиск аниме"),BACK("Назад"),BEGIN("Начать");
    String name;
    Type(String name){
        this.name=name;
    }

}
