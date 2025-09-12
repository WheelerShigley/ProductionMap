package register;

public abstract class Identified {
    private final String namespace, name;

    public Identified(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    private boolean validateIdentifier(String identifier) {
        if(identifier.length() < 2) {
            return false;
        }
        return true;
    }

    public String getIdentifierAsString() {
        return this.namespace+':'+this.name;
    }

    @Override
    public String toString() {
        char[] reformattedIdentifier = this.name.replace('_',' ').toCharArray();
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
