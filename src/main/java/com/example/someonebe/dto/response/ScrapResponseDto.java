package com.example.someonebe.dto.response;

import lombok.Getter;

@Getter
public class ScrapResponseDto {

    private int scrapcount;
    private boolean scrapstatus;

    public ScrapResponseDto(int scrapcount, boolean scrapstatus) {
        this.scrapcount = scrapcount;
        this.scrapstatus = scrapstatus;
    }
}
