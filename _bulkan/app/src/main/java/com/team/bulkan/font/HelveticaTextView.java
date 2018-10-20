package com.team.bulkan.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.team.bulkan.R;

public class HelveticaTextView extends AppCompatTextView {

    public HelveticaTextView(Context context) {
        super(context);
        if (isInEditMode()) return;
        parseAttributes(null);
    }

    public HelveticaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        parseAttributes(attrs);
    }

    public HelveticaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) return;
        parseAttributes(attrs);
    }

    private void parseAttributes(AttributeSet attrs) {
        int typeface;
        if (attrs == null) { //Not created from xml
            typeface = Helvetica.HELVETICA_NORMAL;
        } else {
            TypedArray values = getContext().obtainStyledAttributes(attrs, R.styleable.HelveticaTextView);
            typeface = values.getInt(R.styleable.HelveticaTextView_htypeface, Helvetica.HELVETICA_NORMAL);
            values.recycle();
        }
        setTypeface(getHelvetica(typeface));
    }

    private Typeface getHelvetica(int typeface) {
        return getHelvetica(getContext(), typeface);
    }

    public static Typeface getHelvetica(Context context, int typeface) {
        switch (typeface) {
            default:
            case Helvetica.HELVETICA_NORMAL:
                if (Helvetica.sHelveticaNormal == null) {
                    Helvetica.sHelveticaNormal = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue.ttc");
                }
                return Helvetica.sHelveticaNormal;
        }
    }


    public static class Helvetica {
        public static final int HELVETICA_NORMAL = 0;

        private static Typeface sHelveticaNormal;
    }
}
