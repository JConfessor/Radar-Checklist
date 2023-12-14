package br.imd.ufrn.checklist;

public class ChecklistItem {

    private String itemName;
    private boolean conforme;
    private boolean naoConforme;

    public ChecklistItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isConforme() {
        return conforme;
    }

    public void setConforme(boolean conforme) {
        this.conforme = conforme;
    }

    public boolean isNaoConforme() {
        return naoConforme;
    }

    public void setNaoConforme(boolean naoConforme) {
        this.naoConforme = naoConforme;
    }
}
