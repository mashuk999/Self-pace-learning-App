package com.example.deepak.androidclub;

public class VideoUrl {

    String videourl,source,subject,difficulty,lecturenum;

    public VideoUrl() {
    }

    public VideoUrl(String videourl, String source, String subject, String difficulty, String lecturenum) {
        this.videourl = videourl;
        this.source = source;
        this.subject = subject;
        this.difficulty = difficulty;
        this.lecturenum = lecturenum;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLecturenum() {
        return lecturenum;
    }

    public void setLecturenum(String lecturenum) {
        this.lecturenum = lecturenum;
    }
}
