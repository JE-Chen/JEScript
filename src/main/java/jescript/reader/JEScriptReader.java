package jescript.reader;

public class JEScriptReader {

    private String scriptData;
    private int scriptReadCurrentPos;
    private int scriptDataLength;

    public JEScriptReader(String scriptData) {
        this.scriptData = scriptData;
        this.scriptReadCurrentPos = 0;
        this.scriptDataLength = scriptData.length();
    }

    public int nextChar(){
        if(scriptReadCurrentPos >= scriptDataLength)
            return -1;
        return scriptData.charAt(scriptReadCurrentPos++);
    }

    public void retract(int retractNum){
        if(scriptReadCurrentPos - retractNum < 0)
            scriptReadCurrentPos = 0;
        else
            scriptReadCurrentPos -= retractNum;
    }

    public void retract(){
            scriptReadCurrentPos -= 1;
    }

}
