package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {

    private int id;
    private byte[] data;
    private String mediatype;
    private int landmarkId;


    public Image(int id, byte[] data, String mediatype) {
        this.id = id;
        this.data = data;
        this.mediatype = mediatype;
    }

    public Image(byte[] data, String mediatype) {
        this(-1, data, mediatype);
    }

    public int getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public boolean hasId() {
        return this.id >= 0;
    }

    public String getMediatype() {
        return mediatype;
    }
}
