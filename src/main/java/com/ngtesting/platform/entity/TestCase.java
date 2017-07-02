package com.ngtesting.platform.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tst_case")
public class TestCase extends BaseEntity {
	private static final long serialVersionUID = -7253288259861070288L;

    private String title;
	private Integer priority;
	private Integer estimate;

	@Column(name = "objective", length = 1000)
	private String objective;

	@Column(name = "descr", length = 1000)
    private String descr;
	private Integer ordr;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suite_id", insertable = false, updatable = false)
    private TestSuite suite;

    @Column(name = "suite_id")
    private Long suiteId;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private TestProject project;

    @Column(name = "project_id")
    private Long projectId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private TestUser user;

    @Column(name = "user_id")
    private Long userId;

	@OneToMany(mappedBy="testCase", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderBy("ordr")
	private List<TestCaseStep> steps = new LinkedList<>();

	@OneToMany(mappedBy="testCase", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderBy("ordr")
	private List<TestCaseProp> props = new LinkedList<>();

	public List<TestCaseProp> getProps() {
		return props;
	}

	public void setProps(List<TestCaseProp> props) {
		this.props = props;
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public TestProject getProject() {
		return project;
	}

	public void setProject(TestProject project) {
		this.project = project;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public TestUser getUser() {
		return user;
	}

	public void setUser(TestUser user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public List<TestCaseStep> getSteps() {
		return steps;
	}

	public void setSteps(List<TestCaseStep> steps) {
		this.steps = steps;
	}

	public Integer getOrdr() {
		return ordr;
	}

	public void setOrdr(Integer ordr) {
		this.ordr = ordr;
	}

	public TestSuite getSuite() {
		return suite;
	}

	public void setSuite(TestSuite suite) {
		this.suite = suite;
	}

	public Long getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(Long suiteId) {
		this.suiteId = suiteId;
	}
}
