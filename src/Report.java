package com.nielsen.sdf.domain;

public class Report {
    private Integer id;

    private String reportingFactTypeName;

    private String comment;

    private Integer projectId;

    private Boolean isProductApplicable;

    private String preBuildScript;

    private String buildScript;

    private String postBuildScript;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportingFactTypeName() {
        return reportingFactTypeName;
    }

    public void setReportingFactTypeName(String reportingFactTypeName) {
        this.reportingFactTypeName = reportingFactTypeName == null ? null : reportingFactTypeName.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Boolean getIsProductApplicable() {
        return isProductApplicable;
    }

    public void setIsProductApplicable(Boolean isProductApplicable) {
        this.isProductApplicable = isProductApplicable;
    }

    public String getPreBuildScript() {
        return preBuildScript;
    }

    public void setPreBuildScript(String preBuildScript) {
        this.preBuildScript = preBuildScript == null ? null : preBuildScript.trim();
    }

    public String getBuildScript() {
        return buildScript;
    }

    public void setBuildScript(String buildScript) {
        this.buildScript = buildScript == null ? null : buildScript.trim();
    }

    public String getPostBuildScript() {
        return postBuildScript;
    }

    public void setPostBuildScript(String postBuildScript) {
        this.postBuildScript = postBuildScript == null ? null : postBuildScript.trim();
    }
}