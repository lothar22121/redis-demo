package com.lothar.redisdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lothar.redisdemo.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void testString() {
        //写入一条数据
        stringRedisTemplate.opsForValue().set("name","李四");
        //获取String数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name="+name);
    }


    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void nameSaveUser() throws JsonProcessingException {
        //创建对象
        User user = new User("曹操",18);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入一条数据
        stringRedisTemplate.opsForValue().set("user:200",json);
        //获取String数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        //手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1="+ user1);
    }

    @Test
    void HashTest() {
        stringRedisTemplate.opsForHash().put("user:400","name","白居易");
        stringRedisTemplate.opsForHash().put("user:400","age","18");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries="+entries);
    }

}
