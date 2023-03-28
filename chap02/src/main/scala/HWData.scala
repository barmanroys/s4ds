
import scala.reflect.ClassTag
import io.Source
import java.nio.file.Paths

import breeze.linalg._
import breeze.stats._


class HWData(
              val weights:DenseVector[Double],
              val heights:DenseVector[Double],
              val reportedWeights:DenseVector[Double],
              val reportedHeights:DenseVector[Double],
              val genders:DenseVector[Char]
            ) {

  private val points = heights.length
  require(weights.length == points)
  require(reportedWeights.length == points)
  require(genders.length == points)
  require(reportedHeights.length == points)

  private lazy val rescaled_heights:DenseVector[Double] =
    (heights - mean(heights)) / stddev(heights)

  private lazy val rescaled_weights:DenseVector[Double] =
    (weights - mean(weights)) / stddev(weights)

  lazy val featureMatrix:DenseMatrix[Double] =
    DenseMatrix.horzcat(
      DenseMatrix.ones[Double](points, 1),
      rescaled_heights.toDenseMatrix.t,
      rescaled_weights.toDenseMatrix.t
    )

  lazy val target:DenseVector[Double] =
    genders.values.map { gender => if(gender == 'M') 1.0 else 0.0 }

  override def toString:String = s"HWData [ $points rows ]"

}

object HWData {
  // The paths must be with respect to the build.sbt directory path, not the scala source file directory path.
  private val DataDirectory = "data/"
  private val fileName = "rep_height_weights.csv"

  // The load() method reads the csv file and returns an object of the HWData class.
  def load():HWData =
  {
    //noinspection JavaAccessorEmptyParenCall
    // Join the directory and filepath
    val full_file_path:String=Paths.get(DataDirectory).resolve(Paths.get(fileName)).toString()
    // This gives an iterator like python, which exhausts itself after the first iteration
    val file = Source.fromFile(full_file_path)
    val lines = file.getLines.toVector
    // This function helps split the lines
    def splitter(line:String): Array[String]={line.split(',')}
    val splitLines = lines.map(splitter)

    def fromList[T:ClassTag](index:Int, converter:String => T):DenseVector[T] =
      DenseVector.tabulate(lines.size) { row => converter(splitLines(row)(index)) }

    val genders = fromList(1, elem => elem.replace("\"", "").head)
    val weights = fromList(2, elem => elem.toDouble)
    val heights = fromList(3, elem => elem.toDouble)
    val reportedWeights = fromList(4, elem => elem.toDouble)
    val reportedHeights = fromList(5, elem => elem.toDouble)

    new HWData(weights, heights, reportedWeights, reportedHeights, genders)
  }

}


