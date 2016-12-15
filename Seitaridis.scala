import java.io.File

object Seitaridis {

  def main(args: Array[String]): Unit =
  {
    println("Andreas")


    //creates a list with the names of all the files in the directory
    val currentDir = System.getProperty("user.dir") // gets the working directory
    val listoffilesPos = getListOfFiles(currentDir + "/data/train/pos")

    // open stopwords file
    val stopWordsFile = "file://" + currentDir + "/stopWords.txt"
    // create a list of stopwords
    val stopWords = sc.textFile(stopWordsFile).flatMap(x=>x.split(" ")).collect()

    //prints the all the filenames
    listoffilesPos.foreach(w => {println(w)})

    val stemmer = new Stemmer

    //counts the all the stems from positive reviews (~2.8 millions)
    val what = listoffilesPos.flatMap(filename => {
      stemmer.stemLine(scala.io.Source.fromFile(filename).mkString)
    }).map(word => 1).sum


    // removes the stopwords, also applies stemming
    val whatNonStop = listoffilesPos.flatMap(filename =>
    {
        stemmer.stemLine(scala.io.Source.fromFile(filename)
        .mkString
        .split(" ")
        .filter(word => !stopWords.contains(word)).mkString(" "))
    }).map(word => 1).sum

    println("total number of words : "+ what)
    println("total number of words (No Stopwords) : " + whatNonStop)

    println(what.getClass)

    println(stemmer.stemLine("worked dfgdg  is  a bad  tin0foiled'opened porter334parole ponies perhaps"
      .split(" ").filter(word => !stopWords.contains(word)).mkString(" ")))


  }

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {

      List[File]()
    }
  }


}