package org.wfw.math;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

/**
 * @author Jiawei Mao
 * @version 0.0.1
 * @since 23 May 2023, 11:16
 */
public class PolynomialDemo
{
    public static void main(String[] args)
    {
        // 收集数据
        final WeightedObservedPoints obs = new WeightedObservedPoints();
        obs.add(-1.00, 2.021170021833143);
        obs.add(-0.99, 2.221135431136975);
        obs.add(-0.98, 2.09985277659314);
        obs.add(-0.97, 2.0211192647627025);
        // ... Lots of lines omitted ...
        obs.add(0.99, -2.4345814727089854);

        // 实例化一个三级多项式 fitter
        final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(3);

        // 查询拟合参数（多项式函数的系数）
        final double[] coeff = fitter.fit(obs.toList());
    }
}
