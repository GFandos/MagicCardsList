package com.example.a47989768s.magiccardslist;

import java.io.Serializable;

/**
 * Created by 47989768s on 19/10/16.
 */

public class Card implements Serializable {

    private String name;
    private String rarity;
    private String type;
    private String imageUrl;
    private String color;
    private String description;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Card(String _name, String _rarity, String _type, String _imageUrl, String _color, String _description) {

        name = _name;
        rarity = _rarity;
        type = _type;
        imageUrl = _imageUrl;
        color = _color;
        description = _description;

    }

    public Card() {

    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl'" + imageUrl + '\'' +
                ", color" + color + "\'" +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
