
import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.numerics.{exp, log1p, sigmoid}
import breeze.optimize.{DiffFunction, minimize}

class LogisticRegression(
                          val training: DenseMatrix[Double],
                          val target: DenseVector[Double]
                        ) {
  /** This method performs the minimisation of cost over the coefficients.
   * It uses the breeze.optimize.minimize method.
   * */
  def calculateOptimalCoefficients(): DenseVector[Double] = {
    val f: DiffFunction[DenseVector[Double]] = new DiffFunction[DenseVector[Double]] {
      def calculate(parameters: DenseVector[Double]): (Double, DenseVector[Double]) = {
        costFunctionAndGradient(parameters)
      }
    }
    minimize(f, DenseVector.zeros[Double](training.cols))
  }

  private def costFunctionAndGradient(coefficients: DenseVector[Double]): (Double, DenseVector[Double]) = {
    val xBeta = training * coefficients
    val expXBeta = exp(xBeta)
    val cost = -sum((target :* xBeta) - log1p(expXBeta))
    val probabilities = sigmoid(xBeta)
    val grad = training.t * (probabilities - target)
    (cost, grad)
  }
}
