package cn.sunzhichao.mall.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 对BigDecimal封装，实现double类型和BigDecimal的简易转换
 */
public class BigDecimalUtil {

    private BigDecimalUtil() {
    }

    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        //除法，要考虑除不尽的情况，四舍五入，保留两位小数
        return b1.divide(b2, 2, RoundingMode.HALF_DOWN);
    }
}
