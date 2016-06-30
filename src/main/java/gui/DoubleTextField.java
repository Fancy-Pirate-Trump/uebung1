package gui;

import javafx.scene.control.TextField;

public class DoubleTextField extends TextField
{

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (isNumber(text)||isDot(text))
        {
            super.replaceText(start, end, text);
        }
    }

	@Override
    public void replaceSelection(String text)
    {
        if (isNumber(text)||isDot(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean isNumber(String text)
    {
        return text.matches("[0-9]*");
    }
    private boolean isDot(String text)
    {
        return text.equals(".");
    }
}