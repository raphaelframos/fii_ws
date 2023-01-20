package com.raphaelframos.refii.common.model;

import java.io.Serializable;
import java.util.List;

public class DetailFund implements Serializable {

    private String name;
    private String fullName;
    private List<Option> details;
    private List<String> links;
    private List<CommentFund> comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Option> getDetails() {
        return details;
    }

    public void setDetails(List<Option> details) {
        this.details = details;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<CommentFund> getComments() {
        return comments;
    }

    public void setComments(List<CommentFund> comments) {
        this.comments = comments;
    }
}
