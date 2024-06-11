package by.itacademy.parserservice.service.utils;

import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.FormatException;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParsingUtilsRealtBy {
    public Integer formatArea(String valueElement) {
        try {
            String valueWithoutLetters = valueElement.replaceAll("м²", "").trim();
            if (valueWithoutLetters.matches("-?\\d+(\\.\\d+)?")) {
                double value = Double.parseDouble(valueWithoutLetters);
                return Math.toIntExact(Math.round(value));
            }
            return Integer.parseInt(valueWithoutLetters);
        } catch (NumberFormatException e) {
            throw new FormatException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    public Integer formatFloor(String valueElement) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*/\\s*\\d+");
        Matcher matcher = pattern.matcher(valueElement);
        if (!matcher.find()) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
        return Integer.parseInt(matcher.group(1));
    }

    public Integer formatPrice(String price) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(price);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }
        if (stringBuilder.isEmpty()) {
            throw new FormatException(ErrorMessages.SERVER_ERROR.getMessage());
        }
        return Integer.parseInt(stringBuilder.toString());
    }
}
