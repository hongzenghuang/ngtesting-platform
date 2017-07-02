package com.ngtesting.platform.vo;

import java.util.LinkedList;
import java.util.List;

public class TestCaseVo extends BaseVo {
	private static final long serialVersionUID = -5955583523485410239L;
	
	private String title;
	private Integer priority;
	private Integer estimate;
	private String objective;
    private String descr;

    private List<TestCasePropVo> props = new LinkedList<>();
	private List<TestCaseStepVo> steps = new LinkedList<>();

    public TestCaseVo(Long id, String title, Integer priority, Integer estimate, String objective, String descr) {
        super();

		this.id = id;
        this.title = title;
        this.priority = priority;
        this.estimate = estimate;
        this.objective = objective;
        this.descr = descr;

    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

    public List<TestCasePropVo> getProps() {
        return props;
    }

    public void setProps(List<TestCasePropVo> props) {
        this.props = props;
    }

	public List<TestCaseStepVo> getSteps() {
		return steps;
	}

	public void setSteps(List<TestCaseStepVo> steps) {
		this.steps = steps;
	}
}
