package com.AIStudySystem.content;

import com.AIStudySystem.content.mapper.TeachplanMapper;
import com.AIStudySystem.content.model.dto.TeachplanDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeachplanMapperTests {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    public void testCourseCategory(){
        List<TeachplanDto> teachplanDto = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDto);
    }
}
