## JSON vs Protobuf Benchmark

This project benchmarks JSON (Jackson) vs Protobuf for large payload
deserialization in Java.

### Why?
JSON parsing incurs a hidden CPU cost due to text processing,
branch mispredictions, and runtime validation.

### Results
- Protobuf is ~6–8x faster to deserialize
- Payload size reduced by ~70%
- Strong schema guarantees at compile time

### Verdict
JSON is great for public APIs.
Protobuf is built for high-throughput systems.
