package exercicis

import java.io.File

fun main() {
    var fichero = File.listRoots()[0]
    var opcion = 0
    var files : Array<File>
    do {
        files = fichero.listFiles()
        opcion = preguntarOpcion(fichero)
        if (opcion == 0) {
            if (fichero.parentFile != null) {
                fichero = File(fichero.parentFile.path)
            }
        } else if (opcion != 0 || opcion != -1) {
            if (files[opcion - 1].isDirectory) {
                if (files[opcion - 1].canRead()) {
                    fichero = files[opcion - 1]
                }
            }
        }
    } while (opcion != -1)
}

fun preguntarOpcion(f : File): Int {
    var num = 1
    var titulo : String
    do {
        titulo = "Llista de fitxers i directoris del directori " + f.path
        println(titulo)
        println("-".repeat(titulo.length))
        num = 1
        println("0.- Directori pare")
        for (e in f.listFiles()) {
            if (e.isDirectory) {
                println("$num.- ${e.name} <Directori>")

            } else {
                println("$num.- ${e.name} ${e.length()}")
            }
            num++
        }
        print("\nIntrodueix un número (-1 per acabar): ")
        num = readLine()?.toInt() as Int
    } while (num > f.listFiles().size || num < -1)
    return num
}