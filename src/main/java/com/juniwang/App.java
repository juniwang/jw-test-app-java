package com.juniwang;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws URISyntaxException {
        testVarArgs("item1", "action1", "a", "b", "c", "d", "e", "f", "g");
        testVarArgs("item2", "action2", "a");
        testVarArgs("item3", "action3");
    }

    private static void testVarArgs(String item, String action, String... args) {
        System.out.println(item);
        System.out.println(action);

        Map<String, String> props = new HashMap<String, String>();
        for (int i = 1; i < args.length; i += 2) {
            props.put(args[i - 1], args[i]);
        }

        System.out.println(props);
    }

    private static void testUri() throws URISyntaxException {
        URI uri = new URI("https://github.com/jenkinsci/jira-steps-plugin/blob/20f48d3fa5e048c6df589a7bf1baf9a0a4f4c4b8/src/main/java/org/thoughtslive/jenkins/plugins/jira/login/SigningInterceptor.java?a=bb&b=ccc#fff");
        System.out.println(uri.getAuthority());
        System.out.println(uri.getFragment());
        System.out.println(uri.getHost());
        System.out.println(uri.getPath());
        System.out.println(uri.getQuery());
        System.out.println(uri.getRawAuthority());
        System.out.println(uri.getRawFragment());
        System.out.println(uri.getRawPath());
        System.out.println(uri.getRawQuery());
    }

    private static void printProperties() {
        System.getProperties().list(System.out);
    }
}
