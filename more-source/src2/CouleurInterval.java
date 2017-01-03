package com.company;

import java.util.StringTokenizer;
import java.util.Vector;

public class CouleurInterval {
    private Couleur min;
    private Couleur max;

    private int minRed;
    private int maxRed;
    private int minGreen;
    private int maxGreen;
    private int minBlue;
    private int maxBlue;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] Split(String splitStr, String delimiter) {
        StringBuffer token = new StringBuffer();
        Vector tokens = new Vector();
        // split
        char[] chars = splitStr.toCharArray();
        for (int i=0; i < chars.length; i++) {
            if (delimiter.indexOf(chars[i]) != -1) {
                // we bumbed into a delimiter
                if (token.length() > 0) {
                    tokens.addElement(token.toString());
                    token.setLength(0);
                }
            } else {
                token.append(chars[i]);
            }
        }
        // don't forget the "tail"...
        if (token.length() > 0) {
            tokens.addElement(token.toString());
        }
        // convert the vector into an array
        String[] splitArray = new String[tokens.size()];
        for (int i=0; i < splitArray.length; i++) {
            splitArray[i] = (String) tokens.elementAt(i);
        }
        return splitArray;
    }
    
    public String minToString() {
        return getMinRed() + " " + getMinGreen() + " " + getMinBlue();
    }

    public String maxToString() {
        return getMaxRed() + " " + getMaxGreen() + " " + getMaxBlue();
    }

    public void minFromString(String content) {
    	try {
	        String[] color = Split(content, " ");
	        setMinRed(Integer.parseInt(color[0]));
	        setMinGreen(Integer.parseInt(color[1]));
	        setMinBlue(Integer.parseInt(color[2]));
	        
	        System.out.println(color[0]);
	        System.out.println(color[1]);
	        System.out.println(color[2]);
    	} catch (Exception e) {
    		System.out.println("min");
    	}
    }

    public void maxFromString(String content) {
    	try {
	        String[] color = Split(content, " ");
	        setMaxRed(Integer.parseInt(color[0]));
	        setMaxGreen(Integer.parseInt(color[1]));
	        setMaxBlue(Integer.parseInt(color[2]));

	        System.out.println(color[0]);
	        System.out.println(color[1]);
	        System.out.println(color[2]);
    	} catch (Exception e) {
			System.out.println("max");
		}
    }

    public void setMinRed(int minRed) {
        this.minRed = minRed;
    }

    public void setMinGreen(int minGreen) {
        this.minGreen = minGreen;
    }

    public void setMinBlue(int minBlue) {
        this.minBlue = minBlue;
    }

    public void setMaxRed(int maxRed) {
        this.maxRed = maxRed;
    }

    public void setMaxGreen(int maxGreen) {
        this.maxGreen = maxGreen;
    }

    public void setMaxBlue(int maxBlue) {
        this.maxBlue = maxBlue;
    }

    public int getMinRed() {
        return minRed;
    }

    public int getMinGreen() {
        return minGreen;
    }

    public int getMinBlue() {
        return minBlue;
    }

    public int getMaxRed() {
        return maxRed;
    }

    public int getMaxGreen() {
        return maxGreen;
    }

    public int getMaxBlue() {
        return maxBlue;
    }

    public Couleur getMin() {
        return min;
    }

    public void setMin(Couleur min) {
        this.min = min;
    }

    public Couleur getMax() {
        return max;
    }

    public void setMax(Couleur max) {
        this.max = max;
    }
}
