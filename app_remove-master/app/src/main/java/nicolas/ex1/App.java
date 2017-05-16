package nicolas.ex1;

import android.app.Application;
import android.graphics.Typeface;

// Classe que gerencia as fontes
// Para usa-la, não esqueça de colocar no seu Mainfest: android:name=".App"

public class App extends Application {
    private static final String CANARO_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroType;

    private static final String OPENSANS_BOLD_PATH = "fonts/OpenSans-Bold.ttf";
    public static Typeface openSansBoldType;

    private static final String OPENSANS_LIGHT_PATH = "fonts/OpenSans-Light.ttf";
    public static Typeface openSansLightType;

    private static final String OPENSANS_REGULAR_PATH = "fonts/OpenSans-Regular.ttf";
    public static Typeface openSansRegType;

    private static final String OPENSANS_SBOLD_PATH = "fonts/OpenSans-Semibold.ttf";
    public static Typeface openSansSBoldType;

    @Override
    public void onCreate() {
        super.onCreate();
        setTypefaces();
    }

    // referencia a fonte com o typeface
    private void setTypefaces() {
        canaroType = Typeface.createFromAsset(getAssets(), CANARO_PATH);
        openSansBoldType = Typeface.createFromAsset(getAssets(), OPENSANS_BOLD_PATH);
        openSansLightType = Typeface.createFromAsset(getAssets(), OPENSANS_LIGHT_PATH);
        openSansRegType = Typeface.createFromAsset(getAssets(), OPENSANS_REGULAR_PATH);
        openSansSBoldType = Typeface.createFromAsset(getAssets(), OPENSANS_SBOLD_PATH);
    }


}
