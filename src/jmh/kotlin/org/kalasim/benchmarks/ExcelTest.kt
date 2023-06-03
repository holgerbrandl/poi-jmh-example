package org.kalasim.benchmarks

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.print
import org.jetbrains.kotlinx.dataframe.io.readExcel
import org.openjdk.jmh.annotations.*
import java.io.File
import java.util.concurrent.TimeUnit


@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
open class ExcelTest {

    @Benchmark
    fun doSomething() {
        DataFrame.readExcel(File("iris.xlsx")).print()
    }

}

fun main() {
    DataFrame.readExcel(File("iris.xlsx")).print()
}