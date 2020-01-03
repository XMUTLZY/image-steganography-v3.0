package sch.xmut.jake.imagestegangraphy.service.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import sch.xmut.jake.imagestegangraphy.document.AdminOperateLogDoc;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.AdminOperateLog;

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
}
