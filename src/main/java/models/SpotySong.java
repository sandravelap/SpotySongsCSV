package models;

import java.util.Date;

public class SpotySong {
    private String songName;
    private String[] artistsNames;
    private Date releaseDate;
    private Integer bpm;
    private String coverUrl;

    public SpotySong() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String[] getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String[] artistsNames) {
        this.artistsNames = artistsNames;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
