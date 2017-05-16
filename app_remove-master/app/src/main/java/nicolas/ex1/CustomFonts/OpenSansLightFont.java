package nicolas.ex1.CustomFonts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import nicolas.ex1.App;

// Classe da fonte open sans light

public class OpenSansLightFont extends TextView{

    // Tipos de construtores

    public OpenSansLightFont(Context context) {
        this(context, null);
    }

    public OpenSansLightFont(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OpenSansLightFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.openSansLightType);
    }
}
