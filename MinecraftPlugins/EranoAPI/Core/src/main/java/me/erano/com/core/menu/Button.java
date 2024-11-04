package me.erano.com.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

//leaf element of composite pattern for menu feature
public abstract class Button implements Component{

    private ItemStack icon;
    private String name;

    private Function<Player, ItemStack> creator;
    private Consumer<InventoryClickEvent> consumer;

    @Override
    public void add(int index, Component component) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support add() operation\"");
    }

    @Override
    public void remove(int index) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support remove() operation\"");
    }

    @Override
    public Component getChildren(int index) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support getChildren() operation\"");
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getIcon() {
        return this.icon;
    }
    //builder
    public Button creator(Function<Player, ItemStack> creator) {
        this.creator = creator;
        return this;
    }

    //builder
    public Button consumer(Consumer<InventoryClickEvent> consumer) {
        this.consumer = consumer;
        return this;
    }

    public Consumer<InventoryClickEvent> getEventConsumer() {
        return this.consumer;
    }

    public Function<Player, ItemStack> getIconCreator() {
        return this.creator;
    }
}
