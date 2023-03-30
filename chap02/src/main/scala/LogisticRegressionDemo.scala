import breeze.linalg.DenseVector

object LogisticRegressionDemo {
  /** Unable to extend App here, and must manually define the main method as some SBT commands and spark clusters are incompatible with app */
  def main(args: Array[String]): Unit = {
    val data: HWData = HWData.load() // Method call without arguments works with or without the parentheses
    //noinspection SpellCheckingInspection
    val regressor = new LogisticRegression(data.featureMatrix, data.target)
    val coefficients: DenseVector[Double] = regressor.calculateOptimalCoefficients()
    val directory: String = System.getProperty("user.dir") // Gives the build.sbt directory, not the scala source.
    println(s"The current working directory is: $directory.")
    println("Optimal coefficients (on re-scaled data): ")
    println(coefficients)
  }
}


