package gui;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField
{

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate1(text))
        {
            super.replaceText(start, end, text);
        }
    }

	@Override
    public void replaceSelection(String text)
    {
        if (validate1(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate1(String text)
    {
        return text.matches("[0-9]*");
    }
}