package exercicis


import java.io.File
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    var fichero = File.listRoots()[0]
    var opcion = 0
    var files : Array<File>
    do {
        files = fichero.listFiles()
        opcion = preguntarOpcionVoluntari(fichero)
        if (opcion == 0) {
            if (fichero.parentFile != null) {
                fichero = File(fichero.parentFile.path)
            }
        } else if (opcion != 0 && opcion != -1) {
            if (files.sorted()[opcion - 1].isDirectory) {
                if (files.sorted()[opcion - 1].canRead()) {
                    fichero = files.sorted()[opcion - 1]
                }
            }
        }
    } while (opcion != -1)
}

fun preguntarOpcionVoluntari(f : File): Int {
    var num = 1
    var titulo : String
    do {
        titulo = "Llista de fitxers i directoris del directori " + f.path
        println(titulo)
        println("-".repeat(titulo.length))
        num = 1
        println("0.- Directori pare")
        for (e in f.listFiles().sorted()) {
            if (e.isDirectory) {
                print("$num.- d")

            } else {
                print("$num.- ")
            }
            if (e.canRead())
                print("r")
            else
                print("-")
            if (e.canWrite())
                print("w")
            else
                print("-")
            if (e.canExecute())
                print("x")
            else
                print("-")
            var data = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(e.lastModified());
            println(" ${e.length()}  $data  ${e.name}")
            num++
        }
        print("\nIntrodueix un número (-1 per acabar): ")
        num = readLine()?.toInt() as Int
    } while (num > f.listFiles().size || num < -1)
    return num
}