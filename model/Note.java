package model;

import java.util.Date;

public class Note {
    private String id = "";
    private String title="";

    private String fieldString = "";
    private String dataString = "";

    public Note(String title, String fieldString, String dataString) {
        this.fieldString = fieldString;
        if (dataString=="") {
            Date date = new Date();
            this.dataString = "" + date;
        }
        else {
            this.dataString = dataString;
        }
        this.title = title;
    }

    public Note(String id, String title, String fieldString, String dataString) {
        this(title, fieldString, dataString);
        this.id = id;
    }

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

    public String getFieldString() {
        return fieldString;
    }

    public String getDataString() {
        return dataString;
    }

    public void setFieldString(String fieldString) {
        this.fieldString = fieldString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    @Override
    public String toString() {
        return String.format("Номер: %s,\nЗаголовок: %s,\nЗаметка: %s,\nДата создания: %s\n", id, title, fieldString, dataString);
    }
}
