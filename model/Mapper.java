package model;

public class Mapper {

    String subLine = "_";

    public Mapper(String subLine) {
        this.subLine = subLine;
    }

    public Mapper() {
    }

    public String map(Note note) {
        return String.format("%s%s%s%s%s%s%s", note.getId(), subLine, note.getTitle(), subLine, note.getFieldString(),
                subLine, note.getDataString());
    }

    public Note map(String line) {
        String[] lines = line.split(subLine);
        return new Note(lines[0], lines[1], lines[2], lines[3]);
    }
}
