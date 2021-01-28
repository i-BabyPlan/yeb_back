package com.zoux.server.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zoux.server.mapper.AdminMapper;
import com.zoux.server.pojo.Admin;
import com.zoux.server.service.impl.AdminServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class DatasourceTest {
    @Autowired
    AdminMapper adminMapper;

    @Test
    public void dataSource() {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", "admin").eq("enabled" , true));
        System.out.println(admin);
    }
    @Test
    public  void decode(CharSequence s) {
        s="$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.";
        int nChars = s.length();
        if (nChars % 2 != 0) {
        }
        byte[] result = new byte[nChars / 2];
        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i + 1), 16);
            if (msb < 0 || lsb < 0) {

            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        System.out.println(result); ;
    }
}
