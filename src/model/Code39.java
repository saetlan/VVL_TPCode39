package model;

/**
 * Created by arnoul on 03/11/16.
 */
public class Code39 {
    /**
     * Returns the input given in parameters with a star added at the beginning and the end.
     * @param input the string to be modified.
     * @return the string formed by concatenated with a star at the beginning and at the end.
     */
    public static String addStars(String input) {
        return "*"+input+"*";
    }

    /**
     * Returns the input given in parameters without any character not listed in the Code39.
     * @param input the string to be modified.
     * @return the string after the suppression of unauthorized characters
     */
    public static String deleteUnauthorizedChars(String input) {
        return input.replaceAll("[^a-zA-Z0-9- $%./+]*",""); //\\w matches
    }

    /**
     * Returns a char corresponding to the Modulo43.
     * It's computed by adding the value of every char of the given input based on the Code39 table.
     * We then keep the rest of the division by 43 and convert it into a char based on the Code39 conversion table.
     * @param input the string from which we want to get the Modulo43.
     * @return the Modulo 43 depending on the parameter input.
     */
    public static char getModulo43(String input) {
        int res = 0;
        /*
        * The method I used below to get the modulo number is more efficient than the one described here :
        * The other consists in listing our Code39 in a string and then comparing each character from our input
        * to every char in the alphabet.
        * Once you find the character matching, you add the index of the char in the alphabet to your counter and finally apply your modulo.
        */

        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(Character.isDigit(c)) { 
                res+=(c-'0');
            } else if(Character.isLetter(c)) {
                res+=(c-'A'+10);
            } else {
                if(c=='-') {
                    res+=36;
                } else if(c=='.') {
                    res+=37;
                } else if(c==' ') {
                    res+=38;
                } else if(c=='$') {
                    res+=39;
                } else if(c=='/') {
                    res+=40;
                } else if(c=='+') {
                    res+=41;
                } else if(c=='%') {
                    res+=42;
                }
            }
        }
        res%=43;
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
        return alphabet.charAt(res);
    }

    /**
     * Returns the concatenation of the parameter input with its Modulo43.
     * @param input the string to be concatenated with its Modulo43.
     * @return the string input concatenated with the Modulo43.
     */
    public static String addModulo43ToCode39(String input) {
        return input.substring(0, input.length()-1) + getModulo43(input) + "*";
    }

    /**
     * Returns the input given in parameters modified to respect the Code39.
     * @param input the string to be encoded.
     * @return the string input after modification to respect the Code39.
     */
    public static String encoder(String input){
        String res;
        res = deleteUnauthorizedChars(input);
        res = res.toUpperCase();
        res = addStars(res);
        return res;
    }

    /**
     * Returns the input given in parameters modified to respect the Code39 and Modulo43 extension.
     * @param input the string to be encoded
     * @return the string input after modification to respect the Code39 and Modulo 43 extension.
     */
    public static String encoderExtended(String input){
        return addModulo43ToCode39(encoder(input));
    }
}
