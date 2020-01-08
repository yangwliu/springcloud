package com.thoughtworks.springcloud.zookeeper;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ZookeeperApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(ZookeeperApplication.class).run(args);
  }

}
