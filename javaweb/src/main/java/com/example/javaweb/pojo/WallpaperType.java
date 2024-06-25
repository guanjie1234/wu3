package com.example.javaweb.pojo;

public enum WallpaperType {
    ANIME(1,"anime"),SCENERY(2,"scenery"),ANIMAL(3,"animal"),SCIENCE(4,"sciencefiction"),GAME(5,"game");

    private int code;
    private String msg;
    WallpaperType(int ordinal, String name) {
        this.code = ordinal;
        this.msg = name;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
