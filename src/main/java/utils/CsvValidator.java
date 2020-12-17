package utils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;

public class CsvValidator {
    private int headerSize = -1;
    private int lineSize = -1;
    private List<String> headersList;
    private Map<String, Function<String, Boolean>> validatorsByName = new HashMap<>();
    private Map<Integer, Function<String, Boolean>> validatorsByIndex = new HashMap<>();
    private boolean ignoreCaseInHeader;

    public CsvValidator withHeaderSize(int headerSize) {
        this.headerSize = headerSize;
        return this;
    }

    public CsvValidator withLineSize(int lineSize) {
        this.lineSize = lineSize;
        return this;
    }

    public CsvValidator withHeaders(String... headers) {
        this.headersList = Arrays.asList(headers);
        return this;
    }

    public CsvValidator withIgnoreCaseInHeader(boolean ignoreCaseInHeader) {
        this.ignoreCaseInHeader = ignoreCaseInHeader;
        return this;
    }

    public CsvValidator withValidator(String name, Function<String, Boolean> validator) {
        validatorsByName.put(name, validator);
        return this;
    }

    public CsvValidator withValidator(int index, Function<String, Boolean> validator) {
        validatorsByIndex.put(index, validator);
        return this;
    }

    public void validate(String filePath, boolean headerExist) throws CsvFormatException {
        try (CsvReader csvReader = new CsvReader(filePath, ignoreCaseInHeader)) {
            if (headerExist) {
                validateHeaders(csvReader.readHeaders().getHeader());
            }

            validateRows(csvReader);
        } catch (Exception ex) {
            throw new CsvFormatException(ex);
        }
    }

    private void validateRows(CsvReader csvReader) throws CsvFormatException {
        for (CsvLine csvLine : csvReader) {
            if (lineSize != -1 && lineSize != csvLine.values.size())
                throw new CsvFormatException(MessageFormat.format("Unexpected entry fields count found. Line {0}, expected count {1}, actual count {2}",
                        csvLine.toString(), lineSize, csvLine.values.size()));

            for (Map.Entry<String, Function<String, Boolean>> entry : validatorsByName.entrySet()) {
                if (!entry.getValue().apply(csvLine.getString(entry.getKey())))
                    throw new CsvFormatException(MessageFormat.format("Invalid data in column {0}. Line {1}, field Value {2}",
                            entry.getKey(), csvLine.toString(), csvLine.getString(entry.getKey())));
            }

            for (Map.Entry<Integer, Function<String, Boolean>> entry : validatorsByIndex.entrySet()) {
                if (!entry.getValue().apply(csvLine.getString(entry.getKey())))
                    throw new CsvFormatException(MessageFormat.format("Invalid data in index {0}. Line {1}, field Value {2}",
                            entry.getKey(), csvLine.toString(), csvLine.getString(entry.getKey())));
            }
        }
    }

    private void validateHeaders(String[] headers) throws CsvFormatException {
        if (headerSize != -1 && headerSize != headers.length)
            throw new CsvFormatException(MessageFormat.format("Unexpected header fields count found. Expected {0}, actual {1}", headerSize, headers.length));

        int headerListSize = headersList.size();
        for (int i = 0; i < headerListSize; i++) {
            if (!headers[i].equals(headersList.get(i)) && !(ignoreCaseInHeader && headers[i].equalsIgnoreCase(headersList.get(i))))
                throw new CsvFormatException(MessageFormat.format("Unexpected header field found. index {0}, expected {1}, actual {2}",
                        i, headersList.get(i), headers[i]));
        }
    }

    public static Function<String, Boolean> STRING_VALIDATOR = Objects::nonNull;

    public static Function<String, Boolean> INTEGER_VALIDATOR = s -> {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    };

    public static Function<String, Boolean> BIG_DECIMAL_VALIDATOR = s -> {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    };

    public static Function<String, Boolean> LONG_VALIDATOR = s -> {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    };

    public static Function<String, Boolean> DOUBLE_VALIDATOR = s -> {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    };

    public static Function<String, Boolean> CHAR_VALIDATOR = s -> s.length() <= 1;

    public static Function<String, Boolean> BOOLEAN_VALIDATOR = s -> s.equalsIgnoreCase("TRUE") || s.equalsIgnoreCase("FALSE");
}
