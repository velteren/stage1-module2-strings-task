package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder("[");

        for (String delimiter : delimiters) {
            sb.append(delimiter);
        }

        sb.append("]");

        StringTokenizer st = new StringTokenizer(source, sb.toString());

        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();

            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }

        return tokens;
    }
}
