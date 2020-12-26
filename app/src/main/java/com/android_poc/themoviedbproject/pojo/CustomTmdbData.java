package com.android_poc.themoviedbproject.pojo;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "CustomTmdbData",indices = @Index(value = {"title"},unique = true))
public class CustomTmdbData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    private String description;
    private String displayDate;
    private Long releasedDate;
    private Integer voteCount;
    private String posterImgUrl;
    private Long popularityCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public Long getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Long releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterImgUrl() {
        return posterImgUrl;
    }

    public void setPosterImgUrl(String posterImgUrl) {
        this.posterImgUrl = posterImgUrl;
    }

    public Long getPopularityCount() {
        return popularityCount;
    }

    public void setPopularityCount(Long popularityCount) {
        this.popularityCount = popularityCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomTmdbData{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", displayDate='").append(displayDate).append('\'');
        sb.append(", releasedDate=").append(releasedDate);
        sb.append(", voteCount=").append(voteCount);
        sb.append(", posterImgUrl='").append(posterImgUrl).append('\'');
        sb.append(", popularityCount=").append(popularityCount);
        sb.append('}');
        return sb.toString();
    }
}
