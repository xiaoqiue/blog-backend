package com.xiaoqiu.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import com.xiaoqiu.constant.ESConstant;
import com.xiaoqiu.entity.dto.ArticleDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESServiceImpl implements ESService {

    private final ElasticsearchClient client;

    public ESServiceImpl(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public void saveArticle(ArticleDocument doc) throws IOException {
        client.index(IndexRequest.of(i -> i
                .index(ESConstant.ARTICLE_INDEX)
                .id(doc.getId().toString())
                .document(doc)
        ));
    }

    @Override
    public void deleteArticle(Long id) throws IOException {
        client.delete(DeleteRequest.of(d -> d
                .index(ESConstant.ARTICLE_INDEX)
                .id(id.toString())
        ));
    }

    @Override
    public List<ArticleDocument> search(String keyword) throws IOException {
        SearchRequest request = SearchRequest.of(s -> s
                .index(ESConstant.ARTICLE_INDEX)
                .query(q -> q
                        .multiMatch(m -> m
                                .query(keyword)
                                .fields("title", "content", "tags")
                        )
                )
        );

        SearchResponse<ArticleDocument> response = client.search(request, ArticleDocument.class);
        return response.hits().hits().stream()
                .map(hit -> hit.source())
                .collect(Collectors.toList());
    }
}
