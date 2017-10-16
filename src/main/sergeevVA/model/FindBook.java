package main.sergeevVA.model;

public class FindBook {
    private String author;
    private String title;
    private int isbn;
    private int yearAfter;
    private int yearBefore;
    private int readAlready;

    public FindBook() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getYearAfter() {
        return yearAfter;
    }

    public void setYearAfter(int yearAfter) {
        this.yearAfter = yearAfter;
    }

    public int getYearBefore() {
        return yearBefore;
    }

    public void setYearBefore(int yearBefore) {
        this.yearBefore = yearBefore;
    }

    public int getReadAlready() {
        return readAlready;
    }

    public void setReadAlready(int readAlready) {
        this.readAlready = readAlready;
    }
}
