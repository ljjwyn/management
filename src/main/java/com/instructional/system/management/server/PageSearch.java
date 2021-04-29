package com.instructional.system.management.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.instructional.system.management.config.MybatisPlusConfig;
import com.instructional.system.management.mapper.ScoresMapper;
import com.instructional.system.management.mapper.StudentsMapper;
import com.instructional.system.management.model.Scores;
import com.instructional.system.management.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljjwyn
 */
@Service
public class PageSearch {
    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private ScoresMapper scoresMapper;

    final int PAGE_COUNT = 20;

    public List<Scores> pageSearchScores(int page){
        IPage<Scores> scoresPage = new Page<>(page, PAGE_COUNT);
        MybatisPlusConfig.myTableNameScores.set("scores_1");
        scoresPage = scoresMapper.selectPage(scoresPage, null);
        List<Scores> scoresList = scoresPage.getRecords();
        return scoresList;
    }

    public List<Students> pageSearchStudents(int page){
        IPage<Students> studentsPage = new Page<>(page, PAGE_COUNT);
        MybatisPlusConfig.myTableNameScores.set("scores_1");
        studentsPage = studentsMapper.selectPage(studentsPage, null);
        List<Students> studentsList = studentsPage.getRecords();
        return studentsList;
    }
}
