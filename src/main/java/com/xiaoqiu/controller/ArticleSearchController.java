package com.xiaoqiu.controller;

import com.xiaoqiu.entity.dto.ArticleDocument;
import com.xiaoqiu.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class ArticleSearchController {

    private final ESService esService;

    /**
     * 文章搜索接口
     * @param keyword 搜索关键字
     * @return 搜索结果
     */
    @GetMapping
    public ApiResult<List<ArticleDocument>> search(@RequestParam String keyword) {
        try {
            List<ArticleDocument> list = esService.search(keyword);
            return ApiResult.success(list);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.fail("搜索失败");
        }
    }
}
