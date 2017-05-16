package nicolas.ex1.CustomFonts;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import nicolas.ex1.App;

// Classe da fonte open sans bold

public class OpenSansBoldFont extends TextView{

    // Tipos de construtores

    public OpenSansBoldFont(Context context) {
        this(context, null);
    }

    public OpenSansBoldFont(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OpenSansBoldFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.openSansBoldType);
    }
}
