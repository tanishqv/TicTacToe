package com.scaler.model;

public class Symbol {
    char sign;
    String imagePath;

    public Symbol(char sign, String imagePath) {
        this.sign = sign;
        this.imagePath = imagePath;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
