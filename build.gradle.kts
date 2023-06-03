import java.io.BufferedReader
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    kotlin("jvm") version "1.8.20"
    id("java")
    id("me.champeau.jmh") version "0.7.1"

}

repositories {
    mavenCentral()
}

dependencies {
    jmh("org.jetbrains.kotlinx:dataframe-excel:0.10.1")
}


val commitHash = Runtime
    .getRuntime()
    .exec("git rev-parse --short HEAD")
    .let { process ->
        process.waitFor()
        val output = process.inputStream.use {
            it.bufferedReader().use(BufferedReader::readText)
        }
        process.destroy()
        output.trim()
    }

val timestamp: String = SimpleDateFormat("yyyyMMdd'T'HHmmss").format(Date())


jmh {
    resultFormat.set("csv")
    resultsFile.set(File("perf_logs/benchmarks.json"))
    resultsFile.set(File("perf_logs/results_${timestamp}_${commitHash}.csv"))
//    zip64 = true
}


