package com.land.tag;

import javax.servlet.ServletContext;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import org.springframework.web.context.ServletContextAware;

public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {
  private ServletContext servletContext;
  
  public void initVariables() {
    this.firstPageLabel = "<li class=\"first\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">";
    this.previousPageLabel = "<li class=\"prev\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">";
    this.currentPageLabel = "<li class=\"current\"><a onClick=\"return false;\">{0}</a></li>";
    this.otherPageLabel = "<li><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a></li>";
    this.nextPageLabel = "<li class=\"next\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">";
    this.lastPageLabel = "<li class=\"last\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">";
  }
  
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
    initVariables();
  }
}
