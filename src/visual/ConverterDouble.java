/*
 */

package visual;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * jlbavaresco@gmail.com
 */
public class ConverterDouble extends Converter{

    @Override
    public Object convertForward(Object value) {
        Locale.setDefault(new Locale("en", "US"));
        NumberFormat df = new DecimalFormat("0.00");
        Double d = (Double)value;
        return df.format(d);
    }

    @Override
    public Object convertReverse(Object value) {
        String str = (String) value;
        return Double.parseDouble(str);
    }

}
