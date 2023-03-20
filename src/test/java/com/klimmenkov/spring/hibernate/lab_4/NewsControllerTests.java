package com.klimmenkov.spring.hibernate.lab_4;

import com.klimmenkov.spring.hibernate.lab_4.controller.admin.NewsController;
import com.klimmenkov.spring.hibernate.lab_4.entity.News;
import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NewsControllerTests {
    @Mock
    private NewsService newsService;
    @InjectMocks
    private NewsController newsController;
    @Mock
    private Model model;

    @Test
    public void testShowAllNews() {
        // mock news list
        List<News> newsList = new ArrayList<>();
        newsList.add(News.builder().id(1).type("Type1").info("Info1").build());
        newsList.add(News.builder().id(2).type("Type2").info("Info2").build());

        // when newsService.getAllNews() is called, return the mock news list
        Mockito.when(newsService.getAllNews()).thenReturn(newsList);

        // call the showAllNews method and verify that the model attribute "allNews" is set to the mock news list
        String viewName = newsController.showAllNews(model);
        Mockito.verify(model).addAttribute("allNews", newsList);
        Assert.assertEquals("admin/all-news", viewName);
    }

    @Test
    public void testAddNews() {
        // call the addNews method and verify that the model attribute "news" is set to a new News object
        String viewName = newsController.addNews(model);
        Mockito.verify(model).addAttribute("news", new News());
        Assert.assertEquals("admin/add-news", viewName);
    }

    @Test
    public void testAddUserWithValidData() {
        // create a mock News object
        News news = News.builder().id(1).type("Type").info("Info").build();

        // call the addUser method with the mock News object and verify that newsService.saveNews() is called with the same object
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = newsController.addUser(news, bindingResult);
        Mockito.verify(newsService).saveNews(news);
        Assert.assertEquals("redirect:/adminPage", viewName);
    }

    @Test
    public void testAddUserWithInvalidData() {
        // create a mock News object
        News news = News.builder().id(1).type("").info("").build();

        // call the addUser method with the mock News object and verify that newsService.saveNews() is not called
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = newsController.addUser(news, bindingResult);
        Mockito.verify(newsService, Mockito.never()).saveNews(news);
        Assert.assertEquals("admin/add-news", viewName);
    }

    @Test
    public void testDeleteNews() {
        // call the deleteNews method with a mock id value and verify that newsService.deleteNews() is called with the same id
        Integer id = 1;
        String viewName = newsController.deleteNews(id);
        Mockito.verify(newsService).deleteNews(id);
        Assert.assertEquals("redirect:/adminPage/allNews", viewName);
    }
}
