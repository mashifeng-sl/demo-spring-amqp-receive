package cn.sailing3d.demospringamqpreceive.dto;

import lombok.Data;

@Data
public class CreatePost {
    String title;
    String content;
    String author;
}