package com.gsilva.easynotes.dto;

import org.springframework.hateoas.RepresentationModel;

public class NoteDto extends RepresentationModel<NoteDto> {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
