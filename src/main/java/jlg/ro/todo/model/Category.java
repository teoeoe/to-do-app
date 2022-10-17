package jlg.ro.todo.model;

public enum Category {
    STUDY("study"),
    PRODUCTIVITY("productivity"),
    LIFE("life");

    final private String label;

    Category(final String label) {
        this.label = label;
    }

    public static Category getByLabel(final String label){
        switch (label){
            case("study"):
                return Category.STUDY;
            case("productivity"):
                return Category.PRODUCTIVITY;
            default:
                return Category.LIFE;
        }
    }
}
