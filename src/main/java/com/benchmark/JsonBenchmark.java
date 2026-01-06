package com.benchmark;

import com.benchmark.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonBenchmark {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void run() throws Exception {

        File file = new File("data/users.json");

        // ---------- Deserialization ----------
        long startDeserialize = System.nanoTime();
        List<User> users = mapper.readValue(
                file,
                mapper.getTypeFactory()
                        .constructCollectionType(List.class, User.class)
        );
        long endDeserialize = System.nanoTime();

        // ---------- Serialization ----------
        long startSerialize = System.nanoTime();
        mapper.writeValue(new File("data/tmp-json-out.json"), users);
        long endSerialize = System.nanoTime();

        System.out.println("JSON Deserialize Time: "
                + ((endDeserialize - startDeserialize) / 1_000_000) + " ms");

        System.out.println("JSON Serialize Time:   "
                + ((endSerialize - startSerialize) / 1_000_000) + " ms");

        System.out.println("JSON Records Loaded: " + users.size());
        System.out.println("JSON File Size: " + file.length() / 1024 / 1024 + " MB");
    }
}
