package nicolas.ex1.CustomFonts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import nicolas.ex1.App;

// Classe da fonte canaro

public class CanaroFont extends TextView {

    // Tipos de construtores

    public CanaroFont(Context context) {
        this(context, null);
    }

    public CanaroFont(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanaroFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.canaroType);
    }
}
