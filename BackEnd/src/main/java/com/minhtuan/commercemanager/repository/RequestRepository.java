package com.minhtuan.commercemanager.repository;

import javax.servlet.http.HttpServletRequest;

public interface RequestRepository {
  String getClientIp(HttpServletRequest request);
}
