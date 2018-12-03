package com.example.ricky.comet.Utencilios;

public class Comentario_item {

    String news_nombre_user;
    int
    news_img_perfil_user,
    news_img_comen_user;


    public Comentario_item(String news_nombre_user, int news_img_perfil_user, int news_img_comen_user) {
        this.news_nombre_user = news_nombre_user;
        this.news_img_perfil_user = news_img_perfil_user;
        this.news_img_comen_user = news_img_comen_user;
    }

    public String getNews_nombre_user() {
        return news_nombre_user;
    }

    public void setNews_nombre_user(String news_nombre_user) {
        this.news_nombre_user = news_nombre_user;
    }

    public int getNews_img_perfil_user() {
        return news_img_perfil_user;
    }

    public void setNews_img_perfil_user(int news_img_perfil_user) {
        this.news_img_perfil_user = news_img_perfil_user;
    }

    public int getNews_img_comen_user() {
        return news_img_comen_user;
    }

    public void setNews_img_comen_user(int news_img_comen_user) {
        this.news_img_comen_user = news_img_comen_user;
    }
}
