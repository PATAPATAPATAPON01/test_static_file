package com.example.demo.lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

/**
 * Created by PataPon on 2017/12/13.
 */
public class LuaTest {

    public static void main(String[] args) throws IOException {
        Globals globals = JsePlatform.standardGlobals();

        System.out.println(new File(".").getAbsolutePath());
        String script="./src/main/java/com/example/demo/lua/hello.lua";
        System.out.println(script);
        System.out.println(new File(script).exists());
        LuaValue chunk = globals.loadfile(script);
        chunk.call( LuaValue.valueOf(script) );
        Varargs test = globals.get("test").invoke(LuaValue.valueOf(1), LuaValue.valueOf(2));
        System.out.println(test.toString());
        System.out.println(Instant.now().getEpochSecond());
    }
}
