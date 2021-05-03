package jescript.reader;

public class JEScriptReader {

    // Data type -> string
    private String scriptData;
    private int scriptReadCurrentPos;
    private int scriptDataLength;

    public JEScriptReader(String scriptData) {
        this.scriptData = scriptData;
        this.scriptReadCurrentPos = 0;
        this.scriptDataLength = scriptData.length();
    }

    public String readNextChar() {
        if (scriptReadCurrentPos >= scriptDataLength)
            return String.valueOf(-1);
        return String.valueOf(scriptData.charAt(scriptReadCurrentPos++));
    }

    public void retractBack(int retractNum) {
        if (scriptReadCurrentPos - retractNum < 0)
            scriptReadCurrentPos = 0;
        else
            scriptReadCurrentPos -= retractNum;
    }

}
