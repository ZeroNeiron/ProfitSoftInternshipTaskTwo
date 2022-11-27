package org.example.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.example.service.ChangeNameService;
import org.example.service.Reader;

public class ChangeNameServiceImpl implements ChangeNameService {

    private static final Pattern NAME_PATTERN = Pattern.compile("(?<!sur)name\\s*=\\s*?\"(?<name>\\S*)\"",
            Pattern.MULTILINE | Pattern.COMMENTS);

    private static final Pattern SURNAME_PATTERN = Pattern.compile("surname\\s*?=\\s*?\"(?<surname>\\S*)\"",
            Pattern.MULTILINE | Pattern.COMMENTS);
    private static final String NAME_FORMAT = "name=\"%s %s\"";


    @Override
    public String changeNameAttribute(String line) {
        String changedLine = line;

        Matcher nameMatcher = NAME_PATTERN.matcher(line);
        Matcher surnameMatcher = SURNAME_PATTERN.matcher(line);

        if (nameMatcher.find()) {
            String surname = "";

            if (surnameMatcher.find()) {
                surname = surnameMatcher.group("surname");
                changedLine = changedLine.replaceAll(SURNAME_PATTERN.toString(), "");
            }
            String name = nameMatcher.group("name");
            changedLine = changedLine.replaceAll(NAME_PATTERN.toString(),
                    String.format(NAME_FORMAT, name, surname));
        }

        return changedLine;
    }
}
