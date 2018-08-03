package com.ngtesting.platform.service.impl;

import com.ngtesting.platform.dao.AlertDao;
import com.ngtesting.platform.dao.TestPlanDao;
import com.ngtesting.platform.model.TstAlert;
import com.ngtesting.platform.model.TstPlan;
import com.ngtesting.platform.model.TstTask;
import com.ngtesting.platform.model.TstUser;
import com.ngtesting.platform.service.AlertService;
import com.ngtesting.platform.utils.DateUtil;
import com.ngtesting.platform.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AlertServiceImpl extends BaseServiceImpl implements AlertService {
    @Autowired
    private AlertDao alertDao;

    @Autowired
    private TestPlanDao planDao;

    @Override
    public List<TstAlert> list(Integer userId, Boolean isRead) {
        List<TstAlert> pos = scanAlerts(userId);
        List<TstAlert> vos = genVos(pos);

        return vos;
    }

    @Override
    public List<TstAlert> scanAlerts(Integer userId) {
        Date now = new Date();
        Date startTimeOfToday = DateUtil.GetStartTimeOfDay(now);
        Date endTimeOfToday = DateUtil.GetEndTimeOfDay(now);
        List<TstAlert> alerts = alertDao.scanAlerts(userId, startTimeOfToday, endTimeOfToday);

        return alerts;
    }

    @Override
    public List<TstAlert> genVos(List<TstAlert> pos) {
        List<TstAlert> vos = new LinkedList<>();

        for (TstAlert po: pos) {
            TstAlert vo = genVo(po);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public TstAlert genVo(TstAlert po) {

        Date now = new Date();
        Long startTimeOfToday = DateUtil.GetStartTimeOfDay(now).getTime();
        Long endTimeOfToday = DateUtil.GetEndTimeOfDay(now).getTime();

        Date startTime = po.getStartTime();
        Date endTime = po.getEndTime();

        if (endTime != null && endTime.getTime() >= startTimeOfToday && endTime.getTime() <= endTimeOfToday) {
            po.setTitle("任务" + StringUtil.highlightDict(po.getTitle()) + "完成");
        } else {
            po.setTitle("任务" + StringUtil.highlightDict(po.getTitle()) + "开始");
        }

        return po;
    }
    @Override
    public void create(TstTask task) {
        for (TstUser assignee : task.getAssignees()) {
            TstAlert po = getByTask(task.getId());;
            if (po == null) {
                po = new TstAlert();
            }

            po.setType("task");
            po.setTitle(task.getName());

            po.setEntityId(task.getId());

            po.setUserId(task.getUserId());
            po.setAssigneeId(assignee.getId());

            TstPlan plan= planDao.get(task.getPlanId());

            po.setStartTime(plan.getStartTime());
            po.setEndTime(plan.getEndTime());

            alertDao.create(po);
        }

    }

    @Override
    public void markAllReadPers(String idStr) {
//        String hql = "update TstAlert alert set alert.isRead=true where alert.id IN (?) " +
//                "AND alert.isRead != true AND alert.deleted != true AND alert.disabled != true";
//
//        List<Long> ids = new LinkedList();
//        for (String str : idStr.split(",")) {
//            ids.add(Long.valueOf(str));
//        }
//        getDao().executeByHql(hql, ids.toArray());
    }

    @Override
    public TstAlert getByTask(Integer id) {
//        DetachedCriteria dc = DetachedCriteria.forClass(TstAlert.class);
//
//        dc.add(Restrictions.eq("type", "task"));
//        dc.add(Restrictions.eq("entityId", id));
//
//        dc.add(Restrictions.eq("deleted", Boolean.FALSE));
//        dc.add(Restrictions.eq("disabled", Boolean.FALSE));
//
//        dc.addOrder(Order.asc("id"));
//
//        List<TstAlert> pos = findAllByCriteria(dc);
//        if (pos.size() > 0) {
//            return pos.getDetail(0);
//        } else {
//            return null;
//        }
        return null;
    }

}

