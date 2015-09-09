/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.web.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A simple implementation of {@link Servlet} interface that translates the parameters of
 * {@link #service(ServletRequest, ServletResponse)} function to {@link HttpServletRequest} and
 * {@link HttpServletResponse}. It does nothing else as anything else should be the responsibility
 * of the subclass.
 */
public abstract class HttpServlet implements Servlet {

  private ServletConfig config;

  @Override
  public void destroy() {
  }

  @Override
  public ServletConfig getServletConfig() {
    return config;
  }

  @Override
  public String getServletInfo() {
    return "";
  }

  @Override
  public void init(final ServletConfig pConfig) throws ServletException {
    config = pConfig;
  }

  protected abstract void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException;

  @Override
  public void service(final ServletRequest req, final ServletResponse res) throws ServletException,
      IOException {

    HttpServletRequest request;
    HttpServletResponse response;

    if (!((req instanceof HttpServletRequest) && (res instanceof HttpServletResponse))) {
      throw new ServletException("non-HTTP request or response");
    }

    request = (HttpServletRequest) req;
    response = (HttpServletResponse) res;

    service(request, response);
  }
}
