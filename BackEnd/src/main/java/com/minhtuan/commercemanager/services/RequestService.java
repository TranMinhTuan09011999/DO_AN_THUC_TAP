package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

@Service
public class RequestService implements RequestRepository {

  private final String LOCALHOST_IPV4 = "127.0.0.1";
  private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

  public Boolean getURL(HttpServletRequest request) throws Exception {
    URL url = new URL(request.getRequestURL().toString());

    if (!url.getProtocol().startsWith("http") &&
            !url.getProtocol().startsWith("https")) {
      throw new Exception("Forbidden remote source");
    }
//
//        URLConnection connection = url.openConnection();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String body = reader.lines().collect(Collectors.joining());
    return true;
  }

  @Override
  public String getClientIp(HttpServletRequest request) {
    String ipAddress = request.getHeader("X-Forwarded-For");
    if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
      if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
        try {
          InetAddress inetAddress = InetAddress.getLocalHost();
          ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
          e.printStackTrace();
        }
      }
    }
    if(!StringUtils.isEmpty(ipAddress)
            && ipAddress.length() > 15
            && ipAddress.indexOf(",") > 0) {
      ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
    }
    return ipAddress;
  }
}
