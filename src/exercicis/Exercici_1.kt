package exercicis

import java.io.File
import java.lang.NumberFormatException

fun main() {
    var fichero = File.listRoots()[0]
    var opcion: Int
    var files : Array<File>
    do {
        files = fichero.listFiles()
        opcion = preguntarOpcion(fichero)
        if (opcion == 0) {
            if (fichero.parentFile != null) {
                fichero = File(fichero.parentFile.path)
            } else {
                println("\nEl directori seleccionat no te pare")
            }
        } else if (opcion != 0 && opcion != -1) {
            if (files.sorted()[opcion - 1].isDirectory) {
                if (files.sorted()[opcion - 1].canRead()) {
                    fichero = files.sorted()[opcion - 1]
                } else {
                    println("\nNo tens permisos de lectura per al directori seleccionat")
                }
            } else {
                println("\nHas seleccionat un fitxer")
            }
        }
    } while (opcion != -1)
}

fun preguntarOpcion(f : File): Int {
    var num: Int
    var titulo : String
    var opcionEscogida : String
    do {
        titulo = "Llista de fitxers i directoris del directori " + f.path
        println(titulo)
        println("-".repeat(titulo.length))
        num = 1
        println("0.- Directori pare")
        for (e in f.listFiles().sorted()) {
            if (e.isDirectory) {
                println("$num.- ${e.name} <Directori>")

            } else {
                println("$num.- ${e.name} ${e.length()}")
            }
            num++
        }
        print("\nIntrodueix un número (-1 per acabar): ")
        opcionEscogida = readLine()!!
        try {
            num = opcionEscogida.toInt()
            if (num > f.listFiles().size || num < -1) {
                println("\nTe que ser un numero del -1 al ${f.listFiles().size}")
            }
        } catch (ex: NumberFormatException) {
            println("\nNo has introduit un numero")
        }
    } while (!(num <= f.listFiles().size && num >= -1))
    return num
}

