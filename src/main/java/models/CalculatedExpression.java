package models;

import javafx.beans.property.SimpleStringProperty;

public class CalculatedExpression {
    private final SimpleStringProperty exp;
    private final SimpleStringProperty ans;

    public CalculatedExpression(String exp, String ans) {
        this.exp = new SimpleStringProperty(exp);
        this.ans = new SimpleStringProperty(ans);
    }

    public String getExp() {
        return exp.get();
    }

    public SimpleStringProperty expProperty() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp.set(exp);
    }

    public String getAns() {
        return ans.get();
    }

    public SimpleStringProperty ansProperty() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans.set(ans);
    }
}
