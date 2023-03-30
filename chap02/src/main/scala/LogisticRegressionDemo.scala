
object LogisticRegressionDemo extends App {
  private val data = HWData.load() // Method call without arguments can work without the parentheses
  //noinspection SpellCheckingInspection
  private val regressor = new LogisticRegression(data.featureMatrix, data.target)
  private val coefficients = regressor.calculateOptimalCoefficients()
  println("Optimal coefficients (on re-scaled data): ")
  println(coefficients)
}


