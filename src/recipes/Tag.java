package recipes;

import items.Item;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    public final String name;
    public final List<Item> items;

    public Tag(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }
    public Tag(String name, Item item) {
        this.name = name;

        List<Item> singleEntry = new ArrayList<>();
        singleEntry.add(item);

        this.items = singleEntry;
    }
    public Tag(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public boolean equals(Tag other) {
        for(Item item : items) {
            if( !other.items.contains(item) ) {
                return false;
            }
        }
        for(Item item: other.items) {
            if( !items.contains(item) ) {
                return false;
            }
        }
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        StringBuilder TagMessageBuilder = new StringBuilder();
        TagMessageBuilder.append(name);

        TagMessageBuilder.append(" (");
        for(int index = 0; index < items.size(); index++) {
            TagMessageBuilder.append( items.get(index).getName() );
            if(index < items.size()-1 ) {
                TagMessageBuilder.append(", ");
            }
        }
        TagMessageBuilder.append(")");

        return TagMessageBuilder.toString();
    }
}
