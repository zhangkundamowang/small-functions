package com.zk.mybatisplus.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Table默认的分页参数创建
 */
public class PageFactory {

    /**
     * 创建默认分页page
     */
    public static Page page() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        //第几页
        int pageNum = 1;
        //每页多少条数据
        int limit = 10;
        if (StringUtils.isNotBlank(request.getParameter("pageNum"))) {
            pageNum = Integer.valueOf(request.getParameter("pageNum"));
        }
        if (StringUtils.isNotBlank(request.getParameter("limit"))) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        return page(pageNum, limit);
    }

    /**
     * 创建默认分页page
     */
    public static Page page(Integer pageNum, Integer limit) {
        return new Page(pageNum, limit);
    }

    /**
     * 创建默认分页page
     */
    public static Page resultPage(List<Object> list) {
        Page page = page();
        page.setRecords(list);
        page.setTotal(list.size());
        int totalPagesNum = (int) (page.getTotal() / page.getSize());
        page.setPages((page.getTotal() % page.getSize() == 0) ? totalPagesNum : totalPagesNum + 1);
        return page;
    }


}
