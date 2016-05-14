package gui;

import javafx.scene.control.TextField;

public class DoubleTextField extends TextField
{

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate1(text)||validate2(text))
        {
            super.replaceText(start, end, text);
        }
    }

	@Override
    public void replaceSelection(String text)
    {
        if (validate1(text)||validate2(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate1(String text)
    {
        return text.matches("[0-9]*");
    }
    private boolean validate2(String text)
    {
        return text.equals(".");
    }
}