package com.example.recyclerviewexample;

public class Fruit {
    private int imageId;
    private String fruitName;

    public Fruit(int _imageId,String _fruitName)
    {
        this.imageId = _imageId;
        this.fruitName = _fruitName;
    }

    public int getImageId() {
        return imageId;
    }

    public String getFruitName() {
        return fruitName;
    }
}
