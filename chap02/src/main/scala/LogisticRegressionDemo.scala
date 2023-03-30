
object LogisticRegressionDemo {
  /** Unable to extend App here, and must manually define the main method as some SBT commands and spark clusters are incompatible with app */
  def main(args: Array[String]): Unit = {
    val data = HWData.load() // Method call without arguments can work without the parentheses
    //noinspection SpellCheckingInspection
    val regressor = new LogisticRegression(data.featureMatrix, data.target)
    val coefficients = regressor.calculateOptimalCoefficients()
    println("Optimal coefficients (on re-scaled data): ")
    println(coefficients)
  }
}


