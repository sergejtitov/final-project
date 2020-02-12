package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Link {
    private long linkId;
    private String applicationId;
    private long applicantId;

    @Override
    public int hashCode() {
        return Objects.hash(linkId, applicationId, applicantId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(linkId, link.linkId) &&
                Objects.equals(applicationId, link.applicationId) &&
                Objects.equals(applicantId, link.applicantId);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public Link() {
    }

    public Link(long linkId, String applicationId, long applicantId) {
        this.linkId = linkId;
        this.applicationId = applicationId;
        this.applicantId = applicantId;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }
}
