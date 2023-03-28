
object LogisticRegressionDemo {

  def main(args: Array[String]): Unit = {
    val data = HWData.load() // Method call without arguments can work without the parentheses
    //noinspection SpellCheckingInspection
    val regressor = new LogisticRegression(data.featureMatrix, data.target)
    val coefficients = regressor.optimalCoefficient
    println("Optimal coefficients (on re-scaled data): ")
    println(coefficients)
  }
}

