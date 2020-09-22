package org.selyuk.storelite;

public enum FileFormats {
    JSON (".json"),
    XML (".xml"),
    TXT (".txt"),
    UNSUPPORTED ("");

    private String extension;

    FileFormats(String s) {
        this.extension = s;
    }

    public String getExtension() {
        return this.extension;
    }
}
