package com.example.studentsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentsystemApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); //起一个环境
    }

    @Test
    public void find_student_by_id() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/student/1") //perform执行一个请求， get构造一个请求
                //.param("id","1") //param添加请求传值
                .accept(MediaType.APPLICATION_JSON_UTF8)) //设置返回值类型
                //.andExpect(MockMvcResultMatchers.status().isOk()) //添加执行完成后的断言，与assert的作用相同
                //.andExpect(MockMvcResultMatchers.content().string("{\"name\":\"cc\",\"age\":20}"))
                .andDo(MockMvcResultHandlers.print()) //对结果进行什么处理
                .andReturn(); //执行完成后返回相应对结果
        int status = mvcResult.getResponse().getStatus(); //得到返回代码 200
        String content = mvcResult.getResponse().getContentAsString(); //得到返回结果

        Assert.assertEquals(200, status);
        Assert.assertEquals("{\"name\":\"cc\",\"age\":20}", content);
    }

    @Test
    public void delete_student_by_id() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/student/1") //perform执行一个请求， get构造一个请求
                //.param("id","1") //param添加请求传值
                .accept(MediaType.TEXT_HTML_VALUE)) //设置返回值类型
                .andDo(MockMvcResultHandlers.print()) //对结果进行什么处理
                .andReturn(); //执行完成后返回相应对结果
        int status = mvcResult.getResponse().getStatus(); //得到返回代码 200
        String content = mvcResult.getResponse().getContentAsString(); //得到返回结果

        Assert.assertEquals(200, status);
        Assert.assertEquals("删除成功！", content);
    }

    @Test
    public void add_student() throws Exception{
        Student student = new Student();
        student.setName("jj");
        student.setAge(20);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(student); //将object转成string吧

        mockMvc.perform(MockMvcRequestBuilders.post("/student") //perform执行一个请求， get构造一个请求
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8)) //设置返回值类型
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
