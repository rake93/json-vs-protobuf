package com.benchmark;

import com.benchmark.proto.UserProto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ProtobufBenchmark {

    public static void run() throws Exception {

        File file = new File("data/users.pb");

        // ---------- Deserialization ----------
        long startDeserialize = System.nanoTime();
        UserProto.Users users =
                UserProto.Users.parseFrom(new FileInputStream(file));
        long endDeserialize = System.nanoTime();

        // ---------- Serialization ----------
        long startSerialize = System.nanoTime();
        try (FileOutputStream fos =
                     new FileOutputStream("data/tmp-proto-out.pb")) {
            users.writeTo(fos);
        }
        long endSerialize = System.nanoTime();

        System.out.println("Protobuf Deserialize Time: "
                + ((endDeserialize - startDeserialize) / 1_000_000) + " ms");

        System.out.println("Protobuf Serialize Time:   "
                + ((endSerialize - startSerialize) / 1_000_000) + " ms");

        System.out.println("Protobuf Records Loaded: " + users.getUsersCount());
        System.out.println("Protobuf File Size: " + file.length() / 1024 / 1024 + " MB");
    }
}
