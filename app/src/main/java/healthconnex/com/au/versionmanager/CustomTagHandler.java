package healthconnex.com.au.versionmanager;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

/**
 * Created by frincon on 12/02/2015.
 */
public class CustomTagHandler implements Html.TagHandler {

    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        // you may add more tag handler which are not supported by android here
        if("li".equals(tag)){
            if(opening){
                output.append(" \u2022 ");
            }else{
                output.append("\n");
            }
        }
    }
}