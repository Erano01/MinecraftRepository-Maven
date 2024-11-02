package me.erano.com.core.menu;

//component element of composite pattern for menu feature
public interface Component {

    void draw();
    void add(Component component);
    void remove(Component component);
    Component getChildren(int index);

}
