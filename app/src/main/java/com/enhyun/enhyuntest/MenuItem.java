package com.enhyun.enhyuntest;

public class MenuItem {
    int menu_image_id;
    int cafe_id;
    String category;
    String imgPath;
    String date;
    int menu_id;
    String menu_name;
    String menu_price;

    public MenuItem(int menu_image_id, int cafe_id, String category, String imgPath, String date, int menu_id, String menu_name, String menu_price) {
        this.menu_image_id = menu_image_id;
        this.cafe_id = cafe_id;
        this.category = category;
        this.imgPath = imgPath;
        this.date = date;
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_price = menu_price;
    }

    public int getMenu_image_id() {
        return menu_image_id;
    }

    public void setMenu_image_id(int menu_image_id) {
        this.menu_image_id = menu_image_id;
    }

    public int getCafe_id() {
        return cafe_id;
    }

    public void setCafe_id(int cafe_id) {
        this.cafe_id = cafe_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }
}
