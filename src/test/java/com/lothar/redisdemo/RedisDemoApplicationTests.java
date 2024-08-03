package com.lothar.redisdemo;

import com.lothar.redisdemo.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    void testString() {
        //写入一条数据
        redisTemplate.opsForValue().set("name","lothar");
        //获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name="+name);
    }

    @Test
    void nameSaveUser() {
        //写入一条数据
        redisTemplate.opsForValue().set("user:100",new User("lothar",18));
        //获取String数据
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("user="+user);
    }
}
