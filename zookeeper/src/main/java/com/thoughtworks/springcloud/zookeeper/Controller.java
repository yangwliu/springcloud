package com.thoughtworks.springcloud.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/service")
  public String getServiceUrlByName(@RequestParam("name") String name) {
    List<ServiceInstance> instances = discoveryClient.getInstances("zookeeper-client");
    if (CollectionUtils.isEmpty(instances)) {
      return "No such service";
    }
    return instances.get(0).getUri().toString();
  }
}
