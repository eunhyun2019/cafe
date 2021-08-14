package com.enhyun.enhyuntest;

public class MenuItem {
    int menu_image_id;
    int cafe_id;
    String category;
    String imgPath;
    String date;

    public MenuItem(int menu_image_id, int cafe_id, String category, String imgPath, String date) {
        this.menu_image_id = menu_image_id;
        this.cafe_id = cafe_id;
        this.category = category;
        this.imgPath = imgPath;
        this.date = date;
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
}
