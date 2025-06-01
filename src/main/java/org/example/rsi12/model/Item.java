package org.example.rsi12.model;

import java.time.LocalDateTime;

public class Item {

    private Long id;
    private String author;
    private LocalDateTime created;

    public Item(){
        this("Przykladowy autor");
    }

    public Item(String author){
        this.author = author;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
