package com.fadel.gestiondestock.controller;

import com.fadel.gestiondestock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

}
