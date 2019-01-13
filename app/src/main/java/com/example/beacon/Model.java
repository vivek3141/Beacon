package com.example.beacon;

public class Model {

    private boolean isSelected;

    private String animal;

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String toString() {
        return animal;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Model(String name) {
        this.animal = name;
    }


}
