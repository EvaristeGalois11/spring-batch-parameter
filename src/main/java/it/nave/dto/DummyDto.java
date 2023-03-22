package it.nave.dto;

import java.time.Instant;

public class DummyDto {
    private long id;
    private String name;
    private Instant timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DummyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
