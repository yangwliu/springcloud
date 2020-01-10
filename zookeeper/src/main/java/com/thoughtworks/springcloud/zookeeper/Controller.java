package com.thoughtworks.springcloud.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class Controller {

  private final DiscoveryClient discoveryClient;
  private final RestTemplate restTemplate;

  public Controller(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
    this.discoveryClient = discoveryClient;
    this.restTemplate = restTemplate;
  }

  @RequestMapping("/service")
  public String getServiceUrlByName(@RequestParam("name") String name) {
    List<ServiceInstance> instances = discoveryClient.getInstances("zookeeper-client");
    if (CollectionUtils.isEmpty(instances)) {
      return "No such service";
    }
    return instances.get(0).getUri().toString();
  }

  @RequestMapping("/hello")
  public String sayHello() {

    log.info("enter into current service!");
    return "hello world!";
  }

  @RequestMapping("/proxy/hello")
  public String proxyHello() {
    return restTemplate.getForEntity("http://zookeeper-client/hello", String.class).getBody();
  }
}
