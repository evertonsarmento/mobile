package nicolas.ex1.CustomFonts;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import nicolas.ex1.App;

// Classe da fonte open sans semi bold

public class OpenSansSBoldFont extends TextView{

    // Tipos de construtores

    public OpenSansSBoldFont(Context context) {
        this(context, null);
    }

    public OpenSansSBoldFont(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OpenSansSBoldFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.openSansSBoldType);
    }
}
