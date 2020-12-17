package utils;

import java.util.HashMap;
import java.util.Map;

public class CsvHeader {
    private Map<String, Integer> indices = new HashMap<>();
    private String[] headers;
    private boolean ignoreCase;

    CsvHeader(String[] headers) {
        this(headers, false);
    }

    CsvHeader(String[] headers, boolean ignoreCase) {
        for (int i = 0; i < headers.length; i++) {
            headers[i] = headers[i].trim();
        }

        this.headers = headers;
        this.ignoreCase = ignoreCase;
        for (int i = 0; i < headers.length; i++) {
            indices.put(ignoreCase ? headers[i].toLowerCase() : headers[i], i);
        }
    }

    public Map<String, Integer> getIndices() {
        return indices;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String getHeader(int index) {
        if (index >= headers.length) {
            return null;
        }
        return headers[index];
    }

    public int getIndex(String header) {
        return indices.getOrDefault(ignoreCase ? header.toLowerCase() : header, -1);
    }
}
