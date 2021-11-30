package com.programing.cprograming.Model;

public class CommentModel {

    private String name;

    public CommentModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentModel(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    private String comment;
}
