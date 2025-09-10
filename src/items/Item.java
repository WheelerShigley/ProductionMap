package items;

public record Item(String namespace, String identifier) {
    public Item(String namespace, String identifier) {
        this.namespace = namespace;
        this.identifier = identifier;
        ItemsRegistry.registerItem(this, false);
    }

    public String getIdentifierAsString() {
        return this.namespace+':'+this.identifier;
    }

    @Override
    public String toString() {
        char[] reformattedIdentifier = this.identifier.replace('_',' ').toCharArray();
        StringBuilder titleMaker = new StringBuilder();

        boolean lastCharWasWhiteSpace = true, currentCharIsWhiteSpace = false;
        for(char character : reformattedIdentifier) {
            //skip consecuative white-space
            currentCharIsWhiteSpace = Character.isWhitespace(character);
            if(currentCharIsWhiteSpace && lastCharWasWhiteSpace) {
                continue;
            }

            //capitalize new words
            if(lastCharWasWhiteSpace) {
                titleMaker.append( Character.toUpperCase(character) );
                lastCharWasWhiteSpace = currentCharIsWhiteSpace;
                continue;
            }

            //append current character
            titleMaker.append(character);

            //set last-char
            lastCharWasWhiteSpace = currentCharIsWhiteSpace;
        }
        return titleMaker.toString().replace(' ','-');
    }
}
