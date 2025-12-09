package com.poly.asm.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Videos")
@NamedQueries({
    @NamedQuery(name="Video.findAll", query="SELECT v FROM Video v") 
    // Đã xóa dòng Video.findRandom bị lỗi tại đây
})
public class Video {

    @Id
    @Column(name="Id")
    private String id; // Youtube ID

    @Column(name="Title")
    private String title;

    @Column(name="Poster")
    private String poster;

    @Column(name="Views")
    private Integer views = 0;

    @Column(name="Description")
    private String description;

    @Column(name="Active")
    private Boolean active = true;

    // Liên kết 1-N tới Favorite
    @OneToMany(mappedBy="video")
    List<Favorite> favorites;

    // Liên kết 1-N tới Share
    @OneToMany(mappedBy="video")
    List<Share> shares;

    // --- Constructors ---

    public Video() {
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }
}