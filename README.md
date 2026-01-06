## JSON vs Protobuf Benchmark (Java)

This repository benchmarks **JSON (Jackson)** vs **Protocol Buffers** for large payload processing in Java.

The goal is not to prove that *“Protobuf is always faster”*, but to measure **total system cost** under realistic conditions:

* Serialization
* Deserialization
* Payload size
* Schema guarantees

---

### 🧪 Benchmark Setup

**Dataset**

* 1,000,000 records
* Flat domain model (`id`, `name`, `email`, `age`, `active`)
* Realistic microservice-style payload

**Environment**

* Java 17
* Jackson 2.x
* Protobuf 3.x
* JVM heap: `-Xmx4g`
* Warm filesystem cache
* Separate JVM runs per format

---

### 📊 Results (Serialize + Deserialize)

| Metric             | JSON (Jackson) | Protobuf     |
| ------------------ | -------------- | ------------ |
| Deserialize Time   | ~847 ms        | ~598 ms      |
| Serialize Time     | ~435 ms        | ~383 ms      |
| **Total Time**     | **~1282 ms**   | **~981 ms**  |
| Payload Size       | ~86 MB         | ~43 MB       |
| Schema Enforcement | Runtime        | Compile-time |

---

### 🔍 Key Observations

* Jackson is **highly optimized** and competitive in isolated deserialization benchmarks.
* Protobuf consistently reduces **total CPU cost**, not just parsing time.
* Binary encoding cuts payload size by ~50%, reducing:

  * Network bandwidth
  * Memory pressure
  * Compression overhead
* Protobuf enforces schema correctness at **compile time**, eliminating runtime validation.

---

### 🧠 Conclusion

JSON is an excellent choice for **public APIs** and debuggability.

For **high-throughput microservices**, Protobuf reduces:

* CPU usage
* Memory allocation
* Network cost
* Runtime validation overhead

The advantage is **system-level**, not just parsing speed.

---

### ▶️ How to Run

```bash
# Generate data (one time)
mvn -Pgenerate-data exec:java

# Run JSON benchmark
mvn -Pjson-benchmark exec:java

# Run Protobuf benchmark
mvn -Pprotobuf-benchmark exec:java
```

---

### ⚠️ Notes on Benchmarking

* Single-run microbenchmarks can be misleading.
* Results are reported after JVM warm-up.
* Real-world systems amplify these differences under load.

---
