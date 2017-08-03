package com.ewininfo.festec.example;


import com.ewininfo.latte.activities.ProxyActivity;
import com.ewininfo.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
