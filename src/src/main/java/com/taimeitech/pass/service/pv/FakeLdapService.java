package com.taimeitech.pass.service.pv;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/*
*http://www.cnblogs.com/firebata/p/4689436.html
*/
@Component
public class FakeLdapService {
    public String findManagerForEmployee(String roleId) {
        return "Kermit The Frog";
    }

    public List<String> findAllSales() {
        return Arrays.asList("kermit", "gonzo", "fozzie");
    }
}
