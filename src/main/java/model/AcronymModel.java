package model;

/**
 * Created by phil on 12/07/17.
 */
public class AcronymModel {

    private int id;
    private String acronym;
    private String text;

    public int getId() { return id; }
    public void setId(int id) { this.id = id;}

    public String getAcronym() { return acronym;}
    public void setAcronym(String acronym) { this.acronym = acronym;}

    public String getText() { return text;}
    public void setText(String text) { this.text = text;}

    public boolean isValid() {
        return acronym != null && !acronym.isEmpty() && text != null && text.length() >5;
    }
}
