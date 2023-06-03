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
//fun getDate(): String = Date().toString()//.format("yyyyMMdd_HHmm")

jmh {
    // Configure the JMH task here
//        include = "org\\.example\\.benchmark.*"
//        resultFormat = "csv"
//        resultsFile = file('perf_logs/jmh_results_' + getDate()+ '.csv')
    //        profilers = listOf("gc", "stack", "hs_thr")

    // Additional configurations for the task (if needed)
//    jmhTask.group = "benchmark"
//    description.set("Runs JMH benchmarks")

//val jmh by tasks.getting(JmhPluginExtension::class) {
//jmh {
//    jmhVersion = "1.34"
//    jvmArgs = listOf("-Xms2048m", "-Xmx2048m")

//    include = 'org\\..*Bench.*'
    resultFormat.set("csv")
//    resultFormat = "csv"
//    resultFormat = 'csv'
    resultsFile.set(File("perf_logs/benchmarks.json"))
    resultsFile.set(File("perf_logs/results_${timestamp}_${commitHash}.csv"))
//    resultsFile = file('perf_logs/jmh_results_' + getDate()+ '.csv')

    // for list of available profilers see http://java-performance.info/introduction-jmh-profilers/
//    profilers = Arrays.asList("")
//}
    zip64 = true

}


