package com.benchmark;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        System.out.println("==== JSON vs Protobuf Benchmark ====\n");

        JsonBenchmark.run();
        System.out.println("----------------------------------");
        ProtobufBenchmark.run();
    }
}
