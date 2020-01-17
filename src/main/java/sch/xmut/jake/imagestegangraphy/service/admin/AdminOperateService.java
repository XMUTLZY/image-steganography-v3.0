package sch.xmut.jake.imagestegangraphy.service.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sch.xmut.jake.imagestegangraphy.document.AdminOperateLogDoc;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.AdminOperateLog;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jake.lin on 2020/1/3
 */
@Service
public class AdminOperateService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public AdminOperateLogDoc insert(AdminOperateLog adminOperateLog) {
        AdminOperateLogDoc adminOperateLogDoc = new AdminOperateLogDoc();
        BeanUtils.copyProperties(adminOperateLog, adminOperateLogDoc);
        return mongoTemplate.insert(adminOperateLogDoc);
    }

    public List<AdminOperateLog> findByKey(String key) {
        List<AdminOperateLog> adminOperateLogList = new ArrayList<>();
        List<AdminOperateLogDoc> adminOperateLogDocList;
        if (!StringUtils.hasText(key)) {
            adminOperateLogDocList = mongoTemplate.find(new Query().with(Sort.by(Sort.Order.desc("operate_time"))), AdminOperateLogDoc.class);
        } else {
            Pattern pattern = Pattern.compile(String.format("^.*%s.*$", key), Pattern.CASE_INSENSITIVE);
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("operate_str").regex(pattern), Criteria.where("user_name").regex(pattern)
                    , Criteria.where("role_name").regex(pattern));
            Query query = new Query(criteria);
            query.with(Sort.by(Sort.Order.desc("operate_time")));
            adminOperateLogDocList =  mongoTemplate.find(query, AdminOperateLogDoc.class);
        }

        for (AdminOperateLogDoc adminOperateLogDoc : adminOperateLogDocList) {
            AdminOperateLog adminOperateLog = new AdminOperateLog();
            BeanUtils.copyProperties(adminOperateLogDoc, adminOperateLog);
            adminOperateLogList.add(adminOperateLog);
        }
        return adminOperateLogList;
    }
}
