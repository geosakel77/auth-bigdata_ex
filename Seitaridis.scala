import java.io.File

object Seitaridis {

  def main(args: Array[String]): Unit =
  {
    println("Andreas")


    //creates a list with the names of all the files in the directory
    val listoffilesPos = getListOfFiles("/home/user/Desktop/project/data/train/pos")

    //prints the all the filenames
    listoffilesPos.foreach(w => {println(w)})

    val stemmer = new Stemmer

    //counts the all the stems from positive reviews (~2.8 millions)
    val what = listoffilesPos.flatMap(filename => {
      stemmer.stemLine(scala.io.Source.fromFile(filename).mkString)
    }).map(word => 1).sum

    println("total number of words : "+ what)

    println(what.getClass)

    println(stemmer.stemLine("worked dfgdg  is  a bad  tin0foiled'opened porter334parole ponies"))


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