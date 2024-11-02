package me.erano.com.core.menu;

//leaf element of composite pattern for menu feature
public abstract class Button implements Component{


    @Override
    public void draw() {

    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support add() operation\"");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support remove() operation\"");
    }

    @Override
    public Component getChildren(int index) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support getChildren() operation\"");
    }
}
