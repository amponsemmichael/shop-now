package com.amponsem.model;

import java.sql.Blob;

public class Image {
    private Long id;
    private String fileName;
    private String fileType;

    private Blob image;
    public String downloadUrl;
}
