# 曲线拟合

2023-05-23
****
## 简介

`fitting` 包用于单变量实函数的曲线拟合。当单变量实函数 $y=f(x)$ 包含未知参数 $p_0, p_1, ..., p_{n-1}$，曲线拟合可用于查找这些参数。

对已有数据点 $(x_0, y_0), (x_1, y_1),...,(x_{k-1}, y_{k-1})$ ，曲线拟合通过调整参数，使拟合点与观测点距离最近。参数的调整通过最小化目标函数 $\sum(y_i-f(x_i))^2$ 来实现。这实际上是一个最小二乘问题。

commons-math 提供的所有拟合工具（fitter）的工作原理相同：

- 使用对应类的 `create` 工厂方法创建 fitter 实例；
- 使用观测数据的集合作为参数调用 `fit`，返回包含最适合给定观测数据的参数数组。

对观测数据，可以先逐个添加到 `WeightedObservedPoints` ，然后调用 `toList()` 转换为 `WeightedObservedPoint` list。观测数据可以包含权重值，例如该数据为噪音的可能性，所有数据点的默认权重为 1.0。

有些 fitter 在拟合前需要用户调用 `withStartPoint` 方法提供参数的初始值。如果没有提供初始值，它们会调用内部类 `ParameterGuesser` ，根据提供的数据猜测一个合适的初始值。

## 实现的函数

特定函数的拟合通过以下类实现：

- `PolynomialFitter` 实现多项式拟合
- `HarmonicFitter` 实现调和拟合，其内部类 `ParameterGuesser` 可用于猜测拟合的初始参数。
- `GaussianFitter` 实现高斯拟合，其内部类 `ParameterGuesser` 可用于猜测拟合的初始参数。

下例演示如何进行多项式拟合：

```java
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
```

## 通用实现

`AbstractCurveFitter` 类为实现其它曲线拟合提供了基本功能。对自定义函数，需要实现 `ParametricUnivariateFunction` 接口。

## 参考

- https://commons.apache.org/proper/commons-math/userguide/fitting.html