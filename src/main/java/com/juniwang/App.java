package com.juniwang;

import com.juniwang.docker.JDocker;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        JDocker docker = new JDocker();
        docker.ping();
    }
}
