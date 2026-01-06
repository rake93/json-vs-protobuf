package com.benchmark;

import com.benchmark.model.User;
import com.benchmark.proto.UserProto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final int RECORD_COUNT = 1_000_000;

    public static void main(String[] args) throws Exception {

        new File("data").mkdirs();

        ObjectMapper mapper = new ObjectMapper();
        Random random = new Random();

        List<User> users = new ArrayList<>(RECORD_COUNT);
        UserProto.Users.Builder pbUsers = UserProto.Users.newBuilder();

        for (int i = 0; i < RECORD_COUNT; i++) {
            User user = new User(
                    i,
                    "User-" + i,
                    "user" + i + "@example.com",
                    random.nextInt(60) + 18,
                    i % 2 == 0
            );

            users.add(user);

            pbUsers.addUsers(
                    UserProto.User.newBuilder()
                            .setId(user.id)
                            .setName(user.name)
                            .setEmail(user.email)
                            .setAge(user.age)
                            .setActive(user.active)
                            .build()
            );
        }

        // JSON output
        mapper.writeValue(new File("data/users.json"), users);

        // Protobuf output
        try (FileOutputStream fos = new FileOutputStream("data/users.pb")) {
            pbUsers.build().writeTo(fos);
        }

        System.out.println("Sample data generated successfully.");
    }
}
