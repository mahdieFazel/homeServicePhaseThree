package com.example.homeservicephasethree.validation;

import com.example.homeservicephasethree.exception.ImageFormatException;
import com.example.homeservicephasethree.exception.ImageSizeException;

import java.util.Objects;

public class ImageValidation {
    public static void checkImage(String imageName, Long imageSize) {
        String[] imageNameSplit = imageName.split("\\.");
        if(!Objects.equals(imageNameSplit[imageNameSplit.length - 1].toLowerCase(), "jpg"))
            throw new ImageFormatException("image must be jpg");
        if(imageSize > 300000L)
            throw new ImageSizeException("image size must be less than 300 kb");
    }
}
