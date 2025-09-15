package items;

import register.Identified;

public class Item extends Identified {
    public Item(String namespace, String name) {
        super(namespace, name);
        Items.register(this);
    }

    @Override
    public String toString() {
        StringBuilder nameBuilder = new StringBuilder();

        boolean wasLastCharacterWhiteSpace = true, isCurrentCharacterWhiteSpace;
        for(char currentCharacter : this.getName().toCharArray() ) {
            isCurrentCharacterWhiteSpace = Character.isWhitespace(currentCharacter);
            if(wasLastCharacterWhiteSpace && isCurrentCharacterWhiteSpace) {
                continue;
            }
            if(wasLastCharacterWhiteSpace) {
                nameBuilder.append( Character.toUpperCase(currentCharacter) );
            } else {
                nameBuilder.append( Character.toLowerCase(currentCharacter) );
            }

            wasLastCharacterWhiteSpace = isCurrentCharacterWhiteSpace;
        }

        return nameBuilder.toString();
    }
}
